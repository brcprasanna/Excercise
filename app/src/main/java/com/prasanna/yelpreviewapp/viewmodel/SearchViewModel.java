package com.prasanna.yelpreviewapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.repository.RepositoryResponse;
import com.prasanna.yelpreviewapp.repository.SearchRepository;

/**
 * Created by rampreethajasmi on 2018-10-16.
 */

public class SearchViewModel extends ViewModel {

    private LiveData<RepositoryResponse<CategoryResponse>> mCategoryResponseLiveData;

    public void init(String filterText) {
        mCategoryResponseLiveData = SearchRepository.getInstance().getCategories(filterText);
    }

    public LiveData<RepositoryResponse<CategoryResponse>> getmCategoryResponseLiveData() {
        return mCategoryResponseLiveData;
    }
}
