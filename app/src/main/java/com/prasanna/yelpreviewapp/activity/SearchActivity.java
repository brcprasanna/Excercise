package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.Business;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.Category;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.viewmodel.SearchViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText edtCategory;
    private TextView tvTest;
    private StringBuilder sbTestList;

    private SearchView searchView;

    private SearchViewModel mSearchViewModel;
    private CategoryResponse mCategoryResponse;
    private BusinessSearchResponse mBusinessResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCategory = findViewById(R.id.edtCategory);
        tvTest = findViewById(R.id.tvTestCategory);
        sbTestList = new StringBuilder();

        searchView = findViewById(R.id.searchView);


        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mSearchViewModel.initCategory("");

        mSearchViewModel.getCategoryResponseLiveData().observe(this, searchRepositoryCategoryResponse -> {
            mCategoryResponse = searchRepositoryCategoryResponse.getData();
            if (mCategoryResponse != null) {
                List<Category> categoryList = mCategoryResponse.getCategories();
                for (Category category : categoryList) {
                    sbTestList.append(category.getTitle());
                }
            }
            tvTest.setText(sbTestList);
        });

        String filterText = "food";

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //start list activity here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sbTestList = new StringBuilder();
                mSearchViewModel.initBusiness(newText);

                mSearchViewModel.getBusinessResponseLiveData().observe(SearchActivity.this, businessSearchResponseRepositoryResponse -> {
                    mBusinessResponse = businessSearchResponseRepositoryResponse.getData();
                    if (mBusinessResponse != null) {
                        List<Business> businessList = mBusinessResponse.getBusinesses();
                        for (Business business : businessList) {
                            sbTestList.append(business.getName());
                        }
                    }
                    tvTest.setText(sbTestList);
                });

                return false;
            }


        });

    }
}
