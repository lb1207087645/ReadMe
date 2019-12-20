package com.graduation.android.test;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.graduation.android.R;
import com.graduation.android.base.mvp.BaseMvpFragment;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.base.utils.L;
import com.graduation.android.base.widget.state.CommonState;
import com.graduation.android.home.mvp.HomeContractTest;
import com.graduation.android.share.utils.DialogUtil;
import com.graduation.android.share.utils.ShareSdkUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;


/**
 * 我的页面Fragment,Java版
 */
public class Test1Fragment extends BaseMvpFragment implements PlatformActionListener {


    private static final String TAG = "Test1Fragment";


    @Override
    protected HomeContractTest.Presenter initPresenter() {
        return null;
        // return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_test;
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        View test = view.findViewById(R.id.common_test_view);
        final CommonState commonStateView = new CommonState(test);
        commonStateView.showLoadingView();
    }




    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress() {
//        srl.post(new Runnable() {
//            @Override
//            public void run() {
//                srl.setRefreshing(true);
//            }
//        });
    }

    @Override
    public void dismissProgress() {
//        srl.setRefreshing(false);
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

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
