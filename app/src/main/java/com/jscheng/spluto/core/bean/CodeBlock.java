package com.jscheng.spluto.core.bean;

import com.jscheng.spluto.core.parser.BlockType;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class CodeBlock extends Block {
    private ValuePart valuePart;

    public CodeBlock() {
        super(BlockType.CODE);
    }

    public void setValuePart(ValuePart valuePart) {
        this.valuePart = valuePart;
    }

    public ValuePart getValuePart(){
        return valuePart;
    }

}
