package com.jscheng.spluto.view;

import android.util.Log;

import com.jscheng.spluto.core.bean.*;
import com.jscheng.spluto.core.parser.*;
import com.jscheng.spluto.view.panel.*;
import com.jscheng.spluto.view.span.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class PanelParser {
    private static final String TAG = "CJS";
    public static List<Panel> parser(String source) {
        List<Block> blocks = Analyzer.analyze(source);
        for (Block block: blocks) {
            Log.d(TAG, "parser: " + block);
        }
        return parser(blocks);
    }

    public static List<Panel> parser(List<Block> blocks) {
        List<Panel> panels = new ArrayList<>();
        for (Block block : blocks) {
            panels.addAll(parser(block));
        }
        return panels;
    }

    public static List<Panel> parser(Block block) {
        return parserImpl(block, 0);
    }

    private static List<Panel> parserImpl(Block block, int level) {
        switch (block.getType()) {
            case HEADLINE:
                return parserImpl((HeadLineBlock)block, level);
            case CODE:
                return parserImpl((CodeBlock)block, level);
            case LIST:
                return parserImpl((ListBlock)block, level);
            case TABLE:
                return parserImpl((TableBlock)block, level);
            default:
                return parserImpl((CommonTextBlock)block, level);
        }
    }

    private static List<Panel> parserImpl(ListBlock block, int level) {
        List<Panel> panels = new ArrayList<>();
        List<ListBlock> listBlocks = block.getListData();
        for (ListBlock listBlock : listBlocks) {
            panels.addAll(parserListData(listBlock, null, level, 0));
        }
        return panels;
    }

    private static List<Panel> parserListData(ListBlock block, ListType parentListType, int level, int index) {
        List<Panel> panels = new ArrayList<>();
        List<ListBlock> listBlocks = block.getListData();
        ListType listType = block.getListType();
        Block lineData = block.getLineData();

        if (listType == null) {
            listType = parentListType;
        }

        if (listBlocks != null) {
            for (int i = 0; i < listBlocks.size(); i++) {
                panels.addAll(parserListData(listBlocks.get(i), listType,level+1, index));
            }
        }
        if (lineData != null) {
            panels.add(parserLineData(lineData, listType, level, index));
        }
        return panels;
    }

    private static ListPanel parserLineData(Block block, ListType listType, int level, int index) {
        ListPanel panel = null;
        if (listType == ListType.ORDERED_LIST) {
            panel = new OrderListPanel(index);
        } else if (listType == ListType.UNORDERED_LIST) {
            panel = new UnorderListPanel();
        } else if (listType == ListType.TODO_LIST) {
            panel = new TodoListPanel();
        } else if (listType == ListType.QUOTE) {
            panel = new QuotePanel();
        }
        if (panel != null) {
            if (block.getType() == BlockType.HEADLINE) {
                panel.setSpans(parserSpansImpl((HeadLineBlock) block, 0));
            } else if (block.getType() == BlockType.ROW) {
                panel.setSpans(parserSpansImpl((CommonTextBlock) block));
            }
            panel.setLevel(level);
        }
        return panel;
    }

    private static List<Panel> parserImpl(CodeBlock block, int level) {
        CodePanel panel = new CodePanel();
        ValuePart valuePart = block.getValuePart();
        panel.setValue(valuePart.getValue());
        panel.setLevel(level);
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(HeadLineBlock block, int level) {
        LinePanel panel = new LinePanel();
        int fontLevel = block.getFontLevel();
        panel.setLevel(level);
        panel.setSpans(parserSpansImpl(block, fontLevel));
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(TableBlock block, int level) {
        TablePanel panel = new TablePanel();
        panel.setLevel(level);
        // TODO: 解析table表格
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(CommonTextBlock block, int level) {
        LinePanel panel = new LinePanel();
        panel.setLevel(level);
        panel.setSpans(parserSpansImpl(block));
        return Arrays.asList((Panel) panel);
    }

    private static List<Span> parserSpansImpl(CommonTextBlock block) {
        List<Span> spans = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            spans.add(parserSpanImpl(part, 0));
        }
        return spans;
    }

    private static List<Span> parserSpansImpl(HeadLineBlock block, int fontLevel) {
        List<Span> spans = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            spans.add(parserSpanImpl(part, fontLevel));
        }
        return spans;
    }

    private static Span parserSpanImpl(ValuePart valuePart, int fontLevel) {
        List<TextType> txtTypes = valuePart.getTypes();
        Span span = null;
        if (txtTypes.contains(TextType.IMG)) {
            span = new ImageSpan(valuePart.getUrl(), valuePart.getTitle());
        } else if (txtTypes.contains(TextType.LINK)) {
            span = new LinkSpan(valuePart.getUrl(), valuePart.getTitle());
        } else if (txtTypes.contains(TextType.CODE_WORD)){
            span = new CodeSpan(valuePart.getValue());
        } else {
            span = new TextSpan(valuePart.getValue());
        }

        if (txtTypes.contains(TextType.BOLD_WORD)) {
            span.setBold(true);
        }
        if (txtTypes.contains(TextType.ITALIC_WORD)) {
            span.setItalic(true);
        }
        if (txtTypes.contains(TextType.STRIKE_WORD)) {
            span.setStrike(true);
        }
        span.setFontLevel(fontLevel);
        return span;
    }
}
