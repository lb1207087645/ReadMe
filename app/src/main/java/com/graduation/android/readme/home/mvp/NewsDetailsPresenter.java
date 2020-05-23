package com.graduation.android.readme.home.mvp;

import android.text.TextUtils;
import android.widget.Toast;

import com.graduation.android.readme.api.Api;
import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.mvp.BasePresenter;
import com.graduation.android.readme.base.network.ErrorEntity;
import com.graduation.android.readme.base.utils.L;
import com.graduation.android.readme.base.utils.ToastUtils;
import com.graduation.android.readme.home.bean.NewsDetail;
import com.graduation.android.readme.home.bean.NewsDetailsBean;
import com.graduation.android.readme.home.bean.NewsSummary;
import com.graduation.android.readme.http.BaseObserver;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * 新闻详情模块
 */
public class NewsDetailsPresenter extends BasePresenter<NewsDetailsContract.View> implements NewsDetailsContract.Presenter {

    private NewsDetailsModel model;

    // private LoginPresenter mLoginPresenter;
    public NewsDetailsPresenter() {
// mLoginPresenter = this;
        model = new NewsDetailsModel();
    }


    @Override
    public void getOneNewsData(String postId) {
        subscribe(model.getNewDetail(Api.getCacheControl(), postId).map(new Function<Map<String, NewsDetail>, NewsDetail>() {
            @Override
            public NewsDetail apply(Map<String, NewsDetail> stringNewsDetailMap) throws Exception {
                NewsDetail newsDetail = stringNewsDetailMap.get(postId);
                changeNewsDetail(newsDetail);
                return newsDetail;
            }

        }), new BaseObserver<NewsDetail>() {
            @Override
            public void onError(ErrorEntity err) {
                L.d("data", "onError");
                getView().showErr(err);
            }

            @Override
            public void onAfter() {
                L.d("data", "onAfter");
            }

            @Override
            public void onData(NewsDetail newsDetail) {
                getView().returnOneNewsData(newsDetail);
            }
        });
    }


    private void changeNewsDetail(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        if (isChange(imgSrcs)) {
            String newsBody = newsDetail.getBody();
            newsBody = changeNewsBody(imgSrcs, newsBody);
            newsDetail.setBody(newsBody);
        }
    }

    private boolean isChange(List<NewsDetail.ImgBean> imgSrcs) {
        return imgSrcs != null && imgSrcs.size() >= 2;
    }

    private String changeNewsBody(List<NewsDetail.ImgBean> imgSrcs, String newsBody) {
        for (int i = 0; i < imgSrcs.size(); i++) {
            String oldChars = "<!--IMG#" + i + "-->";
            String newChars;
            if (i == 0) {
                newChars = "";
            } else {
                newChars = "<img src=\"" + imgSrcs.get(i).getSrc() + "\" />";
            }
            newsBody = newsBody.replace(oldChars, newChars);

        }
        return newsBody;
    }
}