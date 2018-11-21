package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.TableBlock;
import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.view.Panel;
import com.jscheng.spluto.view.panel.TablePanel;

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
