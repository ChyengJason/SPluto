package com.jscheng.spluto.view.resource;

import android.content.Context;

import com.jscheng.spluto.util.DipUtil;

public class FontResouce {
    private static final int FONT_DP_IMAGE = 16;
    private static final int FONT_DP_LEVEL_0 = 18;
    private static final int FONT_DP_LEVEL_1 = 22;
    private static final int FONT_DP_LEVEL_2 = 21;
    private static final int FONT_DP_LEVEL_3 = 20;
    private static final int FONT_DP_LEVEL_4 = 19;

    private static int FONT_PX_IMAGE;
    private static int FONT_PX_LEVEL_0;
    private static int FONT_PX_LEVEL_1;
    private static int FONT_PX_LEVEL_2;
    private static int FONT_PX_LEVEL_3;
    private static int FONT_PX_LEVEL_4;

    public static void register(Context context) {
        FONT_PX_IMAGE = DipUtil.dp2px(context, FONT_DP_IMAGE);
        FONT_PX_LEVEL_0 = DipUtil.dp2px(context, FONT_DP_LEVEL_0);
        FONT_PX_LEVEL_1 = DipUtil.dp2px(context, FONT_DP_LEVEL_1);
        FONT_PX_LEVEL_2 = DipUtil.dp2px(context, FONT_DP_LEVEL_2);
        FONT_PX_LEVEL_3 = DipUtil.dp2px(context, FONT_DP_LEVEL_3);
        FONT_PX_LEVEL_4 = DipUtil.dp2px(context, FONT_DP_LEVEL_4);
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

    public static int getImageFontSize() {
        return FONT_PX_IMAGE;
    }
}
