package com.jscheng.spluto.core.parser.tool;

import java.util.LinkedHashMap;
import java.util.Map;
import static com.jscheng.spluto.core.parser.MDToken.*;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public class SymbolUtil {
    public static final Map<String, String> PLACEHOLDER_MAP = new LinkedHashMap<String, String>() { // 需要显示的特殊符号的占位符
        private static final long serialVersionUID = 5649442662460683378L;
        {
            put("\\\\", "$BACKSLASH");
            put("\\" + IMG, "$IMG");
            put("\\" + LINK, "$LINK");
            // put("\\" + BOLD_WORD, "$BOLDWORD"); //因为有 ITALIC_WORD_2 的存在，所以不需要这个
            put("\\" + ITALIC_WORD, "$ITALICWORD");
            put("\\" + ITALIC_WORD_2, "$2ITALICWORD");
            put("\\" + STRIKE_WORD, "$STRIKEWORD");
            put("\\" + CODE_WORD, "$CODEWORD");
            put("\\", "");
        }
    };

    /**
     * 还原value中的特殊符号占位符
     * @param value 操作对象
     * @return 还原结果
     */
    public static String revertValue(String value) {
        for (Map.Entry<String, String> entry : PLACEHOLDER_MAP.entrySet()) {
            String tmpValue = entry.getKey().substring(1);	//需要去除第一个反斜杠
            value = value.replace(entry.getValue(), tmpValue);
        }
        return value;
    }

    /**
     * 把需要显示的特殊符号转换为占位符
     * @param value 操作对象
     * @return 转换结果
     */
    public static String convertValue(String value) {
        for (Map.Entry<String, String> entry : PLACEHOLDER_MAP.entrySet()) {
            value = value.replace(entry.getKey(), entry.getValue());
        }
        return value;
    }
}
