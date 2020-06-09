package com.graduation.android.readme.setting

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import cn.bmob.v3.BmobUser
import com.fjlssy.lszypatient.setting.mvp.SettingPresenter
import com.graduation.android.readme.R
import com.graduation.android.readme.base.eventbus.AppEventType
import com.graduation.android.readme.base.utils.ToastUtils
import com.graduation.android.readme.basemodule.BaseActivity
import com.graduation.android.readme.bean.User
import kotlinx.android.synthetic.main.activity_modify_nickname.*
import kotlinx.android.synthetic.main.activity_person_info.*
import org.greenrobot.eventbus.EventBus

/**
 * 修改昵称
 */
class ModifyNickNameActivity : BaseActivity<SettingContract.Presenter, SettingContract.View>(),
    SettingContract.View, View.OnClickListener {


    override fun onClick(v: View) {
        when (v.id) {
            R.id.common_toolbar_tv_right -> {//修改
                val edtNickName = edt_nick_name.text.toString()
                if (TextUtils.isEmpty(edtNickName)) {
                    ToastUtils.showToast(this, "请输入昵称")
                    return
                }
                mPresenter.updateInfo(edtNickName)//进行修改请求
            }

        }
    }


    override fun bindEventListener() {

    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("修改昵称")

        var tvRight: TextView = toolbarRightView as TextView
        tvRight.setText("修改")
        tvRight.setTextColor(resources.getColor(R.color.black))
        tvRight?.setOnClickListener(this)
        val user: User = BmobUser.getCurrentUser(User::class.java)
        edt_nick_name.setText(user.nickname)

    }

    override fun showProgress() {

    }

    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_modify_nickname
    }

    override fun loadData() {
    }

    override fun dismissProgress() {
    }

    override fun modifyPwdSuccess() {
        ToastUtils.showToast(this, "修改密码成功")
    }

    override fun modifyPwdFailure(errMsg: String) {
        ToastUtils.showToast(this, "修改密码失败" + errMsg)
    }

    override fun modifyNickNameSuccess() {
        ToastUtils.showToast(this, "修改昵称成功")
        EventBus.getDefault().post(AppEventType(AppEventType.UPDATE_INFO))
        finish()
    }

    override fun modifynickNameFailure(errMsg: String) {
        ToastUtils.showToast(this, "修改昵称失败")
        finish()
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