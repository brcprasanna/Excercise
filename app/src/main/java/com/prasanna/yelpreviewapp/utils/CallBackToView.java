package com.prasanna.yelpreviewapp.utils;

/**
 * Interface to callback to activity from service layer
 */
public interface CallBackToView {
    /**
     * ill be Called for success callback
     *
     * @param responseModel
     */
    void onSuccess(String responseModel);

    /**
     * Will be called on Error callback
     *
     * @param errorMsg
     */
    void onFailure(String errorMsg);
}
