package com.graduation.android.readme.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.graduation.android.readme.R;
import com.graduation.android.readme.app.AppConstant;
import com.graduation.android.readme.base.adapter.BaseFragmentAdapter;
import com.graduation.android.readme.base.mvp.IPresenter;
import com.graduation.android.readme.basemodule.BaseActivity;
import com.graduation.android.readme.home.bean.NewsPhotoDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * des:图文详情
 * Created by xsf
 * on 2016.09.9:54
 */
public class NewsPhotoDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.photo_detail_title_tv)
    TextView photoDetailTitleTv;

    private List<Fragment> mPhotoDetailFragmentList = new ArrayList<>();
    private NewsPhotoDetail mNewsPhotoDetail;


    /**
     * 入口
     *
     * @param context
     * @param mNewsPhotoDetail
     */
    public static void startAction(Context context, NewsPhotoDetail mNewsPhotoDetail) {
        Intent intent = new Intent(context, NewsPhotoDetailActivity.class);
        intent.putExtra(AppConstant.PHOTO_DETAIL, mNewsPhotoDetail);
        context.startActivity(intent);
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_news_photo_detail;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        mNewsPhotoDetail = getIntent().getParcelableExtra(AppConstant.PHOTO_DETAIL);
        createFragment(mNewsPhotoDetail);
        initViewPager();
        setPhotoDetailTitle(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    @Override
    public void loadData() {

    }



    @Override
    protected void onStart() {
        super.onStart();
        //监听图片tab点击
//        mRxManager.on(AppConstant.PHOTO_TAB_CLICK, new Action1<Object>() {
//
//            @Override
//            public void call(Object o) {
//                if (photoDetailTitleTv.getVisibility() == View.VISIBLE) {
//                    startAnimation(View.GONE, 0.9f, 0.5f);
//                } else {
//                    photoDetailTitleTv.setVisibility(View.VISIBLE);
//                    startAnimation(View.VISIBLE, 0.5f, 0.9f);
//                }
//            }
//        });
    }

    private void startAnimation(final int endState, float startValue, float endValue) {
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(photoDetailTitleTv, "alpha", startValue, endValue)
                .setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                photoDetailTitleTv.setVisibility(endState);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }


    private void createFragment(NewsPhotoDetail newsPhotoDetail) {
        mPhotoDetailFragmentList.clear();
        for (NewsPhotoDetail.Picture picture : newsPhotoDetail.getPictures()) {
            PhotoDetailFragment fragment = new PhotoDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.PHOTO_DETAIL_IMGSRC, picture.getImgSrc());
            fragment.setArguments(bundle);
            mPhotoDetailFragmentList.add(fragment);
        }
    }

    private void initViewPager() {
        BaseFragmentAdapter photoPagerAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mPhotoDetailFragmentList);
        viewpager.setAdapter(photoPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPhotoDetailTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setPhotoDetailTitle(int position) {
        String title = getTitle(position);
        photoDetailTitleTv.setText(getString(R.string.photo_detail_title, position + 1,
                mPhotoDetailFragmentList.size(), title));
    }

    private String getTitle(int position) {
        String title = mNewsPhotoDetail.getPictures().get(position).getTitle();
        if (title == null) {
            title = mNewsPhotoDetail.getTitle();
        }
        return title;
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
}
