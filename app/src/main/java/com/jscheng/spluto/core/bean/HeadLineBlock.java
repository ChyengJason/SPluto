package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class HeadLineBlock extends Block {
    private int fontLevel;
    private List<ValuePart> valueParts;

	public HeadLineBlock() {
		super(BlockType.HEADLINE);
        this.valueParts = new ArrayList<>();
	}

	public int getFontLevel() {
		return fontLevel;
	}

	public void setFontLevel(int level) {
		this.fontLevel = level;
	}

	public List<ValuePart> getValueParts() {
		return valueParts;
	}

	public void setValueParts(ValuePart... valueParts) {
		this.valueParts.clear();
		for (ValuePart part : valueParts) {
			this.valueParts.add(part);
		}
	}

	public void setValueParts(List<ValuePart> parts) {
		this.valueParts.clear();
		this.valueParts.addAll(parts);
	}

}
