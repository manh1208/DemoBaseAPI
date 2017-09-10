package com.manhnv.demobaseapi;

import com.google.gson.annotations.SerializedName;
import com.tiny.rush.BaseResponseModel;

import java.util.List;

/********************************************************
 * File Name : ResponseModel.java
 * Author : ManhNV
 * Date : 2017-09-06
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-06
 ********************************************************/


public class ResponseModel<T> extends BaseResponseModel {
    @SerializedName("result")
    private T result;
    @SerializedName("succeed")
    private boolean succeed;
    @SerializedName("errorMessage")
    private List<String> errorMessage;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
