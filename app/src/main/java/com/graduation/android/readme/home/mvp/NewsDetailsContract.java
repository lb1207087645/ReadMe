package com.graduation.android.readme.home.mvp;

import com.graduation.android.readme.base.mvp.BaseView;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.home.bean.NewsDetail;

import io.reactivex.Observable;

public class NewsDetailsContract {
    public interface View extends BaseView {
        //返回获取的新闻
        void returnOneNewsData(NewsDetail newsDetail);
    }

    public interface Presenter extends IPresenter<View> {
        //请求获取新闻
         void getOneNewsData(String postId);

    }

}