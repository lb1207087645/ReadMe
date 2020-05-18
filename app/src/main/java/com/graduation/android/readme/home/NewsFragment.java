package com.graduation.android.readme.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.adapter.animation.ScaleInAnimation;
import com.graduation.android.readme.base.mvp.BaseFragment;
import com.graduation.android.readme.base.network.ErrorEntity;
import com.graduation.android.readme.base.utils.L;
import com.graduation.android.readme.home.adapter.NewListAdapter;
import com.graduation.android.readme.home.bean.NewSummaryModel;
import com.graduation.android.readme.home.bean.NewsSummary;
import com.graduation.android.readme.home.mvp.NewsListContract;
import com.graduation.android.readme.home.mvp.NewsListPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 新闻页
 */
public class NewsFragment extends BaseFragment<NewsListContract.Presenter, NewsListContract.View> implements NewsListContract.View {


    private List<NewSummaryModel> datas = new ArrayList<>();

    private NewListAdapter newListAdapter;
    private RecyclerView irc;

    private String mNewsId;
    private String mNewsType;

    private int mStartPage = 0;


    private static final String TAG = "NewsFragment";


    @Override
    protected NewsListContract.Presenter initPresenter() {
        return new NewsListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void loadData() {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        irc = view.findViewById(R.id.irc);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(AppConstant.NEWS_ID);
            mNewsType = getArguments().getString(AppConstant.NEWS_TYPE);
        }
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        newListAdapter = new NewListAdapter(datas);
        newListAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(newListAdapter);
        //数据为空才重新发起请求
        if (newListAdapter.getData().size() <= 0) {
            mStartPage = 0;
            mPresenter.getNewsListDataRequest(mNewsType, mNewsId, mStartPage);

            L.d(TAG,"initView");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getNewsListDataRequest(mNewsType, mNewsId, mStartPage);
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

    @Override
    public void showErr(ErrorEntity err) {

    }

    @Override
    public void showErrorView(String errorType) {

    }


    @Override
    public void returnNewsListData(List<NewsSummary> newsSummaries) {
        List<NewSummaryModel> list = changeServeData(newsSummaries);
        newListAdapter.getData().clear();
        newListAdapter.addData(list);
    }


    private List<NewSummaryModel> changeServeData(List<NewsSummary> newsSummaries) {
        List<NewSummaryModel> studyBeanList = new ArrayList<>();
        for (int i = 0; i < newsSummaries.size(); i++) {
            NewsSummary newsSummary = newsSummaries.get(i);
            NewSummaryModel newSummaryModel;
            if (!TextUtils.isEmpty(newsSummary.getDigest())) {
                newSummaryModel = new NewSummaryModel(NewSummaryModel.TYPE_ITEM, newsSummary);
            } else {
                newSummaryModel = new NewSummaryModel(NewSummaryModel.TYPE_PHOTO_ITEM, newsSummary);
            }
            studyBeanList.add(newSummaryModel);
        }
        return studyBeanList;

    }


}