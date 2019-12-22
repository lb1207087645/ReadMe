package com.graduation.android.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.graduation.android.R
import com.graduation.android.base.BaseActivity
import com.graduation.android.base.mvp.BaseView
import com.graduation.android.base.mvp.IPresenter

/**
 * 启动页
 */
@SuppressLint("Registered")
class SplashActivity2 : BaseActivity<IPresenter<BaseView>, BaseView>() {


    override fun initPresenter() = null


    override fun isActive(): Boolean {
        return false
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTip(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {//启动页
        return R.layout.activity_splash
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}