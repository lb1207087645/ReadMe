package com.graduation.android.mvp;

import com.graduation.android.base.BaseViewTest;
import com.graduation.android.base.IPresenter;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.model.Translation3;

import java.util.List;

public interface HomeContractTest {

    interface View extends BaseViewTest {

        void loadListSuccess(int page, List<DesignRes> datas);

        void loadSimple(String out);

    }

    interface Presenter extends IPresenter<View> {

        void loadList(int page);

        void pullToLoadList();

        void getCall();

    }
}
