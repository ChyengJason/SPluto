package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.HeadLineBlock;
import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.panel.TextPanel;
import com.jscheng.spluto.view.part.Part;

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
        for (Part part : PartBuilder.buildParts(headLineBlock)) {
            panel.addPart(part);
        }
        return Arrays.asList((Panel)panel);
    }
}
