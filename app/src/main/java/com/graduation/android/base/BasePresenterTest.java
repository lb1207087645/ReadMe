package com.graduation.android.base;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import java.lang.ref.WeakReference;


/**
 * 基类Presenter
 *
 * @param <V>
 */
public class BasePresenterTest<V extends BaseViewTest> implements IPresenter<V> {

    private WeakReference<V> mView;//view改成弱引用


    @Override
    public void attachView(V mvpView) {
        this.mView = new WeakReference<>(mvpView);

    }

    @Override
    public void detachView() {//防止内存泄漏
        if (mView != null) {
            mView.clear();
            mView = null;
        }

    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {

    }


    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }


    public boolean isAttachView() {
        return mView != null && mView.get() != null;
    }


    /**
     * 获取目标View
     *
     * @return
     */
    public V getView() {
        return mView == null ? null : mView.get();
    }

    /**
     * 检查View 和presenter是否连接
     */
    public void checkViewAttach() {
        if (!isAttachView()) {
            throw new MvpViewNotAttachedException();
        }

    }

    /**
     * 自定义异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }

}
