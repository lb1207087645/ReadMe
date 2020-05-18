package com.graduation.android.readme.home.mvp;

import android.text.TextUtils;
import android.widget.Toast;

import com.graduation.android.readme.api.Api;
import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.mvp.BasePresenter;
import com.graduation.android.readme.base.net.ApiConstants;
import com.graduation.android.readme.base.net.HostType;
import com.graduation.android.readme.base.network.ErrorEntity;
import com.graduation.android.readme.base.network.Transformer;
import com.graduation.android.readme.base.utils.L;
import com.graduation.android.readme.base.utils.ToastUtils;
import com.graduation.android.readme.home.bean.NewsSummary;
import com.graduation.android.readme.http.BaseObserver;
import com.graduation.android.readme.http.RxSchedulers;
import com.graduation.android.readme.model.FreshNewsBean;
import com.graduation.android.readme.model.NewsModel;
import com.graduation.android.readme.utils.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 新闻列表模块
 */
public class NewsListPresenter extends BasePresenter<NewsListContract.View> implements NewsListContract.Presenter {

    private NewsListModel model;

    // private LoginPresenter mLoginPresenter;
    public NewsListPresenter() {
// mLoginPresenter = this;
        model = new NewsListModel();
    }


    @Override
    public void getNewsListDataRequest(String type, String id, int startPage) {
        subscribe(model.getNewsList(Api.getCacheControl(), type, id, startPage), new BaseObserver<Map<String, List<NewsSummary>>>() {
            @Override
            public void onError(ErrorEntity err) {

                L.d("data", "onError");
                getView().showErr(err);
//                getView().loadFreshNews(null);
            }

            @Override
            public void onAfter() {
                L.d("data", "onAfter");

            }

            @Override
            public void onData(Map<String, List<NewsSummary>> baseResponse) {
                L.d("data", "onData----" + baseResponse.toString());
                getView().showContentView();

                for (String key : baseResponse.keySet()) {
                    System.out.println("key= " + key + " and value= " + baseResponse.get(key));
                    List<NewsSummary> list = baseResponse.get(key);
                    if (list != null && list.size() > 0) {
                        getView().returnNewsListData(list);//转化数据
                    }
                }

            }
        });

    }


//    public Observable<List<NewsSummary>> getNewsListData(final String type, final String id, final int startPage) {
//        return model.getNewsList(Api.getCacheControl(), type, id, startPage)
//                .flatMap(new Function<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
//                    @Override
//                    public Observable<NewsSummary> apply(Map<String, List<NewsSummary>> map) {
//                        if (id.endsWith(ApiConstants.HOUSE_ID)) {
//                            // 房产实际上针对地区的它的id与返回key不同
//                            return Observable.fromIterable(map.get("北京"));//TODO 这里可能出问题
//                        }
//                        return Observable.fromIterable(map.get(id));//TODO 这里可能出问题
//                    }
//                })
//                //转化时间
//                .map(new Function<NewsSummary, NewsSummary>() {
//                    @Override
//                    public NewsSummary apply(NewsSummary newsSummary) {
//                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
//                        newsSummary.setPtime(ptime);
//                        return newsSummary;
//                    }
//                })
//                .distinct()//去重
//                .toSortedList(new BiFunction<NewsSummary, NewsSummary, Integer>() {
//                    @Override
//                    public Integer apply(NewsSummary newsSummary, NewsSummary newsSummary2) {
//                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
//                    }
//                })
//                .compose(RxSchedulers.<List<NewsSummary>>io_main());;//声明线程调度
//
//
//    }
}