package com.graduation.android.base.image.listener;

import android.graphics.Bitmap;

public interface BitmapLoaderListener {

    void onSuccess(Bitmap b);

    void onError();
}