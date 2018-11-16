package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;

import java.util.List;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class TableBlock extends Block {
    private List<List<Block>> tableData;

    public TableBlock() {
        super(BlockType.CODE);
    }

    public List<List<Block>> getTableData() {
        return tableData;
    }

    public void setTableData(List<List<Block>> tableData) {
        this.tableData = tableData;
    }

}
