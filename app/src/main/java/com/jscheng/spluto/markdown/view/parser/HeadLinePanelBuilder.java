package com.jscheng.spluto.markdown.view.parser;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.HeadLineBlock;
import com.jscheng.spluto.markdown.core.parser.BlockType;
import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.panel.TextPanel;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class HeadLinePanelBuilder implements IPanelBuilder {
    @Override
    public List<Panel> build(Block block) {
        if (block.getType() != BlockType.HEADLINE) {
            return null;
        }
        HeadLineBlock headLineBlock = (HeadLineBlock)block;
        return parseHeadLineBlock(headLineBlock);
    }

    private List<Panel> parseHeadLineBlock(HeadLineBlock headLineBlock) {
        TextPanel panel = new TextPanel();
        panel.setParts(PartBuilder.buildParts(headLineBlock));
        return Arrays.asList((Panel)panel);
    }
}
