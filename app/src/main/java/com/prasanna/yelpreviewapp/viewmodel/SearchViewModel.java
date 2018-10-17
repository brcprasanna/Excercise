package com.prasanna.yelpreviewapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.repository.RepositoryResponse;
import com.prasanna.yelpreviewapp.repository.SearchRepository;

/**
 * Created by rampreethajasmi on 2018-10-16.
 */

public class SearchViewModel extends ViewModel {

    private LiveData<RepositoryResponse<CategoryResponse>> mCategoryResponseLiveData;
    private LiveData<RepositoryResponse<BusinessSearchResponse>> mBusinessResponseLiveData;

    public void initCategory() {
        mCategoryResponseLiveData = SearchRepository.getInstance().getCategories();
    }

    public LiveData<RepositoryResponse<CategoryResponse>> getCategoryResponseLiveData() {
        return mCategoryResponseLiveData;
    }

    public void initBusiness(String newText, String priceRange, String Categories) {
        mBusinessResponseLiveData = SearchRepository.getInstance().getBusiness(newText, priceRange, Categories);
    }

    public LiveData<RepositoryResponse<BusinessSearchResponse>> getBusinessResponseLiveData() {
        return mBusinessResponseLiveData;
    }


    public boolean validatePriceRange(String strRange) {
        int range = Integer.parseInt(strRange);
        if (range <= 0 && range >= 5) {
            return false;
        }
        return true;
    }
}
