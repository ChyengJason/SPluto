package com.jscheng.spluto.core.bean;

public class TextOrBlock {

	private boolean isBlock;
	private String text;
	private Block block;
	
	public TextOrBlock(Block block) {
		super();
		this.block = block;
		this.isBlock = true;
	}
	
	public TextOrBlock(String text) {
		super();
		this.text = text;
		this.isBlock = false;
	}
	
	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	@Override
	public String toString() {
		return isBlock?text:block.toString();
	}
}
