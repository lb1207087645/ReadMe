package com.fjlssy.lszypatient.setting.mvp

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.graduation.android.readme.base.mvp.BasePresenter
import com.graduation.android.readme.base.utils.L
import com.graduation.android.readme.bean.User
import cn.bmob.v3.listener.SaveListener
import com.graduation.android.readme.base.BaseApplication
import com.graduation.android.readme.base.utils.ToastUtils
import com.graduation.android.readme.model.FeekBack
import SettingContract




/**
 * 修改密码的P层
 */
class SettingPresenter : BasePresenter<SettingContract.View>(), SettingContract.Presenter {

    override fun feedback(feedBack: String) {
        val feekBack = FeekBack()
        feekBack.content = feedBack
        feekBack.save(object : SaveListener<String>() {
            override fun done(objectId: String, e: BmobException?) {
                if (e == null) {

                    view!!.feedbackSuccess()

                } else {
                    view!!.mfeedbackFailure(e.message.toString())

                }
            }
        })


//        val p2 = Person()
//        p2.setName("lucky")
//        p2.setAddress("北京海淀")
//        p2.save(object : SaveListener<String>() {
//            override fun done(objectId: String, e: BmobException?) {
//                if (e == null) {
//                    toast("添加数据成功，返回objectId为：$objectId")
//                } else {
//                    toast("创建数据失败：" + e.message)
//                }
//            }
//        })
    }


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
        u.gender = sexMsg
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
