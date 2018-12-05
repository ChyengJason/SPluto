package com.jscheng.spluto.markdown.core.parser.filter;

import com.jscheng.spluto.markdown.core.bean.TextOrBlock;
import com.jscheng.spluto.markdown.core.parser.builder.HeaderBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选出内容中的标题格式（两行的，比如：
 * 标题
 * ====
 * ）
 * @author yangyingqiang
 */
public class HeaderNextLineFilter extends SyntaxFilter {

	public HeaderNextLineFilter(SyntaxFilter nextFilter) {
		super(nextFilter);
	}

	@Override
	public List<TextOrBlock> invoke(List<String> lines) {
		List<TextOrBlock> textOrBlocks = new ArrayList<TextOrBlock>();
		StringBuilder outerText = new StringBuilder();
		for (int idx = 0, si = lines.size(); idx < si; idx++) {
			String str = lines.get(idx);
			if ((idx + 1) < si) {
				String nextStr = lines.get(idx + 1);
				int lvl = HeaderBuilder.isRightType(nextStr);
				if (lvl > 0) {
					if (!outerText.toString().equals("")) {
						textOrBlocks.add(new TextOrBlock(outerText.toString()));
						outerText = new StringBuilder();
					}
					textOrBlocks.add(new TextOrBlock(new HeaderBuilder(str).bulid(lvl)));
					idx++;
				} else {
					outerText.append(str).append("\n");
				}
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
