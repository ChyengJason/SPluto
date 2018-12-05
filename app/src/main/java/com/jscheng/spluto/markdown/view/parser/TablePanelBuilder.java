package com.jscheng.spluto.markdown.view.parser;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.TableBlock;
import com.jscheng.spluto.markdown.core.parser.BlockType;
import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.panel.TablePanel;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class TablePanelBuilder implements IPanelBuilder {
    @Override
    public List<Panel> build(Block block) {
        if (block.getType() != BlockType.TABLE) {
            return null;
        }
        TableBlock tableBlock = (TableBlock)block;
        return parseTableBlock(tableBlock);
    }

    private List<Panel> parseTableBlock(TableBlock tableBlock) {
        TablePanel panel = new TablePanel();
        // TODO: 解析table表格
        return Arrays.asList((Panel)panel);
    }
}
