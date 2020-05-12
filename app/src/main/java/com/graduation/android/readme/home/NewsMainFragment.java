package com.graduation.android.readme.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.adapter.BaseFragmentAdapter;
import com.graduation.android.readme.base.mvp.BaseFragment;
import com.graduation.android.readme.base.mvp.BaseMvpFragment;
import com.graduation.android.readme.base.network.ErrorEntity;
import com.graduation.android.readme.base.utils.L;
import com.graduation.android.readme.bean.NewsChannelTable;
import com.graduation.android.readme.home.mvp.NewsMainContract;
import com.graduation.android.readme.home.mvp.NewsMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 新闻首页
 */
public class NewsMainFragment extends BaseFragment<NewsMainContract.Presenter, NewsMainContract.View> implements NewsMainContract.View {


    private static final String TAG = "NewsMainFragment";
    TabLayout tabs;
    ViewPager viewPager;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected NewsMainContract.Presenter initPresenter() {
        return new NewsMainPresenter(mActivity);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frag_news_main;
    }

    @Override
    public void loadData() {
        mPresenter.getChannelsRequest();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void setPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 创建底部新闻列表页
     *
     * @param newsChannel
     * @return
     */
    private NewsFragment createListFragments(NewsChannelTable newsChannel) {
        NewsFragment fragment = new NewsFragment();//每次fragment 数据
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.NEWS_ID, newsChannel.getNewsChannelId());
        bundle.putString(AppConstant.NEWS_TYPE, newsChannel.getNewsChannelType());
        bundle.putInt(AppConstant.CHANNEL_POSITION, newsChannel.getNewsChannelIndex());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.news_view_pager);
        tabs = view.findViewById(R.id.tabs);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

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


    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        if (newsChannelsMine != null) {
            List<String> channelNames = new ArrayList<>();//渠道名
            List<Fragment> mNewsFragmentList = new ArrayList<>();//新闻列表页
            for (int i = 0; i < newsChannelsMine.size(); i++) {
                channelNames.add(newsChannelsMine.get(i).getNewsChannelName());
                mNewsFragmentList.add(createListFragments(newsChannelsMine.get(i)));
            }
            if (fragmentAdapter == null) {
                fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
            } else {
                //刷新fragment
                fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
            }
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
//            MyUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }
}
