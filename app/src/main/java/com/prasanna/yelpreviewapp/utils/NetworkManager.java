package com.prasanna.yelpreviewapp.utils;

import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.prasanna.yelpreviewapp.utils.AppConstants.EMPTY;

public class NetworkManager {
    //private CallBackToView mCallback;

    final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .writeTimeout(5000, TimeUnit.SECONDS)
            .readTimeout(5000, TimeUnit.SECONDS)
            .build();

    public void doGetCategoryRequest(final Class<CategoryResponse> categoryResponseClass, final CallBackToView mCallback) throws IOException {

        Request request = new Request.Builder()
                .url(AppConstants.CATEGORY_URL)
                .addHeader("Authorization", "Bearer " + AppConstants.API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mCallback.onFailure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCallback.onSuccess(response.body().string());
            }
        });
    }

    public void doGetBusinessSearchRequest(String filter, String priceRange, String categories, Class<BusinessSearchResponse> businessSearchResponseClass, final CallBackToView mCallback) {
        String url;

        if (categories.trim().equals(EMPTY)) {
            url = AppConstants.BUSINESS_SEARCH_URL + "&term=" + filter + "&price=" + priceRange;
        } else {
            url = AppConstants.BUSINESS_SEARCH_URL + "&term=" + filter + "&price=" + priceRange + "&categories=" + categories;
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + AppConstants.API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mCallback.onFailure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCallback.onSuccess(response.body().string());
            }
        });
    }
}
