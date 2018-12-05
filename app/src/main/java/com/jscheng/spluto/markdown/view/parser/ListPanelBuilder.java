package com.jscheng.spluto.markdown.view.parser;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.CommonTextBlock;
import com.jscheng.spluto.markdown.core.bean.HeadLineBlock;
import com.jscheng.spluto.markdown.core.bean.ListBlock;
import com.jscheng.spluto.markdown.core.parser.BlockType;
import com.jscheng.spluto.markdown.core.parser.ListType;
import com.jscheng.spluto.markdown.view.panel.ListPanel;
import com.jscheng.spluto.markdown.view.Panel;

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
        ListPanel panel = new ListPanel(listType, index);
        if (panel != null) {
            if (block.getType() == BlockType.HEADLINE) {
                panel.setParts(PartBuilder.buildParts((HeadLineBlock) block));
            } else if (block.getType() == BlockType.ROW) {
                panel.setParts(PartBuilder.buildParts((CommonTextBlock) block));
            }
        }
        return panel;
    }
}
