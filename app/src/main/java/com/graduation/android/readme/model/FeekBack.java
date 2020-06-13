package com.graduation.android.readme.model;

import cn.bmob.v3.BmobObject;


/**
 * 反馈
 */
public class FeekBack extends BmobObject {

    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
