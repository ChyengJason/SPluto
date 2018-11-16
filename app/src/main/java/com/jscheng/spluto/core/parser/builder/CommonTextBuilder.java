package com.jscheng.spluto.core.parser.builder;

import com.jscheng.spluto.core.bean.CommonTextBlock;
import com.jscheng.spluto.core.bean.ValuePart;
import com.jscheng.spluto.core.parser.Analyzer;
import com.jscheng.spluto.core.parser.tool.FliterUtil;

import java.util.ArrayList;
import java.util.List;

public class CommonTextBuilder implements BlockBuilder{

	private String content;
	public CommonTextBuilder(String content){
		this.content = content;
	}

	@Override
	public CommonTextBlock build() {
		CommonTextBlock block = new CommonTextBlock();
		List<String> lines = FliterUtil.read2List(content);
		List<ValuePart> valueParts = new ArrayList<ValuePart>();
		for (String line : lines) {
			valueParts.addAll(Analyzer.analyzeLineText(line));
		}
		block.setValueParts(valueParts);
		
		return block;
	}

	@Override
	public boolean isRightType() {
		return true;
	}

}
