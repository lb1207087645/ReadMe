package com.graduation.android.news.mvp;

import com.graduation.android.base.BaseViewTest;
import com.graduation.android.base.IPresenter;
import com.graduation.android.entity.DesignRes;

import java.util.List;

/**
 * 新闻的contract
 */
public interface NewsContractTest {

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
