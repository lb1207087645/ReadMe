package com.graduation.android.readme.home.mvp;

import com.graduation.android.readme.base.mvp.BaseView;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.home.bean.NewsSummary;

import java.util.List;
import java.util.Map;

public class NewsListContract {
    public interface View extends BaseView {
        //返回获取的新闻
        void returnNewsListData(List<NewsSummary> newsSummaries);
    }

    public interface Presenter extends IPresenter<View> {
        //发起获取新闻请求
        void getNewsListDataRequest(String type, final String id, int startPage);
    }

}