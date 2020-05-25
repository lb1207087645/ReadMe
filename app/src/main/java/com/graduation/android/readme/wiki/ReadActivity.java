package com.graduation.android.readme.wiki;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.graduation.android.readme.R;
import com.graduation.android.readme.base.image.ImageLoadConfig;
import com.graduation.android.readme.base.image.ImageLoaderManager;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.base.utils.StatusBarUtil;
import com.graduation.android.readme.basemodule.BaseActivity;
import com.graduation.android.readme.bean.NewsDetail;
import com.graduation.android.readme.home.mvp.NewsDetailsContract;
import com.graduation.android.readme.http.BaseObserver;
import com.graduation.android.readme.http.RxSchedulers;
import com.graduation.android.readme.model.FreshBean;
import com.graduation.android.readme.utils.TimeUtil;
import com.graduation.android.readme.wiki.bean.FreshNewsArticleBean;
import com.graduation.android.readme.wiki.mvp.NewsContract;
import com.graduation.android.readme.wiki.mvp.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 阅读详情页
 */
public class ReadActivity extends BaseActivity<NewsContract.Presenter, NewsContract.View> implements NewsContract.View {
    private static final String DATA = "data";
    @BindView(R.id.iv_logo)
    ImageView mIvTop;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_excerpt)
    TextView mTvExcerpt;
    @BindView(R.id.wv_contnet)
    WebView mWebView;//webView加载

    @BindView(R.id.ll_content)
    LinearLayout llContent;
    FreshBean.PostsBean postsBean;
    FreshNewsArticleBean newsArticleBean;
    private ImageLoadConfig build;
    @BindView(R.id.progress_wheel)
    ProgressBar progressWheel;//加载进度条
    public static void launch(Context context, FreshBean.PostsBean postsBean) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra(DATA, postsBean);
        context.startActivity(intent);
//        context.startActivity(intent,
//                ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, "topview").toBundle());
    }


    /**
     * 设置webView 属性
     */
    private void setWebViewSetting() {
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl("file:///android_asset/jd/post_detail.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getData(postsBean.getId());
            }
        });
    }


    /**
     * 获取数据
     *
     * @param id
     */
    private void getData(int id) {
        mPresenter.getFreshNewsArticle(id);
    }


    @Override
    protected NewsContract.Presenter initPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        if (getIntent().getExtras() == null) return;
        postsBean = (FreshBean.PostsBean) getIntent().getSerializableExtra(DATA);
        if (postsBean == null) return;

        mTvTitle.setText(postsBean.getTitle());

        FreshBean.PostsBean.AuthorBean authorBean = postsBean.getAuthor();

        if (authorBean != null) {
            mTvAuthor.setText(authorBean.getName()
                    + "  "
                    + TimeUtil.getTimestampString(TimeUtil.string2Date(postsBean.getDate(), "yyyy-MM-dd HH:mm:ss")));

        }
        mTvExcerpt.setText(postsBean.getExcerpt());
        build = new ImageLoadConfig.Builder()
                .setPlaceHolderResId(R.drawable.replaceable_drawable_elite_album_default)
                .setErrorResId(R.drawable.replaceable_drawable_elite_album_default)
                .build();
        ImageLoaderManager.getInstance().loadImage(mIvTop, postsBean.getImgUrl(), build);
        setWebViewSetting();
    }

    @Override
    public void loadData() {
        getData(postsBean.getId());
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
    public void loadFreshNews(List<FreshBean.PostsBean> postsBean) {

    }

    @Override
    public void loadNewsData(List<NewsDetail> itemBeanList) {

    }

    @Override
    public void loadFreshNewsSuccess(FreshNewsArticleBean articleBean) {
        newsArticleBean = articleBean;
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                progressWheel.setVisibility(View.GONE);
                final String content = articleBean.getPost().getContent();
                String url = "javascript:show_content(\'" + content + "\')";
                mWebView.loadUrl(url);//webView 加载
            }
        });
    }
}
