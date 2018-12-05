package com.jscheng.spluto.markdown.core.parser;

public enum CellAlign {
	LEFT("left"),
	CENTER("center"),
	RIGHT("right"),
	NONE("");
	
	private String value;
	private CellAlign(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
