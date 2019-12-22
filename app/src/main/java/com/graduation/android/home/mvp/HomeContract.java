package com.graduation.android.home.mvp;

import com.graduation.android.base.mvp.BaseView;
import com.graduation.android.base.mvp.IPresenter;
import com.graduation.android.bean.Translation1;

public interface HomeContract {

    interface View extends BaseView {
        void loadCallGet(String value);

        void loadCallPost(String value);
    }

    interface Presenter extends IPresenter<View> {
        void getCallGet();//

        void getCallPost();

    }
}
