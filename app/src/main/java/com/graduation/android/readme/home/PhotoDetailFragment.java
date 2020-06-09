package com.graduation.android.readme.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.image.ImageLoadConfig;
import com.graduation.android.readme.base.image.ImageLoaderManager;
import com.graduation.android.readme.base.mvp.BaseFragment;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.http.RxSchedulers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * des:图文新闻详情
 * Created by xsf
 * on 2016.09.9:57
 */
public class PhotoDetailFragment extends BaseFragment {
    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String mImgSrc;


    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fra_news_photo_detail;
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if (getArguments() != null) {
            mImgSrc = getArguments().getString(AppConstant.PHOTO_DETAIL_IMGSRC);
        }
        initPhotoView();
        setPhotoViewClickEvent();
    }


    private void initPhotoView() {
//        Observable.timer(100, TimeUnit.MILLISECONDS) // 直接使用glide加载的话，activity切换动画时背景短暂为默认背景色
//                .compose(RxSchedulers.<Long>io_main())
//                .subscribe(new Subscriber<Long>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//
//
////
////
////
////
////
//
//
//                    }
//                });
//
//
//
//
//
//        Observable.timer(100, TimeUnit.MILLISECONDS) // 直接使用glide加载的话，activity切换动画时背景短暂为默认背景色
//                .compose(RxSchedulers.<Long>io_main())
//                .subscribe(new Subscriber<Long>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                      //  ImageLoaderUtils.displayBigPhoto(getContext(),photoView,mImgSrc);
//                    }
//                });
                        ImageLoaderManager.getInstance().loadImage(photoView, mImgSrc, new ImageLoadConfig.Builder()
                                .setPlaceHolderResId(R.drawable.replaceable_drawable_elite_album_default)
                                .setErrorResId(R.drawable.replaceable_drawable_elite_album_default)
                                .build());
    }


    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showTip(String message) {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void hideLoadingDialog() {

    }


    private void setPhotoViewClickEvent() {
//        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(View view, float v, float v1) {
//                mRxManager.post(AppConstant.PHOTO_TAB_CLICK, "");
//            }
//        });
    }

}
