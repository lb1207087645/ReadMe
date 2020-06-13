package com.graduation.android.readme.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import cn.bmob.v3.BmobUser
import com.graduation.android.readme.R
import com.graduation.android.readme.base.eventbus.AppEventType
import com.graduation.android.readme.base.mvp.BaseView
import com.graduation.android.readme.base.mvp.IPresenter
import com.graduation.android.readme.basemodule.BaseActivity
import com.graduation.android.readme.utils.CacheUtils
import org.greenrobot.eventbus.EventBus

import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity<IPresenter<BaseView>, BaseView>(), View.OnClickListener {


    private var tvRight: TextView? = null


    private var rlClearCache: RelativeLayout? = null


    private var rlModifyPwd: RelativeLayout? = null


    private var rlFeekBack: RelativeLayout? = null

    private var rlAccountInfo: RelativeLayout? = null
    private var tv_user_name_login_out: TextView? = null
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_logout -> {//退出登录
                BmobUser.logOut();
                EventBus.getDefault().post(AppEventType(AppEventType.LOGIN_OUT))
                finish()
            }
            R.id.rl_clear_cache -> {//清理缓存
                CacheUtils.clearAllCache(this@SettingActivity)
            }


            R.id.rl_modify_pwd -> {//修改密码
                startActivity(Intent(mActivity, ModifyPwdActivity::class.java))
            }

            R.id.rl_about_us -> {//关于我们
                startActivity(Intent(mActivity, AboutUsActivity::class.java))
            }


            R.id.rl_feedback -> {//反馈
                startActivity(Intent(mActivity, FeedBackActivity::class.java))
            }


        }
    }


    override fun bindEventListener() {
        tvRight?.setOnClickListener(this)
        rl_clear_cache?.setOnClickListener(this)



    }


    override fun initWidget(savedInstanceState: Bundle?) {
        var tvTitle: TextView = toolbarTitleView as TextView
        tvTitle.setText("设置")
        tvRight = toolbarRightView as TextView
        tvRight!!.setText("")
        tv_user_name_login_out = findViewById<TextView>(R.id.btn_logout)
        tv_user_name_login_out?.setOnClickListener(this)
        rlClearCache = findViewById<RelativeLayout>(R.id.rl_clear_cache)
        rlClearCache?.setOnClickListener(this)

        rlModifyPwd = findViewById<RelativeLayout>(R.id.rl_modify_pwd)
        rlModifyPwd?.setOnClickListener(this)
        rl_about_us?.setOnClickListener(this)


        rlFeekBack = findViewById<RelativeLayout>(R.id.rl_feedback)
        rlFeekBack?.setOnClickListener(this)

    }

    override fun showProgress() {

    }

    override fun isActive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTip(message: String?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun loadData() {
    }

    override fun dismissProgress() {
    }


    override fun initPresenter() = null

}