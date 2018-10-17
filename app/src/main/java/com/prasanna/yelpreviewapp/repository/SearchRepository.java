package com.prasanna.yelpreviewapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.utils.AppConstants;
import com.prasanna.yelpreviewapp.utils.CallBackToView;
import com.prasanna.yelpreviewapp.utils.DataManager;

import java.io.IOException;

/**
 * Created by Prasanna V on 2018-10-16.
 */

public class SearchRepository {
    private static SearchRepository sSearchRepositoryInstance;

    private SearchRepository() {

    }

    public static SearchRepository getInstance() {
        if (sSearchRepositoryInstance == null) {
            sSearchRepositoryInstance = new SearchRepository();
        }
        return sSearchRepositoryInstance;
    }

    public LiveData<RepositoryResponseBase<CategoryResponse>> getCategories() {
        final MutableLiveData<RepositoryResponseBase<CategoryResponse>> liveData = new MutableLiveData();
        RepositoryResponseBase<CategoryResponse> response = new RepositoryResponseBase<>();
        //Category
        try {
            DataManager.getInstance().getCategories(new CallBackToView() {
                @Override
                public void onSuccess(final String responseModel) {
                    CategoryResponse categoryResponse = new Gson().fromJson(responseModel, CategoryResponse.class);
                    response.setStatus(AppConstants.ResponseStatus.RESPONSE_SUCCESS);
                    response.setData(categoryResponse);
                    liveData.postValue(response);
                }

                @Override
                public void onFailure(final String errorMsg) {
                    response.setStatus(AppConstants.ResponseStatus.RESPONSE_ERROR);
                    response.setErrorMsg(errorMsg);
                    liveData.postValue(response);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liveData;
    }

    public LiveData<RepositoryResponseBase<BusinessSearchResponse>> getBusiness(String filterText, String priceRange, String categories) {
        final MutableLiveData<RepositoryResponseBase<BusinessSearchResponse>> liveData = new MutableLiveData();
        RepositoryResponseBase<BusinessSearchResponse> response = new RepositoryResponseBase<>();
        //Search View
        try {
            DataManager.getInstance().getBusinessSearch(filterText, priceRange, categories, new CallBackToView() {
                @Override
                public void onSuccess(final String responseModel) {
                    BusinessSearchResponse businessSearchResponse = new Gson().fromJson(responseModel, BusinessSearchResponse.class);
                    response.setStatus(AppConstants.ResponseStatus.RESPONSE_SUCCESS);
                    response.setData(businessSearchResponse);
                    liveData.postValue(response);
                }

                @Override
                public void onFailure(final String errorMsg) {
                    response.setStatus(AppConstants.ResponseStatus.RESPONSE_ERROR);
                    response.setErrorMsg(errorMsg);
                    liveData.postValue(response);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liveData;
    }
}
