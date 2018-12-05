package com.jscheng.spluto.markdown.view.parser;

import com.jscheng.spluto.markdown.core.bean.CommonTextBlock;
import com.jscheng.spluto.markdown.core.bean.HeadLineBlock;
import com.jscheng.spluto.markdown.core.bean.ValuePart;
import com.jscheng.spluto.markdown.core.parser.TextType;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.part.PartType;

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
        Part.Builder builder = new Part.Builder();
        builder = builder.setFontLevel(fontLevel);

        if (txtTypes.contains(TextType.IMG)) {
            builder = builder.setType(PartType.PART_IMAGE);
            builder = builder.setDescripe(valuePart.getTitle());
            builder = builder.setUrl(valuePart.getUrl());

        } else if (txtTypes.contains(TextType.LINK)) {
            builder = builder.setType(PartType.PART_LINK);
            builder = builder.setDescripe(valuePart.getTitle());
            builder = builder.setUrl(valuePart.getUrl());

        } else if (txtTypes.contains(TextType.CODE_WORD)){
            builder = builder.setType(PartType.PART_CODE);
            builder = builder.setValue(valuePart.getValue());

        } else {
            builder = builder.setType(PartType.PART_TEXT);
            builder = builder.setValue(valuePart.getValue());

        }
        if (txtTypes.contains(TextType.BOLD_WORD)) {
            builder = builder.setBold(true);
        }
        if (txtTypes.contains(TextType.ITALIC_WORD)) {
            builder = builder.setItalic(true);
        }
        if (txtTypes.contains(TextType.STRIKE_WORD)) {
            builder = builder.setStrike(true);
        }

        return builder.build();
    }
}
