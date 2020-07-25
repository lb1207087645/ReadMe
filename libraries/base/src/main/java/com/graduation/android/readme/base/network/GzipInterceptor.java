package com.graduation.android.readme.base.network;


import com.graduation.android.readme.base.utils.GZIPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 有zip 解压缩用的数据
 */
public class GzipInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == 200) {
            //这里是网络拦截器，可以做错误处理
            MediaType mediaType = response.body().contentType();
            //当调用此方法java.lang.IllegalStateException: closed，原因为OkHttp请求回调中response.body().string()只能有效调用一次
            //String content = response.body().string();
            byte[] data = response.body().bytes();
            if (GZIPUtils.isGzip(response.headers())) {
                //请求头显示有gzip，需要解压
                data = GZIPUtils.uncompress(data);
            }
            //创建一个新的responseBody，返回进行处理
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, data))
                    .build();
        } else {
            return response;
        }

    }
}
