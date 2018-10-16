package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.Category;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.repository.SearchRepository;
import com.prasanna.yelpreviewapp.utils.CallBackToView;
import com.prasanna.yelpreviewapp.utils.DataManager;
import com.prasanna.yelpreviewapp.viewmodel.SearchViewModel;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText edtCategory;
    private SearchView searchView;

    private SearchViewModel mSearchViewModel;
    private CategoryResponse mCategoryResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCategory = findViewById(R.id.edtCategory);
        searchView = findViewById(R.id.searchView);

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mSearchViewModel.init("");

        mSearchViewModel.getmCategoryResponseLiveData().observe(this, searchRepositoryCategoryResponse-> {
            mCategoryResponse = searchRepositoryCategoryResponse.getData();
            edtCategory.setText(mCategoryResponse.getCategories().get(0).toString());
        });

        String filterText = "food";


        //Search View
        try {
            DataManager.getInstance().getBusinessSearch(filterText, new CallBackToView() {
                @Override
                public void onSuccess(final String responseModel) {
                    Log.i("Prasanna", responseModel);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //should be shown in a list, so that user can select an item from that list
                            BusinessSearchResponse businessSearchResponse = new Gson().fromJson(responseModel, BusinessSearchResponse.class);
                            //searchView.setQuery(responseModel, false);
                        }
                    });

                }

                @Override
                public void onFailure(final String errorMsg) {
                    Log.i("Prasanna", errorMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //should be shown in a list, so that user can select an item from that list
                            searchView.setQuery(errorMsg, false);
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
