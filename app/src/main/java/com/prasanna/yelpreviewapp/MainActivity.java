package com.prasanna.yelpreviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import com.prasanna.yelpreviewapp.utils.CallBackToView;
import com.prasanna.yelpreviewapp.utils.DataManager;

import java.io.IOException;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtCategory;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCategory = findViewById(R.id.edtCategory);
        searchView = findViewById(R.id.searchView);

        String filterText = "food";


        //Search View
        try {
            DataManager.getInstance().getBusinessSearch(filterText, new CallBackToView() {
                @Override
                public void onSuccess(final String responseModel) {
                    Log.i("Prasanna", responseModel.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //should be shown in a list, so that user can select an item from that list
                            searchView.setQuery(responseModel.toString(), false);
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


        //Category
        try {
            DataManager.getInstance().getCategories(filterText, new CallBackToView() {
                @Override
                public void onSuccess(final String responseModel) {
                    Log.i("Prasanna", responseModel.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //should be shown in a list, so that user can select an item from that list
                            edtCategory.setText(responseModel.toString());
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
                            edtCategory.setText(errorMsg);
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
