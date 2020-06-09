package com.graduation.android.readme.setting
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.fjlssy.lszypatient.setting.mvp.SettingPresenter
import com.graduation.android.readme.R
import com.graduation.android.readme.base.utils.ToastUtils
import com.graduation.android.readme.basemodule.BaseActivity
import com.graduation.android.readme.bean.User
import kotlinx.android.synthetic.main.activity_modify_pwd.*

/**
 * 修改登录密码
 */
class ModifyPwdActivity : BaseActivity<SettingContract.Presenter, SettingContract.View>(),
    SettingContract.View, View.OnClickListener {


    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_change -> {//确定
                val oldPwd = ed_old_password.text.toString()
                if (TextUtils.isEmpty(oldPwd)) {
                    ToastUtils.showToast(this, "请输入旧密码")
                    return
                }

                val pwd = ed_new_password.getText().toString()

                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showToast(this, "请输入新密码")
                    return
                }


                val confirmPwd = ed_new_password_confirm.getText().toString()
                if (TextUtils.isEmpty(confirmPwd)) {
                    ToastUtils.showToast(this, "请再次输入新密码")
                    return
                }

                if (!TextUtils.equals(pwd, confirmPwd)) {
                    ToastUtils.showToast(this, "两次密码输入不一致")
                    return
                }
                mPresenter.modifyPwd(oldPwd, pwd)
            }

        }
    }


    override fun bindEventListener() {
        tv_change?.setOnClickListener(this)
    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("修改登录密码")

    }

    override fun showProgress() {

    }

    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_modify_pwd
    }

    override fun loadData() {
    }

    override fun dismissProgress() {
    }

    override fun modifyPwdSuccess() {
        ToastUtils.showToast(this, "修改密码成功")
    }

    override fun modifyPwdFailure(errMsg:String) {
        ToastUtils.showToast(this, "修改密码失败"+errMsg)
    }

    override fun modifyNickNameSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifynickNameFailure(errMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserSexSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserSexFailure(mssage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




    override fun isActive(): Boolean {
        return false
    }

    override fun initPresenter(): SettingContract.Presenter {
        return SettingPresenter()
    }
}