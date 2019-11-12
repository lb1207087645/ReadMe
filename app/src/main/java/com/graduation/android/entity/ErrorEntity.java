package com.graduation.android.entity;

/**
 * Created by lytjackson on 2017/8/11.
 * email:lytjackson@gmail.com
 */
public class ErrorEntity {

    public String errType;
    public String errMsg;
    public String message;

    public ErrorEntity(String errType, String errMsg) {
        this.errType = errType;
        this.errMsg = errMsg;
    }

    public ErrorEntity(String errType, String errMsg, String message) {
        this.errType = errType;
        this.errMsg = errMsg;
        this.message = message;
    }
}
