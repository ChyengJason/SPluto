package com.jscheng.spluto.view.resource;

import android.content.Context;

import com.jscheng.spluto.util.DipUtil;

public class PaddingResouce {
    private static final int LEFT_PADDING_DP = 15;
    private static int LEFT_PADDING_PX;

    private static final int RIGHT_PADDING_DP = 15;
    private static int RIGHT_PADDING_PX;

    private static final int TOP_PADDING_DP = 15;
    private static int TOP_PADDING_PX;

    private static final int BOTTOM_PADDING_DP = 15;
    private static int BOTTOM_PADDING_PX;

    private static final int PANNEL_SPACING_DP = 6;
    private static int LINE_SPACING_PX;

    private static final int LINE_SPACING_DP = 8;
    private static int PANNEL_SPACING_PX;

    private static final int LIST_MIDDLE_SPACING_DP = 6;
    private static int LIST_MIDDLE_SPACING_PX;

    private static final int CHECKBOX_TOP_PADDING_DP = 6;
    private static int CHECKBOX_TOP_PADDING_PX;

    private static final int QUOTELIST_HEAD_WIDTH_DP = 4;
    private static int QUOTELIST_HEAD_WIDTH_PX;

    private static final int CODE_PANEL_LEFT_RIGHT_PADDING_DP = 5;
    private static int CODE_PANEL_LEFT_RIGHT_PADDING_PX;

    private static final int CODE_PANEL_TOP_BOTTOM_PADDING_DP = 10;
    private static int CODE_PANEL_TOP_BOTTOM_PADDING_PX;

    public static void register(Context context) {
        LEFT_PADDING_PX = DipUtil.dp2px(context, LEFT_PADDING_DP);
        RIGHT_PADDING_PX = DipUtil.dp2px(context, RIGHT_PADDING_DP);
        TOP_PADDING_PX = DipUtil.dp2px(context, TOP_PADDING_DP);
        BOTTOM_PADDING_PX = DipUtil.dp2px(context, BOTTOM_PADDING_DP);
        LINE_SPACING_PX =  DipUtil.dp2px(context, LINE_SPACING_DP);
        PANNEL_SPACING_PX = DipUtil.dp2px(context, PANNEL_SPACING_DP);
        LIST_MIDDLE_SPACING_PX = DipUtil.dp2px(context, LIST_MIDDLE_SPACING_DP);
        CHECKBOX_TOP_PADDING_PX = DipUtil.dp2px(context, CHECKBOX_TOP_PADDING_DP);
        QUOTELIST_HEAD_WIDTH_PX = DipUtil.dp2px(context, QUOTELIST_HEAD_WIDTH_DP);
        CODE_PANEL_LEFT_RIGHT_PADDING_PX = DipUtil.dp2px(context, CODE_PANEL_LEFT_RIGHT_PADDING_DP);
        CODE_PANEL_TOP_BOTTOM_PADDING_PX = DipUtil.dp2px(context, CODE_PANEL_TOP_BOTTOM_PADDING_DP);
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

    public static int getLineSpacingPx() {
        return LINE_SPACING_PX;
    }

    public static int getPannelSpacingPx() {
        return PANNEL_SPACING_PX;
    }

    public static int getListMiddleSpacingPx() {
        return LIST_MIDDLE_SPACING_PX;
    }

    public static int getCheckBoxTopPaddingPx() {
        return CHECKBOX_TOP_PADDING_PX;
    }

    public static int getQuoteListHeadWidth() {
        return QUOTELIST_HEAD_WIDTH_PX;
    }

    public static int getCodePanelLeftRightPadding() {
        return CODE_PANEL_LEFT_RIGHT_PADDING_PX;
    }

    public static int getCodePanelTopBottomPadding() {
        return CODE_PANEL_TOP_BOTTOM_PADDING_PX;
    }
}
