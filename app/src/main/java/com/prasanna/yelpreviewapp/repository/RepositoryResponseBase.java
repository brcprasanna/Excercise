package com.prasanna.yelpreviewapp.repository;

import com.prasanna.yelpreviewapp.utils.AppConstants;

public class RepositoryResponseBase<T> {
    private T data;
    private AppConstants.ResponseStatus status;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AppConstants.ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(AppConstants.ResponseStatus status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}