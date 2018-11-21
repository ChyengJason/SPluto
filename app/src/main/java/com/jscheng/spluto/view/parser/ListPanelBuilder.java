package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.CommonTextBlock;
import com.jscheng.spluto.core.bean.HeadLineBlock;
import com.jscheng.spluto.core.bean.ListBlock;
import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.core.parser.ListType;
import com.jscheng.spluto.view.panel.ListPanel;
import com.jscheng.spluto.view.panel.OrderListPanel;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.panel.QuotePanel;
import com.jscheng.spluto.view.panel.TodoListPanel;
import com.jscheng.spluto.view.panel.UnorderListPanel;
import com.jscheng.spluto.view.part.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class ListPanelBuilder implements IPanelBuilder {
    @Override
    public List<Panel> build(Block block) {
        if (block.getType() != BlockType.LIST) {
            return null;
        }
        ListBlock mListBlock = (ListBlock) block;
        return parseListData(mListBlock, null, 0);
    }

    private static List<Panel> parseListData(ListBlock block, ListType parentListType, int index) {
        List<Panel> panels = new ArrayList<>();
        List<ListBlock> listBlocks = block.getListData();
        ListType listType = block.getListType();
        Block lineData = block.getLineData();

        if (listType == null) {
            listType = parentListType;
        }

        if (listBlocks != null) {
            for (int i = 0; i < listBlocks.size(); i++) {
                panels.addAll(parseListData(listBlocks.get(i), listType, i + 1));
            }
        }
        if (lineData != null) {
            panels.add(parseLineData(lineData, listType, index));
        }
        return panels;
    }

    private static ListPanel parseLineData(Block block, ListType listType, int index) {
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
                for (Part part : PartBuilder.buildParts((HeadLineBlock) block)) {
                    panel.addPart(part);
                }
            } else if (block.getType() == BlockType.ROW) {
                for (Part part : PartBuilder.buildParts((CommonTextBlock) block)) {
                    panel.addPart(part);
                }
            }
        }
        return panel;
    }
}
