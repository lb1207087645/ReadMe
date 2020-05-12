package com.graduation.android.readme.home;

import android.os.Bundle;
import android.view.View;

import com.graduation.android.readme.R;
import com.graduation.android.readme.base.mvp.BaseFragment;
import com.graduation.android.readme.base.mvp.BaseMvpFragment;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.base.network.ErrorEntity;


/**
 * 新闻页
 */
public class NewsFragment extends BaseFragment {

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void loadData() {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }


    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void dismissProgress() {
    }

    @Override
    public void showTip(String message) {
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showErr(ErrorEntity err) {

    }

    @Override
    public void showErrorView(String errorType) {

    }
}