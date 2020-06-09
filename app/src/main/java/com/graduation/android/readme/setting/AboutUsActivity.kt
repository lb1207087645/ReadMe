package com.graduation.android.readme.setting

import android.os.Bundle
import android.widget.TextView
import com.graduation.android.readme.R
import com.graduation.android.readme.base.mvp.BaseView
import com.graduation.android.readme.base.mvp.IPresenter
import com.graduation.android.readme.basemodule.BaseActivity


/**
 * 关于我们界面
 */
class AboutUsActivity : BaseActivity<IPresenter<BaseView>, BaseView>() {


    override fun bindEventListener() {


    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("关于我们")


    }

    override fun showProgress() {

    }

    override fun isActive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about_us
    }

    override fun loadData() {
    }

    override fun dismissProgress() {
    }


    override fun initPresenter() = null

}