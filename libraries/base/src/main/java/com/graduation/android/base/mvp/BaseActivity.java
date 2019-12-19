package com.graduation.android.base.mvp;


import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.graduation.android.base.R;
import com.graduation.android.base.CommonApplication;
import com.graduation.android.base.R;
import com.graduation.android.base.utils.StatusBarUtil;
import com.graduation.android.base.utils.ToolbarUtil;


/**
 * Activity基类的实现
 */
public abstract class BaseActivity<P extends IPresenter<V>, V extends BaseViewTest> extends BaseMvpActivity<P, V> {


    /**
     * 蓝光遮罩view
     */
    private int statusBarHeight;
    private int screenHeight;


    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.replaceable_color_navigation_bar_bg));
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        mToolbar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.common_title_bar_size)));

        mToolbar.setBackgroundResource(R.drawable.replaceable_drawable_toolbar_bg);//toolbar的背景图片

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mToolbar.setFitsSystemWindows(true);
        }
        mToolbar.setVisibility(View.VISIBLE);
        ((TextView) getToolbarLeftView()).setTextColor(ContextCompat.getColor(this, R.color.replaceable_color_title));
        ((TextView) getToolbarLeftView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        ((TextView) getToolbarTitleView()).setTextColor(ContextCompat.getColor(this, R.color.replaceable_color_title));
        ((TextView) getToolbarTitleView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        if (CommonApplication.getContext().getResources().getBoolean(R.bool.replaceable_bool_head_title_style_bold)) {//粗体显示
            ((TextView) getToolbarTitleView()).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            ((TextView) getToolbarTitleView()).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        ((TextView) getToolbarRightView()).setTextColor(ContextCompat.getColor(this, R.color.replaceable_color_title));
        ((TextView) getToolbarRightView()).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        //适配状态栏
        ToolbarUtil.setToolbar(mToolbar);
        setSupportActionBar(mToolbar);



    }
}
