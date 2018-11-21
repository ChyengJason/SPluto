package com.jscheng.spluto.view.part;

import com.jscheng.spluto.view.part.PartType;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public class Part {
    private PartType partType;
    private boolean bold;
    private boolean italic;
    private boolean strike;
    private int fontLevel;
    private boolean underline;
    private String value;
    private String url;
    private String descripe;

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setFontLevel(int fontLevel) {
        this.fontLevel = fontLevel;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isStrike() {
        return strike;
    }

    public boolean isUnderline() {
        return underline;
    }

    public int getFontLevel() {
        return fontLevel;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    public String getText(){
        switch (partType) {
            case PART_CODE:
                return value;
            case PART_LINK:
                return descripe;
            case PART_IMAGE:
                return descripe;
            case PART_TEXT:
                return value;
            default:
                return value;
        }
    }
}
