package com.graduation.android.readme.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.image.ImageLoadConfig;
import com.graduation.android.readme.base.image.ImageLoaderManager;
import com.graduation.android.readme.basemodule.BaseActivity;
import com.graduation.android.readme.home.bean.NewsDetail;
import com.graduation.android.readme.home.mvp.NewsDetailsContract;
import com.graduation.android.readme.home.mvp.NewsDetailsPresenter;
import com.graduation.android.readme.http.RxSchedulers;
import com.graduation.android.readme.utils.TimeUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * 普通新闻详情
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailsContract.Presenter, NewsDetailsContract.View> implements NewsDetailsContract.View {


    @BindView(R.id.news_detail_from_tv)
    TextView newsDetailFromTv;
    @BindView(R.id.news_detail_body_tv)
    TextView newsDetailBodyTv;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private String postId;


    private String mNewsTitle;
    private String mShareLink;
//
//    private URLImageGetter mUrlImageGetter;

    @Override
    protected NewsDetailsContract.Presenter initPresenter() {
        return new NewsDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_details;
    }


    @Override
    protected void initWidget(Bundle savedInstanceState) {
        ((TextView) getToolbarTitleView()).setText("新闻详情");
    }

    @Override
    public void loadData() {
        postId = getIntent().getStringExtra(AppConstant.NEWS_POST_ID);
        mPresenter.getOneNewsData(postId);
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

    /**
     * 入口
     *
     * @param mContext
     * @param postId
     */
    public static void startAction(Context mContext, View view, String postId, String imgUrl) {
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra(AppConstant.NEWS_POST_ID, postId);
        intent.putExtra(AppConstant.NEWS_IMG_RES, imgUrl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, view, AppConstant.TRANSITION_ANIMATION_NEWS_PHOTOS);
            mContext.startActivity(intent, options.toBundle());
        } else {

            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }

    }

    private void setBody(NewsDetail newsDetail, String newsBody) {
        int imgTotal = newsDetail.getImg().size();
        if (isShowBody(newsBody, imgTotal)) {
//              mNewsDetailBodyTv.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效,实测经常卡机崩溃
            //   mUrlImageGetter = new URLImageGetter(newsDetailBodyTv, newsBody, imgTotal);
            //     newsDetailBodyTv.setText(Html.fromHtml(newsBody, mUrlImageGetter, null));
        } else {
            newsDetailBodyTv.setText(Html.fromHtml(newsBody));
        }
    }


    private boolean isShowBody(String newsBody, int imgTotal) {
        return imgTotal >= 2 && newsBody != null;
    }

    private String getImgSrcs(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        String imgSrc;
        if (imgSrcs != null && imgSrcs.size() > 0) {
            imgSrc = imgSrcs.get(0).getSrc();
        } else {
            imgSrc = getIntent().getStringExtra(AppConstant.NEWS_IMG_RES);
        }
        return imgSrc;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void returnOneNewsData(NewsDetail newsDetail) {
        mShareLink = newsDetail.getShareLink();
        mNewsTitle = newsDetail.getTitle();
        String newsSource = newsDetail.getSource();
        String newsTime = TimeUtil.formatDate(newsDetail.getPtime());
        String newsBody = newsDetail.getBody();
        String NewsImgSrc = getImgSrcs(newsDetail);

        setToolBarLayout(mNewsTitle);
        //mNewsDetailTitleTv.setText(newsTitle);
        newsDetailFromTv.setText(getString(R.string.news_from, newsSource, newsTime));
        setNewsDetailPhotoIv(NewsImgSrc);
        setNewsDetailBodyTv(newsDetail, newsBody);
    }

    private void setToolBarLayout(String newsTitle) {
        toolbarLayout.setTitle(newsTitle);
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }


    /**
     * 显示新闻详情的图片
     * @param imgSrc
     */
    private void setNewsDetailPhotoIv(String imgSrc) {
        ImageLoaderManager.getInstance().loadImage(newsDetailPhotoIv, imgSrc, new ImageLoadConfig.Builder().setPlaceHolderResId(R.drawable.icon_head_default)
                .setErrorResId(R.drawable.icon_head_default)
                .setRoundedCorners(true).setRoundingRadius(60)
                .build());

    }


    private void setNewsDetailBodyTv(final NewsDetail newsDetail, final String newsBody) {
        setBody(newsDetail, newsBody);
    }

}

