package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.CommonTextBlock;
import com.jscheng.spluto.core.bean.HeadLineBlock;
import com.jscheng.spluto.core.bean.ValuePart;
import com.jscheng.spluto.core.parser.TextType;
import com.jscheng.spluto.view.part.Part;
import com.jscheng.spluto.view.part.PartType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class PartBuilder {

    public static List<Part> buildParts(CommonTextBlock block) {
        List<Part> parts = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            parts.add(parserPartImpl(part, 0));
        }
        return parts;
    }

    public static List<Part> buildParts(HeadLineBlock block) {
        List<Part> parts = new ArrayList<>();
        for (ValuePart part : block.getValueParts()) {
            parts.add(parserPartImpl(part, block.getFontLevel()));
        }
        return parts;
    }

    private static Part parserPartImpl(ValuePart valuePart, int fontLevel) {
        List<TextType> txtTypes = valuePart.getTypes();
        Part part = new Part();
        if (txtTypes.contains(TextType.IMG)) {
            part.setPartType(PartType.PART_IMAGE);
            part.setDescripe(valuePart.getTitle());
            part.setUrl(valuePart.getUrl());

        } else if (txtTypes.contains(TextType.LINK)) {
            part.setPartType(PartType.PART_LINK);
            part.setDescripe(valuePart.getTitle());
            part.setUrl(valuePart.getUrl());

        } else if (txtTypes.contains(TextType.CODE_WORD)){
            part.setPartType(PartType.PART_CODE);
            part.setValue(valuePart.getValue());

        } else {
            part.setPartType(PartType.PART_TEXT);
            part.setValue(valuePart.getValue());

        }

        part.setFontLevel(fontLevel);

        if (txtTypes.contains(TextType.BOLD_WORD)) {
            part.setBold(true);
        }
        if (txtTypes.contains(TextType.ITALIC_WORD)) {
            part.setItalic(true);
        }
        if (txtTypes.contains(TextType.STRIKE_WORD)) {
            part.setStrike(true);
        }
        return part;
    }
}
