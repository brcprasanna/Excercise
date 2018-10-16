package com.prasanna.yelpreviewapp.utils;

import okhttp3.Response;

/**
 * Interface to callback to activity from service layer
 */
public interface CallBackToView {
    /**
     * ill be Called for success callback
     *
     * @param responseModel
     */
    void onSuccess(Response responseModel);

    /**
     * Will be called on Error callback
     *
     * @param errorMsg
     */
    void onFailure(String errorMsg);
}
