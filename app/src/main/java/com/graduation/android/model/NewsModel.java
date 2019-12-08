package com.graduation.android.model;

import com.graduation.android.base.network.RetrofitServiceManager;
import com.graduation.android.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public class NewsModel {


    private ModelService modelService;

    public NewsModel() {
        modelService = RetrofitServiceManager.getInstance().create(ModelService.class);
    }


    public Observable<BaseResponse<Translation3.content>> getAudioDetailInfo() {
        return modelService.getCall();
    }


    /**
     * 请求接口
     */
    interface ModelService {
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
        Observable<BaseResponse<Translation3.content>> getCall();
    }

}
