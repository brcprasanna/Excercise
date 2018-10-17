package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.adapter.BusinessListAdapter;
import com.prasanna.yelpreviewapp.model.Business;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.utils.AppConstants;
import com.prasanna.yelpreviewapp.utils.EndlessRecyclerViewScrollListener;
import com.prasanna.yelpreviewapp.viewmodel.BusinessListViewModel;

import java.util.LinkedList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity {
    private static final int LIMIT = 15;
    private static final int NUMBER_OF_ITEMS = 100;

    public RecyclerView recyclerView;
    public ProgressBar progressBar;

    private LinearLayoutManager layoutManager;
    private List<Business> items;
    private int offset;

    private BusinessListViewModel mViewmodel;
    private String mSearchText;
    private String mPriceRangeText;
    private String mCategoryText;
    private BusinessSearchResponse mBusinessResponse;

    private EndlessRecyclerViewScrollListener scrollListener;
    private BusinessListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        mViewmodel = ViewModelProviders.of(this).get(BusinessListViewModel.class);

        mSearchText = getIntent().getStringExtra(AppConstants.INTENT_DATA_SEARCH_TEXT);
        mPriceRangeText = getIntent().getStringExtra(AppConstants.INTENT_DATA_PRICE_RANGE_TEXT);
        mCategoryText = getIntent().getStringExtra(AppConstants.INTENT_DATA_CATEGORY_TEXT);

        recyclerView = findViewById(R.id.recycler_view);

        mViewmodel.initBusiness(mSearchText, mPriceRangeText, mCategoryText, LIMIT,  1);

        mViewmodel.getBusinessResponseLiveData().observe(BusinessListActivity.this, businessSearchResponseRepositoryResponse -> {
            mBusinessResponse = businessSearchResponseRepositoryResponse.getData();
            if (mBusinessResponse != null) {
                List<Business> businessList = mBusinessResponse.getBusinesses();
                items = businessList;
            }
            if (items != null) {
                mAdapter = new BusinessListAdapter(items);
                recyclerView.setAdapter(mAdapter);
            }

            int curSize = mAdapter.getItemCount();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyItemRangeInserted(curSize, items.size() - 1);
                }
            });


        });


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mViewmodel.initBusiness(mSearchText, mPriceRangeText, mCategoryText, LIMIT, page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);


    }


}