package com.graduation.android.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.android.R;
import com.graduation.android.adapter.MainAdapter;
import com.graduation.android.base.BaseActivity;
import com.graduation.android.base.BaseMvpActivity;
import com.graduation.android.base.IPresenter;
import com.graduation.android.home.HomeFragment;
import com.graduation.android.mine.MineFragment;
import com.graduation.android.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;


/**
 * 首页
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.main_bottom_tab_layout)
    TabLayout mainTabLayout;


    @BindView(R.id.main_viewpager)
    ViewPager mainViewPager;


    /**
     * tab 页String
     */
    private String[] tabStrArray = {"首页", "新闻", "天气", "我的"};

    private MainAdapter vpAdapter;


    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        initTab();
    }


    /**
     * 初始化tab页
     */
    private void initTab() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new HomeFragment());
        fragments.add(new MineFragment());
        vpAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        mainViewPager.setAdapter(vpAdapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
        mainViewPager.setOffscreenPageLimit(vpAdapter.getCount());
        mainViewPager.setCurrentItem(0);


//初始化每个tab页
        for (int i = 0; i < vpAdapter.getCount(); i++) {

            String tabStr = tabStrArray[i];


            // 获得每一个tab
            TabLayout.Tab tab = mainTabLayout.getTabAt(i);
            if (tab != null) {
                // 给每一个tab设置view
                tab.setCustomView(R.layout.main_item_activity_main_tab);
            }
            if (i == 0) {
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    // 设置第一个tab的TextView是被选择的样式
                    tabView.findViewById(R.id.main_tab_item).setSelected(true);
                }
            }
            //设置tabLayout点击事件
            if (tab.getCustomView() != null) {
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                tabView.setOnClickListener(mTabOnClickListener);
            }
            View tabView = tab.getCustomView();
            ImageView imageView;
            TextView textView;
            View view;
            if (tabView != null) {
                imageView = tabView.findViewById(R.id.main_tab_icon);
                textView = tabView.findViewById(R.id.main_tab_title);
                textView.setText(tabStr);

                switch (tabStr) {
                    case "首页":
                        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.main_recommend_icon_selector));
                        break;
                    case "新闻":
                        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.main_news_icon_selector));
                        break;
                    case "天气":
                        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.main_weather_icon_selector));
                        break;
                    case "我的":
                        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.main_mine_icon_selector));
                        break;
                    default:
                        break;
                }
            }

        }
        bindOnTabSelectedListener();
    }


    /**
     * tab页选中事件
     */
    private void bindOnTabSelectedListener() {
        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    tabView.findViewById(R.id.main_tab_item).setSelected(true);
                }
                mainViewPager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    tabView.findViewById(R.id.main_tab_item).setSelected(false);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /**
     * TabLayout点击事件处理
     */
    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            TabLayout.Tab tab = mainTabLayout.getTabAt(position);
//            if (tab != null) {
//                L.d(TAG, "position==" + position + "tab.isSelected()" + tab.isSelected());
//                //点击好看
//                if (position == 0 && tab.isSelected()) {
//                    EventBus.getDefault().post(new SwitchEvent());
//                }
//            }
        }
    };

    @Override
    public boolean isActive() {
        return false;
    }


    @Override
    protected boolean isUseToolbar() {
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
}
