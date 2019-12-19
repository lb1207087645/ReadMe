package com.graduation.android.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.android.R;
import com.graduation.android.adapter.NewsAdapter;
import com.graduation.android.base.mvp.BaseFragment;
import com.graduation.android.base.image.ImageLoadConfig;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.base.utils.L;
import com.graduation.android.bean.NewsDetail;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.model.FreshBean;
import com.graduation.android.news.mvp.NewsContractTest;
import com.graduation.android.news.mvp.NewsPresenterTest;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;


/**
 * 新闻的NewsFragment
 */
public class NewsFragment extends BaseFragment<NewsContractTest.Presenter, NewsContractTest.View> implements NewsContractTest.View {
//
//    private SwipeRefreshLayout srl;
//    private RecyclerView rv;
//
//    private int curPage = 1;
//    private ArrayList<DesignRes> datas = new ArrayList<>();
//    private LoadMoreAdapter adapter;


    private TextView tvHello;


    ImageView iv_image;//图片显示

    //    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private static final String TAG = "NewsFragment";

    private ImageLoadConfig appTopicTitleBuild;


    private SwipyRefreshLayout swipyRefreshLayout;

    @Override
    protected NewsContractTest.Presenter initPresenter() {
        return new NewsPresenterTest(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_news;
    }

    @Override
    public void loadData() {
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {//简单下拉刷新
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                mPresenter.getFreshNews(0);

            }
        });

    }


    @Override
    public void loadListSuccess(int page, List<DesignRes> datas) {
//        curPage = page;
//        if (curPage == 1) {
//            this.datas.clear();
//        }
//        this.datas.addAll(datas);
//
//        // 设置是否已加载完全部数据状态
//        adapter.setStatus(datas.size() == CommonConstants.COUNT_OF_PAGE
//                ? LoadMoreAdapter.STATUS_HAVE_MORE : LoadMoreAdapter.STATUS_LOADED_ALL);
//        adapter.notifyDataSetChanged();

        // iv_image.


    }

    @Override
    public void loadSimple(List<NewsDetail> list) {
        for (int i = 0; i < list.size(); i++) {
            Log.d("sddsd", list.get(i) + "");

        }


    }

    @Override
    public void loadFreshNews(List<FreshBean.PostsBean> postsBean) {
        swipyRefreshLayout.setRefreshing(false);//需要加入setRefreshing false ，不然不能用
        NewsAdapter newsAdapter = new NewsAdapter(postsBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(newsAdapter);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress() {
//        srl.post(new Runnable() {
//            @Override
//            public void run() {
//                srl.setRefreshing(true);
//            }
//        });
    }

    @Override
    public void dismissProgress() {
//        srl.setRefreshing(false);
    }

    @Override
    public void showTip(String message) {
        showToast(message);
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void hideLoadingDialog() {

    }


    @Override
    public void showErr(ErrorEntity err) {
        L.d(TAG, err.errMsg);


    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showErrorView(String errorType) {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvHello = view.findViewById(R.id.tv_hello);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        iv_image = view.findViewById(R.id.iv_image);
        swipyRefreshLayout = view.findViewById(R.id.refresh_layout);
//
        appTopicTitleBuild = new ImageLoadConfig.Builder()
                .setPlaceHolderResId(R.drawable.recommend_item_app_topic_title_default)
                .setErrorResId(R.drawable.recommend_item_app_topic_title_default)
                .build();


//
//        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
//        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.pullToLoadList();
//            }
//        });
//
//        rv = (RecyclerView) view.findViewById(R.id.rv);
//        rv.addItemDecoration(new GridSpacingDecorator(DensityUtils.dp2px(mActivity, 8)));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
//        rv.setLayoutManager(gridLayoutManager);
//
//        adapter = new LoadMoreAdapter(rv,
//                new DesignResAdapter(mActivity, datas),
//                new LoadMoreAdapter.OnLoadMoreListener() {//加载更多
//                    @Override
//                    public void onLoadMore() {
//                        mPresenter.loadList(curPage + 1);
//                    }
//                });
//        rv.setAdapter(adapter);
    }

    @Override
    public void showEmptyView(int imageId) {

    }

    @Override
    public void showContentView() {

    }

}
