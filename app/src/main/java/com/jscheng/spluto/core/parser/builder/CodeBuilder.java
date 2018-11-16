package com.jscheng.spluto.core.parser.builder;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.CodeBlock;
import com.jscheng.spluto.core.bean.ValuePart;

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
