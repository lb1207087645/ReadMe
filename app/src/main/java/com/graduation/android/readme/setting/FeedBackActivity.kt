package com.graduation.android.readme.setting

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.fjlssy.lszypatient.setting.mvp.SettingPresenter
import com.graduation.android.readme.R
import com.graduation.android.readme.base.BaseApplication
import com.graduation.android.readme.base.utils.ToastUtils
import com.graduation.android.readme.basemodule.BaseActivity


/**
 * 意见反馈界面
 */
class FeedBackActivity :  BaseActivity<SettingContract.Presenter, SettingContract.View>(),
        SettingContract.View, View.OnClickListener {
    override fun updateUserSexFailure(mssage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserSexSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifynickNameFailure(errMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifyNickNameSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifyPwdFailure(errMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifyPwdSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun mfeedbackFailure(errMsg: String) {
        ToastUtils.showToast(BaseApplication.getContext(), "创建数据失败：" +errMsg)
    }

    override fun feedbackSuccess() {
        ToastUtils.showToast(BaseApplication.getContext(), "添加数据成功")
        finish()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun isActive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private var edt_feed_back: EditText? = null

    override fun bindEventListener() {


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.common_toolbar_tv_right -> {//提交
                mPresenter?.feedback(edt_feed_back!!.text.toString());
            }

        }
    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("意见反馈")
        var tvRight: TextView = toolbarRightView as TextView
        tvRight.setTextColor(resources.getColor(R.color.black))
        tvRight.setText("提交")
        tvRight.setOnClickListener(this)
        edt_feed_back = findViewById<EditText>(R.id.edt_feed_back)
    }

    override fun showProgress() {

    }


    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_feed_back
    }

    override fun loadData() {
    }

    override fun dismissProgress() {
    }



    override fun initPresenter(): SettingContract.Presenter {
        return SettingPresenter()
    }



}