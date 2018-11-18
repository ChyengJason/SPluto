package com.jscheng.spluto.view.resource;

import android.content.Context;

import com.jscheng.spluto.util.DipUtil;

public class PaddingResouce {
    private static final int LEFT_PADDING_DP = 15;
    private static final int RIGHT_PADDING_DP = 15;
    private static final int TOP_PADDING_DP = 15;
    private static final int BOTTOM_PADDING_DP = 15;
    private static final int LINE_SPACING_DP = 5;

    private static int LEFT_PADDING_PX;
    private static int RIGHT_PADDING_PX;
    private static int TOP_PADDING_PX;
    private static int BOTTOM_PADDING_PX;
    private static int LINE_SPACING_PX;

    public static void register(Context context) {
        LEFT_PADDING_PX = DipUtil.dp2px(context, LEFT_PADDING_DP);
        RIGHT_PADDING_PX = DipUtil.dp2px(context, RIGHT_PADDING_DP);
        TOP_PADDING_PX = DipUtil.dp2px(context, TOP_PADDING_DP);
        BOTTOM_PADDING_PX = DipUtil.dp2px(context, BOTTOM_PADDING_DP);
        LINE_SPACING_PX =  DipUtil.dp2px(context, LINE_SPACING_DP);
    }

    public static int getLeftPaddingPx() {
        return LEFT_PADDING_PX;
    }

    public static int getRightPaddingPx() {
        return RIGHT_PADDING_PX;
    }

    public static int getTopPaddingPx() {
        return TOP_PADDING_PX;
    }

    public static int getBottomPaddingPx() {
        return BOTTOM_PADDING_PX;
    }

    public static float getLineSpacingPx() {
        return LINE_SPACING_PX;
    }
}
