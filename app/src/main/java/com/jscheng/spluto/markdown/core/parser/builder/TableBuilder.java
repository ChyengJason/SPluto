package com.jscheng.spluto.markdown.core.parser.builder;

import com.jscheng.spluto.markdown.core.bean.Block;
import com.jscheng.spluto.markdown.core.bean.CommonTextBlock;
import com.jscheng.spluto.markdown.core.bean.TableBlock;
import com.jscheng.spluto.markdown.core.parser.CellAlign;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableBuilder implements BlockBuilder{

	private List<List<String>> data;
	private Map<Integer, CellAlign> cellAligns;
	
	public TableBuilder(List<List<String>> data, Map<Integer, CellAlign> cellAligns) {
		super();
		this.data = data;
		this.cellAligns = cellAligns;
	}

	@Override
	public Block build() {
		TableBlock block = new TableBlock();
		block.setTableData(convertData(data, cellAligns));
		return block;
	}

	private List<List<Block>> convertData(List<List<String>> data, Map<Integer, CellAlign> cellAligns) {
		List<List<Block>> result = new ArrayList<List<Block>>();
		for (List<String> list : data) {
			List<Block> blocks = new ArrayList<Block>();
			for (int i = 0, s = list.size(); i < s; i ++) {
				String str = list.get(i);
				CommonTextBlock block = new CommonTextBuilder(str).build();
				CellAlign align = cellAligns.get(i);
				block.setAlign(align == null ? CellAlign.NONE : align);
				blocks.add(block);
			}
			result.add(blocks);
		}
		return result;
	}

	@Override
	public boolean isRightType() {
		return false;
	}

}
