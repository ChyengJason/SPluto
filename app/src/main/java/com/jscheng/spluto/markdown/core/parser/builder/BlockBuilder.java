package com.jscheng.spluto.markdown.core.parser.builder;

import com.jscheng.spluto.markdown.core.bean.Block;

public interface BlockBuilder {

	/**
	 * 创建语法块
	 * @return 语法块
	 */
	Block build();
	
	/**
	 * 检查内容是否属于当前语法块
	 * @return true：是，false：否
	 */
	boolean isRightType();
}
