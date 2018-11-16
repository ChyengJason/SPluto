package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.core.parser.ListType;

import java.util.List;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class ListBlock extends Block {
	private String mdToken;
    private List<ListBlock> listData;
    private Block lineData;
    private ListType listType;

    public ListBlock() {
        super(BlockType.LIST);
    }

    public String getMdToken() {
		return mdToken;
	}

	public void setMdToken(String mdToken) {
		this.mdToken = mdToken;
	}

    public List<ListBlock> getListData() {
        return listData;
    }

    public void setListData(List<ListBlock> listData) {
        this.listData = listData;
    }

    public Block getLineData() {
        return lineData;
    }

    public void setLineData(Block lineData) {
        this.lineData = lineData;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    public ListType getListType() {
        return listType;
    }

}
