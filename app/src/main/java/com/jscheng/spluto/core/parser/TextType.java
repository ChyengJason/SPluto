package com.jscheng.spluto.core.parser;

/**
 * Created by chengjunsen on 2018/11/2.
 */
public enum TextType {
    IMG,
    BOLD_WORD,
    ITALIC_WORD,
    STRIKE_WORD,
    CODE_WORD,
    LINK,
    NONE;

    public static TextType convert(String mdToken) {
        if (mdToken.equals(MDToken.BOLD_WORD1) || mdToken.equals(MDToken.BOLD_WORD2)) {
            return TextType.BOLD_WORD;
        } else if (mdToken.equals(MDToken.IMG)) {
            return TextType.IMG;
        } else if (mdToken.equals(MDToken.ITALIC_WORD) || mdToken.equals(MDToken.ITALIC_WORD_2)) {
            return TextType.ITALIC_WORD;
        } else if (mdToken.equals(MDToken.STRIKE_WORD)) {
            return TextType.STRIKE_WORD;
        } else if (mdToken.equals(MDToken.CODE_WORD)) {
            return TextType.CODE_WORD;
        } else if (mdToken.equals(MDToken.LINK)) {
            return TextType.LINK;
        } else {
            return TextType.NONE;
        }
    }
}
