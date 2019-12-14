package com.graduation.android.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.graduation.android.R;
import com.graduation.android.base.adapter.BaseMultiItemQuickAdapter;
import com.graduation.android.base.adapter.BaseViewHolder;
import com.graduation.android.base.image.ImageLoadConfig;
import com.graduation.android.base.image.ImageLoaderManager;
import com.graduation.android.bean.NewsDetail;
import com.graduation.android.model.FreshBean;
import com.graduation.android.model.FreshNewsBean;

import java.util.List;

/**
 * 新闻adapter
 */
public class NewsAdapter extends BaseMultiItemQuickAdapter<FreshBean.PostsBean, BaseViewHolder> {

    private ImageLoadConfig build;


    public NewsAdapter(@Nullable List<FreshBean.PostsBean> data) {
        super(data);
        build = new ImageLoadConfig.Builder()
                .setPlaceHolderResId(R.drawable.replaceable_drawable_elite_album_default)
                .setErrorResId(R.drawable.replaceable_drawable_elite_album_default)
                .build();
        addItemType(NewsDetail.ItemBean.TYPE_DOC_TITLEIMG, R.layout.item_freshnews);//
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, FreshBean.PostsBean item) {
        viewHolder.setText(R.id.tv_title, item.getTitle());
        //   viewHolder.setText(R.id.tv_info, item.getAuthor().getName());
        viewHolder.setText(R.id.tv_commnetsize, item.getComment_count() + "评论");
        //   ImageLoaderManager.getInstance().loadImage(viewHolder.getView(R.id.iv_logo), item.getUrl(), build);

    }
}
