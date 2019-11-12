package com.graduation.android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.graduation.android.utils.ToastUtils;


/**
 * mvp版的BaseFragment 基类
 *
 * @param <P>
 * @param <V>
 */
public abstract class BaseMvpFragment<P extends IPresenter<V>, V extends BaseViewTest> extends Fragment implements BaseViewTest {

    protected P mPresenter;//p层


    protected Activity mActivity;//Fragment对应的Activity

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        // 这里直接inflate出fragment布局
        rootView = inflater.inflate(getLayoutId(), container, false);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//presenter与view连接
        }


        initView(rootView);
        loadData();
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 子类去初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();


    /**
     * 获取布局文件的ID
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 加载数据
     */
    protected abstract void loadData();


    /**
     * 子类的初始化操作
     *
     * @param view
     */
    protected abstract void initView(View view);


    @Override
    public void onDestroyView() {
        if (mPresenter != null) {//防止内存泄漏
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtils.showToast(mActivity, msg, Toast.LENGTH_SHORT);
    }
}
