package com.jscheng.spluto.view.parser;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.CodeBlock;
import com.jscheng.spluto.core.bean.ValuePart;
import com.jscheng.spluto.core.parser.BlockType;
import com.jscheng.spluto.view.panel.CodePanel;
import com.jscheng.spluto.view.Panel;

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
        panel.setValue(valuePart.getValue());
        return Arrays.asList((Panel)panel);
    }
}
