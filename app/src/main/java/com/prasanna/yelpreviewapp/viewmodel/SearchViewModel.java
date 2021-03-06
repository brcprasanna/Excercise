package com.prasanna.yelpreviewapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.repository.RepositoryResponseBase;
import com.prasanna.yelpreviewapp.repository.SearchRepository;
import com.prasanna.yelpreviewapp.utils.AppConstants;

/**
 * Created by Prasanna V on 2018-10-16.
 */

public class SearchViewModel extends ViewModel {

    private LiveData<RepositoryResponseBase<CategoryResponse>> mCategoryResponseLiveData;
    private LiveData<RepositoryResponseBase<BusinessSearchResponse>> mBusinessResponseLiveData;

    public void initCategory() {
        mCategoryResponseLiveData = SearchRepository.getInstance().getCategories();
    }

    public LiveData<RepositoryResponseBase<CategoryResponse>> getCategoryResponseLiveData() {
        return mCategoryResponseLiveData;
    }

    public void initBusiness(String newText, String priceRange, String Categories) {
        mBusinessResponseLiveData = SearchRepository.getInstance().getBusiness(newText, priceRange, Categories);
    }

    public LiveData<RepositoryResponseBase<BusinessSearchResponse>> getBusinessResponseLiveData() {
        return mBusinessResponseLiveData;
    }

    public String getSpinnerRangeText(String spinnerRangeText, String resString) {
        if (spinnerRangeText.equalsIgnoreCase(resString)) {
            return AppConstants.EMPTY;
        } else {
            return spinnerRangeText;
        }
    }
}
