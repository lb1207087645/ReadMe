package com.graduation.android.readme.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.image.ImageLoadConfig;
import com.graduation.android.readme.base.image.ImageLoaderManager;
import com.graduation.android.readme.base.utils.ToastUtils;
import com.graduation.android.readme.basemodule.BaseActivity;
import com.graduation.android.readme.home.bean.NewsDetail;
import com.graduation.android.readme.home.mvp.NewsDetailsContract;
import com.graduation.android.readme.home.mvp.NewsDetailsPresenter;
import com.graduation.android.readme.http.RxSchedulers;
import com.graduation.android.readme.mine.MineFragment;
import com.graduation.android.readme.utils.TimeUtil;
import com.graduation.android.share.utils.DialogUtil;
import com.graduation.android.share.utils.ShareSdkUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import io.reactivex.Observable;

/**
 * 普通新闻详情
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailsContract.Presenter, NewsDetailsContract.View> implements NewsDetailsContract.View, PlatformActionListener {


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
    private ShareSdkUtils shareSdkUtils;
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
    protected void bindEventListener() {

        //分享
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareSdkUtils = new ShareSdkUtils(BaseApplication.getContext());
                try {
                    final Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    DialogUtil dialogUtil = DialogUtil.getInstance();
                    dialogUtil.showShareDialog(NewsDetailActivity.this, true);
                    final String content = "来自读我的分享";
                    dialogUtil.setmDialogClickListener(new DialogUtil.OnClickListener() {
                        @Override
                        public void onQqClick() {
                            shareSdkUtils.shareQQ_WebPage(content+mShareLink, mNewsTitle, content, bmp, NewsDetailActivity.this);
                        }

                        @Override
                        public void onWeChatClick() {

//
//                    Wechat.ShareParams sp = new Wechat.ShareParams();
//                    //微信分享网页的参数严格对照列表中微信分享网页的参数要求
//                    sp.setTitle("标题");
//                    sp.setText("我是共用的参数，这几个平台都有text参数要求，提取出来啦");
//                    sp.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");
//                    sp.setUrl("http://sharesdk.cn");
//                    sp.setShareType(Platform.SHARE_WEBPAGE);
//                    LogUtils.d("ShareSDK", sp.toMap().toString());
//                    Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
//// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
//                    weChat.setPlatformActionListener(new PlatformActionListener() {
//                        @Override
//                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//                            LogUtils.d("ShareSDK", "onComplete ---->  分享成功");
//
//                        }
//
//                        @Override
//                        public void onError(Platform platform, int i, Throwable throwable) {
//                            LogUtils.d("ShareSDK", "onError ---->  分享失败" + throwable.getStackTrace().toString());
//                            LogUtils.d("ShareSDK", "onError ---->  分享失败" + throwable.getMessage());
//                            throwable.getMessage();
//                            throwable.printStackTrace();
//                        }
//
//                        @Override
//                        public void onCancel(Platform platform, int i) {
//                            LogUtils.d("ShareSDK", "onCancel ---->  分享取消");
//                        }
//                    });
//// 执行图文分享
//                    weChat.share(sp);
                            shareSdkUtils.shareWechat_WebPage(mShareLink, mNewsTitle, content, bmp, NewsDetailActivity.this);
                        }

                        @Override
                        public void onQZoneClick() {
                            //  mShareUtils.shareQZone_WebPage(finalShareUrl, title, content, mFilePath, InviteBuildActivity.this);
                        }

                        @Override
                        public void onWeChatFriendsClick() {
                            //    mShareUtils.shareWechatFriends_WebPage(finalShareUrl, title, content, mFilePath, InviteBuildActivity.this);
                        }

                        @Override
                        public void onMessageClick() {
                            //    mShareUtils.shareMessage_Test(title + (type == 1 ? ",邀请地址<" : ",领取地址<") + finalShareUrl + ">", InviteBuildActivity.this);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
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
     *
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

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        ToastUtils.showToast(BaseApplication.getContext(), "分享成功");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtils.showToast(BaseApplication.getContext(), "分享失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtils.showToast(BaseApplication.getContext(), "分享取消");
    }
}

