package com.prasanna.yelpreviewapp.utils;

import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;

import java.io.IOException;

public class DataManager {
    private static DataManager dataManager = new DataManager();
    private NetworkManager networkManager;

    /**
     * Private constructor to prevent being created by other object
     */
    private DataManager() {
    }

    /**
     * Get singleton class instance
     */
    public static DataManager getInstance() {
        return dataManager;
    }

    /**
     * Class initialization method
     * ATTENTION: call this function at application level prior to other service call
     *
     */
    public void init() {
        networkManager = new NetworkManager();
    }

    public void getCategories(final CallBackToView callBackToView) throws IOException {
        networkManager.doGetCategoryRequest(CategoryResponse.class, callBackToView);
    }

    public void getBusinessSearch(String filter, String priceRange, String categories, final CallBackToView callBackToView) throws IOException {
        networkManager.doGetBusinessSearchRequest(filter, priceRange, categories, BusinessSearchResponse.class, callBackToView);
    }
}
    
