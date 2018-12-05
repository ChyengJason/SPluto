package com.jscheng.spluto.markdown.core.parser.builder;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.CodeBlock;
import com.jscheng.spluto.markdown.core.bean.ValuePart;

public class CodeBuilder implements BlockBuilder{

	private String content;
	public CodeBuilder(String content){
		this.content = content;
	}

	@Override
	public Block build() {
		CodeBlock block = new CodeBlock();
		block.setValuePart(new ValuePart(content));
		return block;
	}

	@Override
	public boolean isRightType() {
		return false;
	}

}
