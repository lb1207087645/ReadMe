package com.graduation.android.base.image.listener;

import android.graphics.Bitmap;

public interface ImageDownLoadCallBack {

    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
