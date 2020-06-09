package com.graduation.android.readme.login.mvp;

import android.support.design.widget.Snackbar;

import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.mvp.BasePresenter;
import com.graduation.android.readme.base.utils.ToastUtils;
import com.graduation.android.readme.bean.User;
import com.graduation.android.readme.login.RegisterActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * 登录注册模块
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {



    public LoginPresenter() {

    }

    @Override
    public void passwordLogin(String username, String password) {

        final User user = new User();
        //此处替换为你的用户名
        user.setUsername(username);
        //此处替换为你的密码
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    getView().passwordLoginSuccess();
                } else {
                    getView().passwordLoginFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void register(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    getView().registerSuccess();
                } else {
                    getView().registerFailure(e.getMessage());

                }
            }
        });
    }

}