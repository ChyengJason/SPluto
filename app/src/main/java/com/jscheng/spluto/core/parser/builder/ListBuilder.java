package com.jscheng.spluto.core.parser.builder;

import com.jscheng.spluto.core.bean.Block;
import com.jscheng.spluto.core.bean.ListBlock;
import com.jscheng.spluto.core.parser.ListType;
import com.jscheng.spluto.core.parser.MDToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 列表（有序列表，无序列表，引用）builder
 */
public class ListBuilder implements BlockBuilder {

	private String content;

	public ListBuilder(String content) {
		this.content = content;
	}

	/**
	 * 找出当前行的缩进空格数
	 * 
	 * @param line
	 * @return
	 */
	private String blankStrInHead(String line) {
		if (line == null) {
			return "";
		}
		String blankStr = " ";
		while (line.startsWith(blankStr)) {
			blankStr = blankStr + " ";
		}
		return blankStr.substring(1, blankStr.length());
	}

	@Override
	public Block build() {

		BufferedReader br = new BufferedReader(new StringReader(content));
		ListBlock block = new ListBlock();
		List<ListBlock> listData = new ArrayList<>();
		block.setListData(listData);
		try {
			String value = br.readLine();
			while (value != null) {
				ListBlock subBlock = new ListBlock();
				value = buildListBlock(subBlock, value, br, new ArrayList<TypeAndBlank>());
				listData.add(subBlock);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return block;
	}

	public class TypeAndBlank {

		private String blankStr;
		private ListType listType;

		public TypeAndBlank(String blankStr, ListType blockType) {
			super();
			this.blankStr = blankStr;
			this.listType = blockType;
		}

		public String getBlankStr() {
			return blankStr;
		}

		public ListType getBlockType() {
			return listType;
		}
	}

	private boolean jumpOut(List<TypeAndBlank> levels, TypeAndBlank currentLevels) {
		ArrayList<TypeAndBlank> childLevels = new ArrayList<ListBuilder.TypeAndBlank>();

		for (TypeAndBlank typeAndBlank : levels) {
			if (typeAndBlank.getBlankStr().equals(currentLevels.getBlankStr())
					&& typeAndBlank.getBlockType() == currentLevels.getBlockType()) {
				Iterator<TypeAndBlank> it = levels.iterator();
				for (int i = 0, l = childLevels.size(); i < l; i++) {
					it.next();
					it.remove();
				}
				return true;
			} else {
				childLevels.add(typeAndBlank);
			}
		}
		return false;
	}

	private String buildListBlock(ListBlock result, String value, BufferedReader br, List<TypeAndBlank> levels)
			throws IOException {

		String firstBlankStr = blankStrInHead(value);
		ListType firstCurrentType = listType(value);
		List<ListBlock> listData = new ArrayList<>();

		result.setListType(firstCurrentType);
		result.setListData(listData);
		levels.add(0, new TypeAndBlank(firstBlankStr, firstCurrentType));
		while (value != null) {
			ListType listType = listType(value);
			String blankStr = blankStrInHead(value);
			if (!blankStr.equals(firstBlankStr) || listType != firstCurrentType) { // 下一行格式跟当前行不一致，跳出while
				return value;
			}
			ListBlock block = new ListBlock();
			value = value.substring(firstBlankStr.length()); // 删除缩进空格

			int index = computeCharIndex(value, listType);
			if (index < 0) {
				value = br.readLine();
				continue;
			}
			block.setMdToken(value.substring(0, index));
			value = value.substring(index + 1).trim(); // 取出当前行取出列表符之后的真正数据
			
			if (value.equals("")) { // 空行直接忽略
				value = br.readLine();
				continue;
			}
			HeaderBuilder headerBuilder = new HeaderBuilder(value);
			if (headerBuilder.isRightType()) {
				block.setLineData(headerBuilder.build());
			} else {
				block.setLineData(new CommonTextBuilder(value).build());
			}
			listData.add(block);
			value = br.readLine();
			if (value == null) {
				break;
			}
			listType = listType(value);
			blankStr = blankStrInHead(value);
			if (value != null) {
				if (blankStr.equals(firstBlankStr) && listType != firstCurrentType) { // 同级别的列表，但是不同格式，跳出while
					return value;
				}
				if (!blankStr.equals(firstBlankStr) || listType != firstCurrentType) { // 下一行格式跟当前行不一致
					if (jumpOut(levels, new TypeAndBlank(blankStr, listType))) { // 检查是否为父级列表，是的话跳出while，否则作为子列表
						return value;
					}
					value = buildListBlock(block, value, br, levels);
				}
			}
		}
		return value;
	}

	private static ListType listType(String line) {
		if (line == null) {
			return null;
		}
		if (isTodoList(line)) {
			return ListType.TODO_LIST;
		}
		if (isUnTodoList(line)) {
		    return ListType.UNTODO_LIST;
        }
		if (isOrderedList(line)) {
			return ListType.ORDERED_LIST;
		}
		if (isUnOrderedList(line)) {
			return ListType.UNORDERED_LIST;
		}
		if (isQuote(line)) {
			return ListType.QUOTE;
		}
		return null;
	}

	private static int computeCharIndex(String line, ListType type) {
		if (type == ListType.ORDERED_LIST || type == ListType.UNORDERED_LIST) {
			return line.indexOf(" ");
		}
		if (type == ListType.QUOTE) {
			return line.indexOf(MDToken.QUOTE);
		}
		if (type == ListType.UNTODO_LIST) {
			int i = line.indexOf(MDToken.TODO_LIST_UNCHECKED);
			if (i != -1) {
				return i + 4;
			}
		}
		if (type == ListType.TODO_LIST) {
            int i = line.indexOf(MDToken.TODO_LIST_CHECKED);
            if (i != -1) {
                return i + 4;
            }
        }
		return -1;
	}

	public static boolean isList(String str) {
		return isUnTodoList(str) || isTodoList(str) || isOrderedList(str) || isUnOrderedList(str) || isQuote(str);
	}

	private static boolean isOrderedList(String str) {
		return Pattern.matches("^[\\d]+\\. [\\d\\D][^\n]*$", str.trim());
	}

	private static boolean isUnOrderedList(String str) {
		return str.trim().startsWith(MDToken.UNORDERED_LIST1) ||
				str.trim().startsWith(MDToken.UNORDERED_LIST2) ||
				str.trim().startsWith(MDToken.UNORDERED_LIST3);
	}

	private static boolean isQuote(String str) {
		return str.trim().startsWith(MDToken.QUOTE);
	}
	
	private static boolean isTodoList(String str) {
		return str.trim().startsWith(MDToken.TODO_LIST_CHECKED);
	}

	private static boolean isUnTodoList(String str) {
		return str.trim().startsWith(MDToken.TODO_LIST_UNCHECKED);
	}

	@Override
	public boolean isRightType() {
		return false;
	}

}
