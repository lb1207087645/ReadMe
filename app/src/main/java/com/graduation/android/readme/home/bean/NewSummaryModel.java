package com.graduation.android.readme.home.bean;

import com.graduation.android.readme.base.entity.MultiItemEntity;
import com.graduation.android.readme.base.mvp.BaseModel;

import java.io.Serializable;

public class NewSummaryModel extends BaseModel implements MultiItemEntity {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;

    private NewsSummary data;
    private int itemType;

    public NewSummaryModel(int itemType, NewsSummary data) {
        this.itemType = itemType;
        this.data = data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public NewsSummary getData() {
        return data;
    }

    public void setData(NewsSummary data) {
        this.data = data;
    }
}
