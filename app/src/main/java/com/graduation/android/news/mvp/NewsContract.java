package com.graduation.android.news.mvp;

import com.graduation.android.base.mvp.BaseView;
import com.graduation.android.base.mvp.IPresenter;
import com.graduation.android.bean.NewsDetail;
import com.graduation.android.model.FreshBean;

import java.util.List;

/**
 * 新闻的contract
 */
public interface NewsContract {

    interface View extends BaseView {


        /**
         * 煎蛋新闻
         *
         * @param postsBean
         */

        void loadFreshNews(List<FreshBean.PostsBean> postsBean);


        /**
         * 加载新闻数据
         *
         * @param itemBeanList
         */
        void loadNewsData(List<NewsDetail> itemBeanList);

    }

    interface Presenter extends IPresenter<View> {


        void getFreshNews(int page);

        /**
         * 获取新闻详细信息
         *
         * @param id      频道ID值
         * @param action  用户操作方式
         *                1：下拉 down
         *                2：上拉 up
         *                3：默认 default
         * @param pullNum 操作次数 累加
         */
        void getNewsData(String id, String action, int pullNum);

    }
}
