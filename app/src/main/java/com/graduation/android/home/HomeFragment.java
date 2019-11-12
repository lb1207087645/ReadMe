package com.graduation.android.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.graduation.android.R;
import com.graduation.android.base.BaseMvpFragment;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.entity.ErrorEntity;
import com.graduation.android.mvp.HomeContractTest;
import com.graduation.android.mvp.HomePresenterTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试的HomeFragment
 */
public class HomeFragment extends BaseMvpFragment<HomeContractTest.Presenter, HomeContractTest.View> implements HomeContractTest.View {

    private SwipeRefreshLayout srl;
    private RecyclerView rv;

    private int curPage = 1;
    private ArrayList<DesignRes> datas = new ArrayList<>();
//    private LoadMoreAdapter adapter;


    @Override
    protected HomeContractTest.Presenter initPresenter() {
//        return new HomePresenterTest();
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void loadData() {
//        mPresenter.loadList(1);//加载请求
    }

    @Override
    protected void initView(View view) {
//        new TitleBuilder(view).setTitleText(getString(R.string.tab1));
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
//        rv.addItemDecoration(new GridSpacingDecorator(DisplayUtils.dp2px(mActivity, 8)));
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
        srl.setRefreshing(false);
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
    public void showEmptyView(int imageId) {

    }

    @Override
    public void showContentView() {

    }

}
