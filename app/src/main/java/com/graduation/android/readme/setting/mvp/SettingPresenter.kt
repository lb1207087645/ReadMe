package com.fjlssy.lszypatient.setting.mvp

import SettingContract
import android.support.design.widget.Snackbar
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.graduation.android.readme.base.mvp.BasePresenter
import com.graduation.android.readme.base.utils.L
import com.graduation.android.readme.bean.User


/**
 * 修改密码的P层
 */
class SettingPresenter : BasePresenter<SettingContract.View>(), SettingContract.Presenter {


    override fun modifyPwd(oldPwd: String, newPwd: String) {
        L.d("ModifyPwdPresenter", oldPwd)
        //TODO 此处替换为你的旧密码和新密码
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, object : UpdateListener() {

            override fun done(e: BmobException?) {
                if (e == null) {
                    view!!.modifyPwdSuccess()
                } else {
                    view!!.modifyPwdFailure(e.message.toString())
                    val user: User = BmobUser.getCurrentUser(User::class.java)//获取当前用户信息
                    L.d("ModifyPwdPresenter", user.username)
                    L.d("ModifyPwdPresenter", e.message.toString())
                }
            }
        })

    }


    /**
     * 修改昵称
     */
    override fun updateInfo(nickName: String) {

        val user: User = BmobUser.getCurrentUser(User::class.java)
        val u = User()
        u.setNickname(nickName)
        u.objectId = user.objectId
        u.update(object : UpdateListener() {

            override fun done(e: BmobException?) {
                if (e == null) {
                    view!!.modifyNickNameSuccess()
                } else {
                    view!!.modifynickNameFailure(e.message.toString())
                }

            }
        })


    }


    /**
     * 更新用户性别
     */
    override fun updateUserSex(sexMsg: Int) {
        val user: User = BmobUser.getCurrentUser(User::class.java)
        val u = User()
        u.gender=sexMsg
        u.objectId = user.objectId
        u.update(object : UpdateListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    view!!.updateUserSexSuccess()
                } else {
                    view!!.updateUserSexFailure(e.message.toString())
                }
            }
        })


    }


}
