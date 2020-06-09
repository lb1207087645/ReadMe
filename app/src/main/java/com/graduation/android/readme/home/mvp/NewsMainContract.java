package com.graduation.android.readme.home.mvp;

import com.graduation.android.readme.base.mvp.BaseView;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.bean.NewsChannelTable;
import com.graduation.android.readme.bean.Translation1;

import java.util.List;

/**
 * 新闻首页
 */
public interface NewsMainContract {

    interface View extends BaseView {

        void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
    }

    interface Presenter extends IPresenter<View> {

        void getChannelsRequest();//获取渠道

    }
}
