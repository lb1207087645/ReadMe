package com.graduation.android.news.mvp;

import com.graduation.android.base.mvp.BaseViewTest;
import com.graduation.android.base.mvp.IPresenter;
import com.graduation.android.bean.NewsDetail;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.model.FreshBean;

import java.util.List;

/**
 * 新闻的contract
 */
public interface NewsContractTest {

    interface View extends BaseViewTest {

        void loadListSuccess(int page, List<DesignRes> datas);

        void loadSimple(List<NewsDetail> list);

        void loadFreshNews(List<FreshBean.PostsBean> postsBean);//煎蛋新闻

    }

    interface Presenter extends IPresenter<View> {

        void loadList(int page);

        void pullToLoadList();

        void getCall(String id, String action, int pullNum);

        void getFreshNews(int page);

    }
}
