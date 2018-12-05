package com.jscheng.spluto.markdown.view.parser;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.CodeBlock;
import com.jscheng.spluto.markdown.core.bean.ValuePart;
import com.jscheng.spluto.markdown.core.parser.BlockType;
import com.jscheng.spluto.markdown.view.panel.CodePanel;
import com.jscheng.spluto.markdown.view.Panel;
import com.jscheng.spluto.markdown.view.part.Part;
import com.jscheng.spluto.markdown.view.part.PartType;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Chengjunsen on 2018/11/21
 */
public class CodePanelBuilder implements IPanelBuilder{
    @Override
    public List<Panel> build(Block block) {
        if (block.getType() != BlockType.CODE) {
            return null;
        }
        CodeBlock codeBlock= (CodeBlock)block;
        return parseCodeBlock(codeBlock);
    }

    private List<Panel> parseCodeBlock(CodeBlock codeBlock) {
        CodePanel panel = new CodePanel();
        ValuePart valuePart = codeBlock.getValuePart();
        Part part = new Part.Builder().
                setType(PartType.PART_CODE).
                setValue(valuePart.getValue()).
                setFontLevel(0).
                build();
        panel.setParts(Arrays.asList(part));
        return Arrays.asList((Panel)panel);
    }
}
