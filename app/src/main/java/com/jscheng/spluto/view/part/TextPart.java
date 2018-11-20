package com.jscheng.spluto.view.part;

import com.jscheng.spluto.view.Part;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class TextPart extends Part {
    private static final String TAG = "CJS";
    private String value;

    public TextPart(String value) {
        super(PartType.PART_TEXT);
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
