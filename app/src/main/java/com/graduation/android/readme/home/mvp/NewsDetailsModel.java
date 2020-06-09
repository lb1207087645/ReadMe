package com.graduation.android.readme.home.mvp;

import com.graduation.android.readme.base.network.RetrofitServiceManager;
import com.graduation.android.readme.home.bean.NewsDetail;
import com.graduation.android.readme.home.bean.NewsDetailsBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * 新闻详情
 */
public class NewsDetailsModel {

    private NewsDetailsService newsDetailsService;

    public NewsDetailsModel() {
        newsDetailsService = RetrofitServiceManager.getInstance().create(NewsDetailsService.class);
    }

    public Observable<Map<String, NewsDetail>> getNewDetail(String cacheControl, String postId) {
        return newsDetailsService.getNewDetail(cacheControl, postId);
    }

    public interface NewsDetailsService {


        //新闻详情接口
        @GET("http://c.m.163.com/nc/article/{postId}/full.html")
        Observable<Map<String, NewsDetail>> getNewDetail(
                @Header("Cache-Control") String cacheControl,
                @Path("postId") String postId);

    }
}