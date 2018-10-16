package com.prasanna.yelpreviewapp.repository;

import com.prasanna.yelpreviewapp.utils.AppConstants;

public class RepositoryResponse<T> {
    private T data;
    private AppConstants.ResponseStatus status;
    private String errorMsg;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setStatus(AppConstants.ResponseStatus status) {
        this.status = status;
    }

    public AppConstants.ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}