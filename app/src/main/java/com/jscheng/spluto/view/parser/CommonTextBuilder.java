package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.CommonTextBlock;
import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.panel.TextPanel;
import com.jscheng.spluto.view.part.Part;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class CommonTextBuilder implements IPanelBuilder {

    @Override
    public List<Panel> build(Block block) {
        if (block.getType() != BlockType.ROW) {
            return null;
        }
        CommonTextBlock textBlock = (CommonTextBlock) block;
        return parseTextBlock(textBlock);
    }

    private List<Panel> parseTextBlock(CommonTextBlock textBlock) {
        TextPanel panel = new TextPanel();
        panel.setParts(PartBuilder.buildParts(textBlock));
        return Arrays.asList((Panel) panel);
    }
}
