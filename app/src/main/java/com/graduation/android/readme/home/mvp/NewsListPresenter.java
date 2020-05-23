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

    public NewsListPresenter() {
        model = new NewsListModel();
    }


    @Override
    public void getNewsListDataRequest(String type, String id, int startPage) {
        subscribe(model.getNewsList(Api.getCacheControl(), type, id, startPage), new BaseObserver<Map<String, List<NewsSummary>>>() {
            @Override
            public void onError(ErrorEntity err) {//先实现，再优化
                L.d("data", "onError");
                getView().showErr(err);
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
}