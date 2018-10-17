package com.prasanna.yelpreviewapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.adapter.BusinessListAdapter;

import java.util.LinkedList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity {
    private static final int MAX_ITEMS_PER_REQUEST = 20;
    private static final int NUMBER_OF_ITEMS = 100;
    private static final int SIMULATED_LOADING_TIME_IN_MS = 1500;

    public RecyclerView recyclerView;
    public ProgressBar progressBar;

    private LinearLayoutManager layoutManager;
    private List<String> items;
    private int page;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
        this.items = createItems();
        initViews();
        initRecyclerView();
    }

    private static List<String> createItems() {
        List<String> itemsLocal = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            String prefix = i < 10 ? "0" : "";
            itemsLocal.add("Item #".concat(prefix).concat(String.valueOf(i)));
        }
        return itemsLocal;
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BusinessListAdapter(items.subList(page, MAX_ITEMS_PER_REQUEST)));
        recyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(MAX_ITEMS_PER_REQUEST, layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                simulateLoading();
                int start = ++page * MAX_ITEMS_PER_REQUEST;
                final boolean allItemsLoaded = start >= items.size();
                if (allItemsLoaded) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    int end = start + MAX_ITEMS_PER_REQUEST;
                    final List<String> itemsLocal = getItemsToBeLoaded(start, end);
                    refreshView(recyclerView, new BusinessListAdapter(itemsLocal), firstVisibleItemPosition);
                }
            }
        };
    }

    private List<String> getItemsToBeLoaded(int start, int end) {
        List<String> newItems = items.subList(start, end);
        final List<String> oldItems = ((BusinessListAdapter) recyclerView.getAdapter()).getItems();
        final List<String> itemsLocal = new LinkedList<>();
        itemsLocal.addAll(oldItems);
        itemsLocal.addAll(newItems);
        return itemsLocal;
    }

    /**
     * WARNING! This method is only for demo purposes!
     * Don't do anything like that in your regular project!
     */
    private void simulateLoading() {
        new AsyncTask<Void, Void, Void>() {
            @Override protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(SIMULATED_LOADING_TIME_IN_MS);
                } catch (InterruptedException e) {
                    Log.e("BusinessListActivity", e.getMessage());
                }
                return null;
            }

            @Override protected void onPostExecute(Void param) {
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }
}