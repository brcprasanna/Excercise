package com.prasanna.yelpreviewapp.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.adapter.BusinessListAdapter;
import com.prasanna.yelpreviewapp.model.Business;
import com.prasanna.yelpreviewapp.model.BusinessSearchResponse;
import com.prasanna.yelpreviewapp.utils.AppConstants;
import com.prasanna.yelpreviewapp.utils.EndlessRecyclerViewScrollListener;
import com.prasanna.yelpreviewapp.viewmodel.BusinessListViewModel;

import java.util.List;

public class BusinessListActivity extends AppCompatActivity {
    private static final int LIMIT = 15;

    private RecyclerView mRecyclerView;

    private List<Business> mBusinessList;

    private BusinessListViewModel mBusinessListViewModel;
    private String mSearchText;
    private String mPriceRangeText;
    private String mCategoryText;
    private BusinessSearchResponse mBusinessResponse;

    private BusinessListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        mBusinessListViewModel = ViewModelProviders.of(this).get(BusinessListViewModel.class);

        mSearchText = getIntent().getStringExtra(AppConstants.INTENT_DATA_SEARCH_TEXT);
        mPriceRangeText = getIntent().getStringExtra(AppConstants.INTENT_DATA_PRICE_RANGE_TEXT);
        mCategoryText = getIntent().getStringExtra(AppConstants.INTENT_DATA_CATEGORY_TEXT);

        mRecyclerView = findViewById(R.id.recycler_view);

        mBusinessListViewModel.initBusiness(mSearchText, mPriceRangeText, mCategoryText, LIMIT, 0);

        mBusinessListViewModel.getBusinessResponseLiveData().observe(BusinessListActivity.this, businessSearchResponseRepositoryResponseBase -> {
            if (businessSearchResponseRepositoryResponseBase != null) {
                mBusinessResponse = businessSearchResponseRepositoryResponseBase.getData();
                if (mBusinessResponse != null) {
                    this.mBusinessList = mBusinessResponse.getBusinesses();
                }
                if (mBusinessList != null) {
                    mAdapter = new BusinessListAdapter(mBusinessList);
                    mRecyclerView.setAdapter(mAdapter);
                }

                int curSize = mAdapter.getItemCount();
                mAdapter.notifyItemRangeInserted(curSize, mBusinessList.size() - 1);
            }
        });


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mBusinessListViewModel.initBusiness(mSearchText, mPriceRangeText, mCategoryText, LIMIT, page);
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);


    }


}