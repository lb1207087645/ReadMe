package com.graduation.android.readme.home.mvp;

import android.app.Activity;

import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.cache.ACache;
import com.graduation.android.readme.base.mvp.BasePresenter;
import com.graduation.android.readme.base.net.ApiConstants;
import com.graduation.android.readme.base.network.ErrorEntity;
import com.graduation.android.readme.bean.NewsChannelTable;
import com.graduation.android.readme.bean.Translation1;
import com.graduation.android.readme.http.BaseObserver;
import com.graduation.android.readme.http.BaseObserverGet;
import com.graduation.android.readme.http.BaseResponse;
import com.graduation.android.readme.model.NewsMainModel;
import com.graduation.android.readme.model.Translation3;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;


/**
 * home的p层
 */
public class NewsMainPresenter extends BasePresenter<NewsMainContract.View> implements NewsMainContract.Presenter {


    private NewsMainModel model;
    private NewsMainPresenter mNewMainPresenter;

    public NewsMainPresenter(Activity activity) {
        mNewMainPresenter = this;
        model = new NewsMainModel();
    }


    /**
     * TODO 改成异步加载
     *
     * @return
     */
    @Override
    public void getChannelsRequest() {
        ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(BaseApplication.getContext()).getAsObject(AppConstant.CHANNEL_MINE);
        if (newsChannelTableList == null) {
            newsChannelTableList = (ArrayList<NewsChannelTable>) loadNewsChannelsStatic();
            ACache.get(BaseApplication.getContext()).put(AppConstant.CHANNEL_MINE, newsChannelTableList);
        }
        getView().returnMineNewsChannels(newsChannelTableList);

    }


    /**
     * TODO 改成异步加载
     *
     * @return
     */
//    public List<NewsChannelTable> lodeMineNewsChannels() {//
//
////        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
////            @Override
////            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
////
////                subscriber.onNext(newsChannelTableList);
////                subscriber.onCompleted();
////            }
////        }).compose(RxSchedulers.<List<NewsChannelTable>>io_main());
//
//
//        ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(BaseApplication.getContext()).getAsObject(AppConstant.CHANNEL_MINE);
//        if (newsChannelTableList == null) {
//            newsChannelTableList = (ArrayList<NewsChannelTable>) loadNewsChannelsStatic();
//            ACache.get(BaseApplication.getContext()).put(AppConstant.CHANNEL_MINE, newsChannelTableList);
//        }
//
//        return newsChannelTableList;
//    }

    /**
     * 加载固定新闻类型
     *
     * @return
     */
    public static List<NewsChannelTable> loadNewsChannelsStatic() {
        List<String> channelName = Arrays.asList(BaseApplication.getContext().getResources().getStringArray(R.array.news_channel_name_static));
        List<String> channelId = Arrays.asList(BaseApplication.getContext().getResources().getStringArray(R.array.news_channel_id_static));
        ArrayList<NewsChannelTable> newsChannelTables = new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                    , ApiConstants.getType(channelId.get(i)), i <= 5, i, true);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }
}
