package com.graduation.android.home.mvp;

import com.graduation.android.base.mvp.BaseViewTest;
import com.graduation.android.base.mvp.IPresenter;
import com.graduation.android.entity.DesignRes;

import java.util.List;

public interface HomeContractTest {

    interface View extends BaseViewTest {

        void loadListSuccess(int page, List<DesignRes> datas);

        void loadSimple(String out);

    }

    interface Presenter extends IPresenter<View> {

        void loadList(int page);

        void pullToLoadList();

        void getCall();//

    }
}
