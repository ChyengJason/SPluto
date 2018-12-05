package com.jscheng.spluto.markdown.core.parser.filter;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.TextOrBlock;
import com.jscheng.spluto.markdown.core.parser.builder.ListBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选出内容中的列表
 * @author yangyingqiang
 * 2017年7月14日 下午4:43:35
 */
public class ListFilter extends SyntaxFilter {

	public ListFilter(SyntaxFilter nextFilter) {
		super(nextFilter);
	}

	@Override
	public List<TextOrBlock> invoke(List<String> lines) {
		List<TextOrBlock> textOrBlocks = new ArrayList<TextOrBlock>();
		StringBuilder outerText = new StringBuilder();
		for (int idx = 0, si = lines.size(); idx < si; idx++) {
			String str = lines.get(idx);
			if (!isListLine(str)) {
				outerText.append(str + "\n");
				continue;
			}
			StringBuilder interText = new StringBuilder(str).append("\n");
			for (int idx1 = (idx + 1); idx1 < si; idx1++) {
				str = lines.get(idx1);
				if(!str.trim().equals("")){
					if(!isListLine(str)) {	//列表结束，跳出循环
						idx = idx1 - 1;		//外部循环开始读数据的地方
						break;
					}else{
						interText.append(str).append("\n");
					}
				}
				if(idx1 == (si - 1)) {	//列表已无可读数据，通知外部循环不需要再继续读取数据
					idx = idx1;
				}
			}
			
			if (!outerText.toString().equals("")) {
				textOrBlocks.add(new TextOrBlock(outerText.toString()));
				outerText = new StringBuilder();
			}
			textOrBlocks.add(new TextOrBlock(buildBlock(interText.toString())));
		}
		if (!outerText.toString().equals("")) {
			textOrBlocks.add(new TextOrBlock(outerText.toString()));
		}
		return textOrBlocks;
	}

	/**
	 * 是否是符合规则的列表
	 * @param target 检查目标
	 * @return 布尔值
	 */
	protected boolean isListLine(String target) {
		return ListBuilder.isList(target);
	}
	
	/**
	 * 创建对应的block
	 * @param source 元数据
	 * @return 创建结果
	 */
	protected Block buildBlock(String source) {
		return new ListBuilder(source).build();
	}
	
}
