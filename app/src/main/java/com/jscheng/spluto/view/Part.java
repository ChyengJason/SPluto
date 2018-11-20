package com.jscheng.spluto.view;

import com.jscheng.spluto.view.part.PartType;

/**
 * Created by chengjunsen on 2018/11/15.
 */
public abstract class Part {
    private int begin, end;
    private boolean bold;
    private boolean italic;
    private boolean strike;
    private int fontLevel;
    private boolean underline;
    private PartType partType;

    public Part(PartType partType) {
        this.partType = partType;
    }

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

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public abstract String getText();

    public PartType getPartType() {
        return partType;
    }

}
