package com.graduation.android.readme.setting

import SettingContract
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import cn.bmob.v3.BmobUser
import com.fjlssy.lszypatient.setting.mvp.SettingPresenter
import com.graduation.android.readme.R
import com.graduation.android.readme.base.utils.L
import com.graduation.android.readme.base.utils.ToastUtils
import com.graduation.android.readme.basemodule.BaseActivity
import com.graduation.android.readme.bean.User
import kotlinx.android.synthetic.main.activity_person_info.*

/**
 * 个人资料页
 */
class PersonInfoActivity : BaseActivity<SettingContract.Presenter, SettingContract.View>(),
    SettingContract.View, View.OnClickListener, RadioGroup
    .OnCheckedChangeListener {


    private var sexMsg: Int = 1




    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_nick -> {//昵称
                startActivity(Intent(mActivity, ModifyNickNameActivity::class.java))
            }

        }
    }


    override fun bindEventListener() {
        layout_nick?.setOnClickListener(this)
        sexRadioGroup.setOnCheckedChangeListener(this)
    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("个人资料")
        sexRadioGroup.check(R.id.boy)
    }

    override fun onResume() {
        super.onResume()
        val user: User = BmobUser.getCurrentUser(User::class.java)
        tv_set_nick.text = user.nickname
        tv_set_name.text = user.username
        if (user.getGender() === 1) { //性别
            sexRadioGroup.check(R.id.boy)
            sexMsg = 1
        } else if (user.getGender() === 2) {
            sexRadioGroup.check(R.id.grid)
            sexMsg = 2
        }
    }

    override fun showProgress() {

    }

    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_person_info
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun modifynickNameFailure(errMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserSexSuccess() {
        ToastUtils.showToast(this, "修改性别成功")
    }

    override fun updateUserSexFailure(mssage: String) {

        L.d("PersonInfoActivity",mssage)
        ToastUtils.showToast(this, "修改性别失败"+mssage)
    }


    override fun isActive(): Boolean {
        return false
    }

    override fun initPresenter(): SettingContract.Presenter {
        return SettingPresenter()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.boy) {
            sexMsg = 1
        } else if (checkedId == R.id.grid) {
            sexMsg = 2
        }
        mPresenter.updateUserSex(sexMsg)
    }

}