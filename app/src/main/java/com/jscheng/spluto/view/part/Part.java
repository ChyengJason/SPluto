package com.jscheng.spluto.view.part;

import android.widget.Button;

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
    private int begin;
    private int end;

    private Part(Builder builder) {
        this.partType = builder.partType;
        this.bold = builder.bold;
        this.italic = builder.italic;
        this.strike = builder.strike;
        this.fontLevel = builder.fontLevel;
        this.underline = builder.underline;
        this.value = builder.value;
        this.url = builder.url;
        this.descripe = builder.descripe;
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

    public PartType getPartType() {
        return partType;
    }

    public String getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripe() {
        return descripe;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
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

    public static class Builder {
        private PartType partType;
        private boolean bold;
        private boolean italic;
        private boolean strike;
        private int fontLevel;
        private boolean underline;
        private String value;
        private String url;
        private String descripe;

        public Builder setBold(boolean bold) {
            this.bold = bold;
            return this;
        }

        public Builder setItalic(boolean italic) {
            this.italic = italic;
            return this;
        }

        public Builder setStrike(boolean strike) {
            this.strike = strike;
            return this;
        }

        public Builder setFontLevel(int fontLevel) {
            this.fontLevel = fontLevel;
            return this;
        }

        public Builder setDescripe(String descripe) {
            this.descripe = descripe;
            return this;
        }

        public Builder setUnderline(boolean underline) {
            this.underline = underline;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setType(PartType partType) {
            this.partType = partType;
            return this;
        }

        public Part build() {
            return new Part(this);
        }
    }
}
