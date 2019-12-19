package com.graduation.android.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.graduation.android.R;
import com.graduation.android.base.mvp.BaseMvpFragment;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.base.utils.L;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.home.mvp.HomeContractTest;

import java.util.List;


/**
 * 测试的HomeFragment
 */
public class HomeFragment extends BaseMvpFragment<HomeContractTest.Presenter, HomeContractTest.View> implements HomeContractTest.View {
//
//    private SwipeRefreshLayout srl;
//    private RecyclerView rv;
//
//    private int curPage = 1;
//    private ArrayList<DesignRes> datas = new ArrayList<>();
//    private LoadMoreAdapter adapter;


    private TextView tvHello;

    private static final String TAG = "HomeFragment";

    @Override
    protected HomeContractTest.Presenter initPresenter() {
       // return new HomePresenterTest(mActivity);
         return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    public void loadData() {
      //  mPresenter.getCall();//加载请求
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvHello = view.findViewById(R.id.tv_hello);
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
    public void loadSimple(String out) {
        tvHello.setText(out);
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
    public void showEmptyView(int imageId) {

    }

    @Override
    public void showContentView() {

    }

}
