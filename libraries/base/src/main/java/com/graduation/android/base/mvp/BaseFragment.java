package com.graduation.android.base.mvp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.graduation.android.base.diff.BaseViewCallback;
import com.graduation.android.base.diff.ModuleBaseDiffHelper;
import com.graduation.android.base.network.ErrorEntity;

/**
 * Fragment基类
 */

public abstract class BaseFragment<P extends IPresenter<V>, V extends BaseViewTest> extends BaseMvpFragment<P, V> {


    /**
     * 不同加载状态的布局
     */
    @Override
    public void showLoadingView() {
        ModuleBaseDiffHelper.getInstance().showLoadingView(mStateView);
    }

    @Override
    public void showErrorView() {
        ModuleBaseDiffHelper.getInstance().showErrorView(mStateView, "", new BaseViewCallback() {
            @Override
            public void viewLoadData() {
                loadData();
            }
        });
    }

    @Override
    public void showErrorView(String errorType) {
        ModuleBaseDiffHelper.getInstance().showErrorView(mStateView, errorType, new BaseViewCallback() {
            @Override
            public void viewLoadData() {
                loadData();
            }
        });
    }


    public void showEmptyViewDefault(String text, boolean isShowTopCurve) {
        ModuleBaseDiffHelper.getInstance().showEmptyViewDefault(mStateView, text, isShowTopCurve);
    }

    @Override
    public void showErr(ErrorEntity err) {

    }
}