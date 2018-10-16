package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.adapter.MainListViewAdapter;
import com.prasanna.yelpreviewapp.model.Business;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.Category;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {

    //for testing, will be removed later
    private TextView tvTest;
    //for testing, will be removed later
    private StringBuilder sbTestList;


    private SearchView mSearchViewMain;
    private SearchView mSearchViewCategory;

    private ListView mListViewMain;

    private SearchViewModel mSearchViewModel;
    private CategoryResponse mCategoryResponse;
    private BusinessSearchResponse mBusinessResponse;

    private List<Category> mCategoryList;
    private List<Business> mBusinessList;

    private MainListViewAdapter mMainListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tvTest = findViewById(R.id.tvTest);
        sbTestList = new StringBuilder();

        mSearchViewMain = findViewById(R.id.searchViewMain);
        mSearchViewCategory = findViewById(R.id.searchViewCategory);

        mListViewMain = findViewById(R.id.listViewMain);
        mListViewMain.setVisibility(View.GONE);

        mBusinessList = new ArrayList<>();
        mMainListViewAdapter = new MainListViewAdapter(this, R.layout.list_item_main, mBusinessList);
        mListViewMain.setAdapter(mMainListViewAdapter);

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        sbTestList = new StringBuilder();
        mSearchViewModel.initCategory();

        mSearchViewModel.getCategoryResponseLiveData().observe(SearchActivity.this, searchRepositoryCategoryResponse -> {
            mCategoryResponse = searchRepositoryCategoryResponse.getData();
            if (mCategoryResponse != null) {
                List<Category> categoryList = mCategoryResponse.getCategories();
                if (categoryList != null) {
                    for (Category category : categoryList) {
                        sbTestList.append(category.getTitle());
                    }
                }
                mCategoryList = categoryList;
            }
            tvTest.setText(sbTestList);
        });

        mSearchViewCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //not required as of now
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //as there is no end point to fiter with search text, we have to store it in our local list, filter
                //and show it for the entered text
                sbTestList = new StringBuilder();
                List<Category> filterList =
                        //Create a Stream from the personList
                        mCategoryList.stream().
                                //filter the element to select only those matching with text
                                        filter(p -> p.getTitle().contains(newText)).
                                //put those filtered elements into a new List.
                                        collect(Collectors.toList());

                if (filterList != null) {
                    for (Category category : filterList) {
                        sbTestList.append(category.getTitle());
                    }
                }

                tvTest.setText(sbTestList);

                return false;
            }
        });

        mSearchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //start list activity here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    mListViewMain.setVisibility(View.VISIBLE);
                } else {
                    mListViewMain.setVisibility(View.GONE);
                }
                sbTestList = new StringBuilder();
                mSearchViewModel.initBusiness(newText);

                mSearchViewModel.getBusinessResponseLiveData().observe(SearchActivity.this, businessSearchResponseRepositoryResponse -> {
                    mBusinessResponse = businessSearchResponseRepositoryResponse.getData();
                    if (mBusinessResponse != null) {
                        List<Business> businessList = mBusinessResponse.getBusinesses();
                        if (businessList != null) {
                            for (Business business : businessList) {
                                sbTestList.append(business.getName());
                            }
                        }
                        mBusinessList = businessList;
                    }
                    tvTest.setText(sbTestList);
                    mMainListViewAdapter.setData(mBusinessList);
                });
                return false;
            }


        });

    }
}
