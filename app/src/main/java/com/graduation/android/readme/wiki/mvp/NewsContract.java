package com.graduation.android.readme.wiki.mvp;

import com.graduation.android.readme.base.mvp.BaseView;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.bean.NewsDetail;
import com.graduation.android.readme.model.FreshBean;
import com.graduation.android.readme.wiki.bean.FreshNewsArticleBean;

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

        void loadFreshNewsSuccess(FreshNewsArticleBean articleBean);

    }

    interface Presenter extends IPresenter<View> {
        void getFreshNews(int page);

        void getFreshNewsArticle(int id);

    }
}
