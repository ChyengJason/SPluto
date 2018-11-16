package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.core.parser.tool.JsonUtil;

public abstract class Block {
    private BlockType type;

	public Block(BlockType type) {
	    this.type = type;
    }

	public BlockType getType() {
		return type;
	}

    public void setType(BlockType type) {
        this.type = type;
    }

	@Override
	public String toString() {
	    return JsonUtil.toJson(this);
	}
}
