package com.graduation.android.readme.home.mvp;

import com.graduation.android.readme.base.network.RetrofitServiceManager;
import com.graduation.android.readme.home.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class NewsListModel {

    private NewsListService newsListService;

    public NewsListModel() {
        newsListService = RetrofitServiceManager.getInstance().create(NewsListService.class);
    }

    public Observable<Map<String, List<NewsSummary>>> getNewsList(String cacheControl,
                                                                  String type, String id,
                                                                  int startPage) {
        return newsListService.getNewsList(cacheControl, type, id, startPage);
    }

    public interface NewsListService {

        /**
         * 获取新闻列表
         *
         * @param cacheControl
         * @param type
         * @param id
         * @param startPage
         * @return
         */
        @GET("http://c.m.163.com/nc/article/{type}/{id}/{startPage}-20.html")
        Observable<Map<String, List<NewsSummary>>> getNewsList(
                @Header("Cache-Control") String cacheControl,
                @Path("type") String type, @Path("id") String id,
                @Path("startPage") int startPage);

    }
}