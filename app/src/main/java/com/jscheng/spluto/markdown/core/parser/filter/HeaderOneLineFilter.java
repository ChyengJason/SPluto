package com.jscheng.spluto.markdown.core.parser.filter;

import com.jscheng.spluto.markdown.core.bean.TextOrBlock;
import com.jscheng.spluto.markdown.core.parser.MDToken;
import com.jscheng.spluto.markdown.core.parser.builder.HeaderBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选出内容中的标题格式（同一行的，比如：
 * ### 标题
 * ）
 * @author yangyingqiang
 * 2017年7月14日 下午3:41:26
 */
public class HeaderOneLineFilter extends SyntaxFilter{

	public HeaderOneLineFilter(SyntaxFilter nextFilter) {
		super(nextFilter);
	}

	@Override
	public List<TextOrBlock> invoke(List<String> lines) {
		List<TextOrBlock> textOrBlocks = new ArrayList<TextOrBlock>();
		StringBuilder outerText = new StringBuilder();
		for (int idx = 0, si = lines.size(); idx < si; idx++) {
			String str = lines.get(idx);
			if (str.trim().startsWith(MDToken.HEADLINE)) {
				if (!outerText.toString().equals("")) {
					textOrBlocks.add(new TextOrBlock(outerText.toString()));
					outerText = new StringBuilder();
				}
				textOrBlocks.add(new TextOrBlock(new HeaderBuilder(str).build()));
			} else {
				outerText.append(str).append("\n");
			}
		}
		if (!outerText.toString().equals("")) {
			textOrBlocks.add(new TextOrBlock(outerText.toString()));
		}
		return textOrBlocks;
	}

}
