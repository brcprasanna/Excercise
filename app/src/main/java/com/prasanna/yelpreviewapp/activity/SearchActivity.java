package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.adapter.CategoryListViewAdapter;
import com.prasanna.yelpreviewapp.adapter.MainListViewAdapter;
import com.prasanna.yelpreviewapp.model.Business;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.model.category.Category;
import com.prasanna.yelpreviewapp.model.category.CategoryResponse;
import com.prasanna.yelpreviewapp.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity{

    private SearchView mSearchViewMain;
    private SearchView mSearchViewCategory;

    private ListView mListViewMain;
    private ListView mListViewCategory;

    private SearchViewModel mSearchViewModel;
    private CategoryResponse mCategoryResponse;
    private BusinessSearchResponse mBusinessResponse;

    private List<Category> mCategoryList;
    private List<Business> mBusinessList;

    private MainListViewAdapter mMainListViewAdapter;
    private CategoryListViewAdapter mCategoryListViewAdapter;

    private Spinner mSpinnerRange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchViewMain = findViewById(R.id.searchViewMain);
        mSearchViewMain.setQueryHint(getString(R.string.hint_main_search));

        mSearchViewCategory = findViewById(R.id.searchViewCategory);
        mSearchViewCategory.setQueryHint(getString(R.string.hint_category_search));

        mListViewMain = findViewById(R.id.listViewMain);
        mListViewMain.setVisibility(View.GONE);
        mListViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Business business = (Business) mListViewMain.getItemAtPosition(i);
                mSearchViewMain.setQuery(business.getName(), false);
                mListViewMain.setVisibility(View.GONE);
            }
        });

        mListViewCategory = findViewById(R.id.listViewCategory);
        mListViewCategory.setVisibility(View.GONE);
        mListViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) mListViewCategory.getItemAtPosition(i);
                mSearchViewCategory.setQuery(category.getTitle(), false);
                mListViewCategory.setVisibility(View.GONE);
            }
        });

        mSpinnerRange = findViewById(R.id.spinnerRange);

        mBusinessList = new ArrayList<>();
        mMainListViewAdapter = new MainListViewAdapter(this, R.layout.list_item_main, mBusinessList);
        mListViewMain.setAdapter(mMainListViewAdapter);

        mCategoryList = new ArrayList<>();
        mCategoryListViewAdapter = new CategoryListViewAdapter(this, R.layout.list_item_main, mCategoryList);
        mListViewCategory.setAdapter(mCategoryListViewAdapter);

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        mSearchViewModel.initCategory();

        mSearchViewModel.getCategoryResponseLiveData().observe(SearchActivity.this, searchRepositoryCategoryResponse -> {
            mCategoryResponse = searchRepositoryCategoryResponse.getData();
            if (mCategoryResponse != null) {
                List<Category> categoryList = mCategoryResponse.getCategories();
                if (categoryList != null) {
                    mCategoryList = categoryList;
                }
            }
        });

        mSearchViewCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //not required as of now
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    mListViewCategory.setVisibility(View.VISIBLE);
                } else {
                    mListViewCategory.setVisibility(View.GONE);
                }
                List<Category> filterList =
                        mCategoryList.stream().
                                filter(p -> p.getTitle().contains(newText)).
                                collect(Collectors.toList());
                mCategoryListViewAdapter.setData(filterList);
                return false;
            }
        });

        mSearchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //start list activity here
                Intent intentBusinessActivity = new Intent(SearchActivity.this, BusinessListActivity.class);
                startActivity(intentBusinessActivity);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    mListViewMain.setVisibility(View.VISIBLE);
                } else {
                    mListViewMain.setVisibility(View.GONE);
                }

                String spinnerRangeText = mSearchViewModel.getSpinnerRangeText(mSpinnerRange.getSelectedItem().toString(), getString(R.string.all));

                if (mSearchViewCategory.getQuery().length() > 0) {
                    mSearchViewModel.initBusiness(newText, spinnerRangeText, mSearchViewCategory.getQuery().toString());
                } else {
                    mSearchViewModel.initBusiness(newText, spinnerRangeText, null);
                }

                mSearchViewModel.getBusinessResponseLiveData().observe(SearchActivity.this, businessSearchResponseRepositoryResponse -> {
                    mBusinessResponse = businessSearchResponseRepositoryResponse.getData();
                    if (mBusinessResponse != null) {
                        List<Business> businessList = mBusinessResponse.getBusinesses();
                        mBusinessList = businessList;
                    }
                    mMainListViewAdapter.setData(mBusinessList);
                });
                return false;
            }

        });

    }

}
