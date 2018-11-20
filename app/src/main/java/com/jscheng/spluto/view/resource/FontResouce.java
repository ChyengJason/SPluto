package com.jscheng.spluto.view.resource;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.jscheng.spluto.util.DipUtil;
import com.jscheng.spluto.util.FontUtil;

public class FontResouce {
    private static final int FONT_DP_IMAGE = 15;
    private static int FONT_PX_IMAGE;

    private static final int FONT_DP_CODE_PANEL = 15;
    private static int FONT_PX_CODE_PANEL;

    private static final int FONT_DP_LEVEL_0 = 18;
    private static int FONT_PX_LEVEL_0;
    private static int FONT_PX_HEIGHT_0;

    private static final int FONT_DP_LEVEL_1 = 22;
    private static int FONT_PX_LEVEL_1;
    private static int FONT_PX_HEIGHT_1;

    private static final int FONT_DP_LEVEL_2 = 21;
    private static int FONT_PX_LEVEL_2;
    private static int FONT_PX_HEIGHT_2;

    private static final int FONT_DP_LEVEL_3 = 20;
    private static int FONT_PX_LEVEL_3;
    private static int FONT_PX_HEIGHT_3;

    private static final int FONT_DP_LEVEL_4 = 19;
    private static int FONT_PX_LEVEL_4;
    private static int FONT_PX_HEIGHT_4;

    public static void register(Context context) {
        FONT_PX_IMAGE = DipUtil.dp2px(context, FONT_DP_IMAGE);
        FONT_PX_CODE_PANEL = DipUtil.dp2px(context, FONT_DP_CODE_PANEL);
        FONT_PX_LEVEL_0 = DipUtil.dp2px(context, FONT_DP_LEVEL_0);
        FONT_PX_HEIGHT_0 = (int)FontUtil.getFontHeight(FONT_PX_LEVEL_0);
        FONT_PX_LEVEL_1 = DipUtil.dp2px(context, FONT_DP_LEVEL_1);
        FONT_PX_HEIGHT_1 = (int)FontUtil.getFontHeight(FONT_PX_LEVEL_1);
        FONT_PX_LEVEL_2 = DipUtil.dp2px(context, FONT_DP_LEVEL_2);
        FONT_PX_HEIGHT_2 = (int)FontUtil.getFontHeight(FONT_PX_LEVEL_2);
        FONT_PX_LEVEL_3 = DipUtil.dp2px(context, FONT_DP_LEVEL_3);
        FONT_PX_HEIGHT_3 = (int)FontUtil.getFontHeight(FONT_PX_LEVEL_3);
        FONT_PX_LEVEL_4 = DipUtil.dp2px(context, FONT_DP_LEVEL_4);
        FONT_PX_HEIGHT_4 = (int)FontUtil.getFontHeight(FONT_PX_LEVEL_4);
    }

    public static void unRegister() {

    }

    public static int getFontSize(int fontLevel) {
       if (fontLevel <= 0) {
           return FONT_PX_LEVEL_0;
       }
       if (fontLevel == 1) {
           return FONT_PX_LEVEL_1;
       }
       if (fontLevel == 2) {
           return FONT_PX_LEVEL_2;
       }
       if (fontLevel == 3) {
           return FONT_PX_LEVEL_3;
       }
       if (fontLevel >= 4) {
           return FONT_PX_LEVEL_4;
       }
        return FONT_PX_LEVEL_0;
    }

    public static int getFontHeight(int fontLevel) {
        if (fontLevel <= 0) {
            return FONT_PX_HEIGHT_0;
        }
        if (fontLevel == 1) {
            return FONT_PX_HEIGHT_1;
        }
        if (fontLevel == 2) {
            return FONT_PX_HEIGHT_2;
        }
        if (fontLevel == 3) {
            return FONT_PX_HEIGHT_3;
        }
        if (fontLevel >= 4) {
            return FONT_PX_HEIGHT_4;
        }
        return FONT_PX_LEVEL_0;
    }

    public static int getImageFontSize() {
        return FONT_PX_IMAGE;
    }

    public static int getCodePanelFontSize() {
        return FONT_PX_CODE_PANEL;
    }

}
