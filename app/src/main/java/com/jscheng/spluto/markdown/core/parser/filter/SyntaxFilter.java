package com.jscheng.spluto.markdown.core.parser.filter;
import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.TextOrBlock;
import com.jscheng.spluto.markdown.core.parser.builder.CommonTextBuilder;
import com.jscheng.spluto.markdown.core.parser.tool.FliterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * markdown语法过滤器
 * 
 * @author yangyingqiang
 * 2017年7月14日 上午11:38:51
 */
public abstract class SyntaxFilter {

	private SyntaxFilter nextFilter = null;

	public abstract List<TextOrBlock> invoke(List<String> lines);

	public SyntaxFilter(SyntaxFilter nextFilter) {
		super();
		this.nextFilter = nextFilter;
	}

	public List<Block> call(String content) {
		List<TextOrBlock> textOrBlocks = invoke(FliterUtil.read2List(content));
		List<Block> result = new ArrayList<Block>();
		for (TextOrBlock textOrBlock : textOrBlocks) {
			if (textOrBlock.isBlock()) {
				result.add(textOrBlock.getBlock());
				continue;
			}
			String text = textOrBlock.getText();
			if (nextFilter != null) {
				result.addAll(nextFilter.call(text));
			} else {
				result.addAll(buildCommonTextBlock(text));
			}
		}
		return result;
	}
	
	/**
	 * 将内容当成普通的文本处理
	 * @param text 内容
	 * @return 处理结果
	 */
	private List<Block> buildCommonTextBlock(String text) {
		List<Block> result = new ArrayList<Block>();
		List<String> lines = FliterUtil.read2List(text);
		StringBuilder commonText = new StringBuilder();
		for (int idx = 0, l = lines.size(); idx < l; idx++) {
			String line = lines.get(idx);
			if (idx + 1 < l) {
				String nextLine = lines.get(idx + 1);
				commonText.append(line + "\n");
				if (nextLine.trim().equals("")) {
					if(!commonText.toString().equals("")) {
						result.add(new CommonTextBuilder(commonText.toString()).build());
						commonText = new StringBuilder();
						idx ++;
					}
				}
			} else {
				commonText.append(line + "\n");
			}
		}
		if(!commonText.toString().equals("")) {
			result.add(new CommonTextBuilder(commonText.toString()).build());
		}
		
		return result;
	}
}
