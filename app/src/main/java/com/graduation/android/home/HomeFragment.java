package com.graduation.android.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.graduation.android.R;
import com.graduation.android.base.mvp.BaseMvpFragment;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.base.utils.L;
import com.graduation.android.bean.Translation1;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.home.mvp.HomeContract;
import com.graduation.android.home.mvp.HomePresenter;

import java.util.List;


/**
 * 测试的HomeFragment
 */
public class HomeFragment extends BaseMvpFragment<HomeContract.Presenter, HomeContract.View> implements HomeContract.View {


    private TextView tvHello;

    private static final String TAG = "HomeFragment";

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter(mActivity);
        // return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    public void loadData() {
//        mPresenter.getCallGet();
        mPresenter.getCallPost();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvHello = view.findViewById(R.id.tv_hello);
    }


    @Override
    public void loadCallGet(String value) {
//        tvHello.setText(out);
    }

    @Override
    public void loadCallPost(String value) {
        tvHello.setText(value);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showTip(String message) {
        showToast(message);
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void hideLoadingDialog() {

    }


    @Override
    public void showErr(ErrorEntity err) {
        L.d(TAG, err.errMsg);


    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showErrorView(String errorType) {

    }

    @Override
    public void showLoadingView() {

    }


    @Override
    public void showEmptyView(int imageId) {

    }

    @Override
    public void showContentView() {

    }

}
