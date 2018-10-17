package com.prasanna.yelpreviewapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.repository.BusinessListRepository;
import com.prasanna.yelpreviewapp.repository.RepositoryResponseBase;

/**
 * Created by Prasanna V on 2018-10-16.
 */

public class BusinessListViewModel extends ViewModel {

    private LiveData<RepositoryResponseBase<BusinessSearchResponse>> mBusinessResponseLiveData;

    public void initBusiness(String newText, String priceRange, String Categories, int limit, int offset) {
        mBusinessResponseLiveData = BusinessListRepository.getInstance().getBusinessWithLimitAndOffset(newText, priceRange, Categories, limit, offset);
    }

    public LiveData<RepositoryResponseBase<BusinessSearchResponse>> getBusinessResponseLiveData() {
        return mBusinessResponseLiveData;
    }

}
