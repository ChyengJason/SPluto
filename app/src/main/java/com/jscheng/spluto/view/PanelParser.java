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
 * 需要重构
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
        return parserImpl(block);
    }

    private static List<Panel> parserImpl(Block block) {
        switch (block.getType()) {
            case HEADLINE:
                return parserImpl((HeadLineBlock)block);
            case CODE:
                return parserImpl((CodeBlock)block);
            case LIST:
                return parserImpl((ListBlock)block);
            case TABLE:
                return parserImpl((TableBlock)block);
            default:
                return parserImpl((CommonTextBlock)block);
        }
    }

    private static List<Panel> parserImpl(ListBlock block) {
        List<Panel> panels = new ArrayList<>();
        List<ListBlock> listBlocks = block.getListData();
        for (int i = 0; i< listBlocks.size(); i++) {
            panels.addAll(parserListData(listBlocks.get(i), null, i + 1));
        }
        return panels;
    }

    private static List<Panel> parserListData(ListBlock block, ListType parentListType, int index) {
        List<Panel> panels = new ArrayList<>();
        List<ListBlock> listBlocks = block.getListData();
        ListType listType = block.getListType();
        Block lineData = block.getLineData();

        if (listType == null) {
            listType = parentListType;
        }

        if (listBlocks != null) {
            for (int i = 0; i < listBlocks.size(); i++) {
                panels.addAll(parserListData(listBlocks.get(i), listType, i + 1));
            }
        }
        if (lineData != null) {
            panels.add(parserLineData(lineData, listType, index));
        }
        return panels;
    }

    private static ListPanel parserLineData(Block block, ListType listType, int index) {
        ListPanel panel = null;
        if (listType == ListType.ORDERED_LIST) {
            panel = new OrderListPanel(index);
        } else if (listType == ListType.UNORDERED_LIST) {
            panel = new UnorderListPanel();
        } else if (listType == ListType.TODO_LIST) {
            panel = new TodoListPanel(true);
        } else if (listType == ListType.UNTODO_LIST) {
            panel = new TodoListPanel(false);
        } else if (listType == ListType.QUOTE) {
            panel = new QuotePanel();
        }
        if (panel != null) {
            if (block.getType() == BlockType.HEADLINE) {
                for (Span span : parserHeadLineSpansImpl((HeadLineBlock) block)) {
                    panel.addSpan(span);
                }
            } else if (block.getType() == BlockType.ROW) {
                for (Span span : parserCommonTextSpansImpl((CommonTextBlock) block)) {
                    panel.addSpan(span);
                }
            }
        }
        return panel;
    }

    private static List<Panel> parserImpl(CodeBlock block) {
        CodePanel panel = new CodePanel();
        ValuePart valuePart = block.getValuePart();
        panel.setValue(valuePart.getValue());
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(HeadLineBlock block) {
        TextPanel panel = new TextPanel();
        for (Span span : parserHeadLineSpansImpl(block)) {
            panel.addSpan(span);
        }
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(TableBlock block) {
        TablePanel panel = new TablePanel();
        // TODO: 解析table表格
        return Arrays.asList((Panel)panel);
    }

    private static List<Panel> parserImpl(CommonTextBlock block) {
        TextPanel panel = new TextPanel();
        for (Span span : parserCommonTextSpansImpl(block)) {
            panel.addSpan(span);
        }
        return Arrays.asList((Panel) panel);
    }

    private static List<Span> parserCommonTextSpansImpl(CommonTextBlock block) {
        List<Span> spans = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            spans.add(parserSpanImpl(part, 0));
        }
        return spans;
    }

    private static List<Span> parserHeadLineSpansImpl(HeadLineBlock block) {
        List<Span> spans = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            spans.add(parserSpanImpl(part, block.getFontLevel()));
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
