package com.prasanna.yelpreviewapp.utils;

import com.prasanna.yelpreviewapp.model.CategoryResponse;

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

    public void getCategories(String filter, final CallBackToView callBackToView) throws IOException {
        networkManager.doGetCategoryRequest(filter, CategoryResponse.class, callBackToView);
    }
}
    
