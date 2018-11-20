package com.jscheng.spluto.view.part;

import com.jscheng.spluto.view.Part;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class CodePart extends Part {
    private String value;

    public CodePart(String value) {
        super(PartType.PART_CODE);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return value;
    }

}
