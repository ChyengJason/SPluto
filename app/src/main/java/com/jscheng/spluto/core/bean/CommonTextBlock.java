package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.core.parser.CellAlign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class CommonTextBlock extends Block {
    private CellAlign align;
    private List<ValuePart> valueParts;

    public CommonTextBlock() {
        super(BlockType.ROW);
        this.valueParts = new ArrayList<>();
    }

    public CellAlign getAlign() {
        return align;
    }

    public void setAlign(CellAlign align) {
        this.align = align;
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
