package com.jscheng.spluto.core.parser.builder;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.HeadLineBlock;
import com.jscheng.spluto.core.bean.ValuePart;
import com.jscheng.spluto.core.parser.Analyzer;
import com.jscheng.spluto.core.parser.MDToken;

import java.util.List;

public class HeaderBuilder implements BlockBuilder {

	private String content;
	public HeaderBuilder(String content){
		this.content = content;
	}

	@Override
	public Block build() {
		content = content.trim();
		int i = content.lastIndexOf(MDToken.HEADLINE);
		content = content.substring(i+1).trim();
		return bulid(i);
	}
	
	public Block bulid(int level) {
		HeadLineBlock block = new HeadLineBlock();
		List<ValuePart> list = Analyzer.analyzeLineText(content);
		block.setValueParts(list);
		block.setLevel(level + 1);
		return block;
	}

	@Override
	public boolean isRightType() {
		return content.startsWith(MDToken.HEADLINE);
	}

	public static int isRightType(String nextLineStr){
		if(!nextLineStr.startsWith("-") && !nextLineStr.startsWith("=")){
			return 0;
		}
		String tmpS = nextLineStr.replaceAll("-", "").trim();
		if(tmpS.length()==0){
			return 2;
		}
		tmpS = nextLineStr.replaceAll("=", "").trim();
		if(tmpS.length()==0){
			return 1;
		}
		return 0;
	}
	
}
