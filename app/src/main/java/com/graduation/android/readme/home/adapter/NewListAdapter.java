package com.graduation.android.readme.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.graduation.android.readme.R;
import com.graduation.android.readme.base.BaseApplication;
import com.graduation.android.readme.base.adapter.BaseMultiItemQuickAdapter;
import com.graduation.android.readme.base.adapter.BaseViewHolder;
import com.graduation.android.readme.base.image.ImageLoadConfig;
import com.graduation.android.readme.base.image.ImageLoaderManager;
import com.graduation.android.readme.base.utils.DensityUtil;
import com.graduation.android.readme.home.NewsDetailActivity;
import com.graduation.android.readme.home.NewsPhotoDetailActivity;
import com.graduation.android.readme.home.bean.NewSummaryModel;
import com.graduation.android.readme.home.bean.NewsPhotoDetail;
import com.graduation.android.readme.home.bean.NewsSummary;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 新闻列表页
 */
public class NewListAdapter extends BaseMultiItemQuickAdapter<NewSummaryModel, BaseViewHolder> {
    private List<NewSummaryModel> items;
    private ImageLoadConfig build;


    public NewListAdapter(@Nullable List<NewSummaryModel> data) {
        super(data);
        build = new ImageLoadConfig.Builder()
                .setPlaceHolderResId(R.drawable.replaceable_drawable_elite_album_default)
                .setErrorResId(R.drawable.replaceable_drawable_elite_album_default)
                .build();
        // 普通样式
        addItemType(NewSummaryModel.TYPE_ITEM, R.layout.item_news);
//图文样式
        addItemType(NewSummaryModel.TYPE_PHOTO_ITEM, R.layout.item_news_photo);
    }


    @Override
    protected void convert(BaseViewHolder helper, NewSummaryModel item) {
        if (item.getItemType() == NewSummaryModel.TYPE_ITEM) {
            setItemValues(helper, item.getData(), helper.getAdapterPosition());
        } else if (item.getItemType() == NewSummaryModel.TYPE_PHOTO_ITEM) {
            setPhotoItemValues(helper, item.getData(), helper.getAdapterPosition());
        }

    }

    /**
     * 普通样式
     *
     * @param holder
     * @param newsSummary
     * @param position
     */
    private void setItemValues(final BaseViewHolder holder, final NewsSummary newsSummary, final int position) {
        String title = newsSummary.getLtitle();
        if (title == null) {
            title = newsSummary.getTitle();
        }
        String ptime = newsSummary.getPtime();
        String digest = newsSummary.getDigest();
        String imgSrc = newsSummary.getImgsrc();
        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        holder.setText(R.id.news_summary_digest_tv, digest);
        ImageLoaderManager.getInstance().loadImage(holder.getView(R.id.news_summary_photo_iv), imgSrc, build);
        holder.setOnClickListener(R.id.rl_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailActivity.startAction(mContext, holder.getView(R.id.news_summary_photo_iv), newsSummary.getPostid(), newsSummary.getImgsrc());
            }
        });
    }

    /**
     * 图文样式
     *
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(BaseViewHolder holder, final NewsSummary newsSummary, int position) {
        String title = newsSummary.getTitle();
        String ptime = newsSummary.getPtime();
        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        setImageView(holder, newsSummary);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsPhotoDetailActivity.startAction(mContext, getPhotoDetail(newsSummary));
            }
        });
    }

    private NewsPhotoDetail getPhotoDetail(NewsSummary newsSummary) {
        NewsPhotoDetail newsPhotoDetail = new NewsPhotoDetail();
        newsPhotoDetail.setTitle(newsSummary.getTitle());
        setPictures(newsSummary, newsPhotoDetail);
        return newsPhotoDetail;
    }

    private void setPictures(NewsSummary newsSummary, NewsPhotoDetail newsPhotoDetail) {
        List<NewsPhotoDetail.Picture> pictureList = new ArrayList<>();
        if (newsSummary.getAds() != null) {
            for (NewsSummary.AdsBean entity : newsSummary.getAds()) {
                setValuesAndAddToList(pictureList, entity.getTitle(), entity.getImgsrc());
            }
        } else if (newsSummary.getImgextra() != null) {
            for (NewsSummary.ImgextraBean entity : newsSummary.getImgextra()) {
                setValuesAndAddToList(pictureList, null, entity.getImgsrc());
            }
        } else {
            setValuesAndAddToList(pictureList, null, newsSummary.getImgsrc());
        }

        newsPhotoDetail.setPictures(pictureList);
    }

    private void setValuesAndAddToList(List<NewsPhotoDetail.Picture> pictureList, String title, String imgsrc) {
        NewsPhotoDetail.Picture picture = new NewsPhotoDetail.Picture();
        if (title != null) {
            picture.setTitle(title);
        }
        picture.setImgSrc(imgsrc);

        pictureList.add(picture);
    }

    private void setImageView(BaseViewHolder holder, NewsSummary newsSummary) {
        int PhotoThreeHeight = (int) DensityUtil.dp2px(90);
        int PhotoTwoHeight = (int) DensityUtil.dp2px(120);
        int PhotoOneHeight = (int) DensityUtil.dp2px(150);

        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;
        LinearLayout news_summary_photo_iv_group = holder.getView(R.id.news_summary_photo_iv_group);
        ViewGroup.LayoutParams layoutParams = news_summary_photo_iv_group.getLayoutParams();

        if (newsSummary.getAds() != null) {
            List<NewsSummary.AdsBean> adsBeanList = newsSummary.getAds();
            int size = adsBeanList.size();
            if (size >= 3) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();
                layoutParams.height = PhotoThreeHeight;
                holder.setText(R.id.news_summary_title_tv, BaseApplication.getContext()
                        .getString(R.string.photo_collections, adsBeanList.get(0).getTitle()));
            } else if (size >= 2) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else if (newsSummary.getImgextra() != null) {
            int size = newsSummary.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsSummary.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else {
            imgSrcLeft = newsSummary.getImgsrc();

            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        news_summary_photo_iv_group.setLayoutParams(layoutParams);
    }

    private void setPhotoImageView(BaseViewHolder holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight) {
        if (imgSrcLeft != null) {
            holder.setVisible(R.id.news_summary_photo_iv_left, true);
            ImageLoaderManager.getInstance().loadImage(holder.getView(R.id.news_summary_photo_iv_left), imgSrcLeft, build);


        } else {
            holder.setVisible(R.id.news_summary_photo_iv_left, false);
        }
        if (imgSrcMiddle != null) {
            holder.setVisible(R.id.news_summary_photo_iv_middle, true);
            ImageLoaderManager.getInstance().loadImage(holder.getView(R.id.news_summary_photo_iv_middle), imgSrcMiddle, build);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_middle, false);
        }
        if (imgSrcRight != null) {
            holder.setVisible(R.id.news_summary_photo_iv_right, true);
            ImageLoaderManager.getInstance().loadImage(holder.getView(R.id.news_summary_photo_iv_right), imgSrcRight, build);


        } else {
            holder.setVisible(R.id.news_summary_photo_iv_right, false);
        }
    }
}