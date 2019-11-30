package com.graduation.android.utils;

import android.view.View;

import com.graduation.android.base.BaseApplication;
import com.graduation.android.base.utils.DensityUtils;

public class ToolbarUtil {


    public static void setToolbar(View toolbar) {
        if (toolbar == null) {
            return;
        }
        int statusBarHeight = getStatusBarHeight();

        toolbar.getLayoutParams().height = statusBarHeight + DensityUtils.dp2px(50);
        toolbar.setPadding(toolbar.getLeft(), statusBarHeight, toolbar.getRight(), toolbar.getBottom());
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = BaseApplication.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = BaseApplication.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
