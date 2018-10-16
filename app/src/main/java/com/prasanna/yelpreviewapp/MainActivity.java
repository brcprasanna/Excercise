package com.prasanna.yelpreviewapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.utils.AppConstants;
import com.prasanna.yelpreviewapp.utils.CallBackToView;
import com.prasanna.yelpreviewapp.utils.DataManager;
import com.prasanna.yelpreviewapp.utils.NetworkManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.test);
        String filterText = "food";
        try {
            DataManager.getInstance().getCategories(filterText, new CallBackToView() {
                @Override
                public void onSuccess(final Response responseModel) {
                    Log.i("Prasanna", responseModel.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvTest.setText(responseModel.toString());
                        }
                    });

                }

                @Override
                public void onFailure(final String errorMsg) {
                    Log.i("Prasanna", errorMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvTest.setText(errorMsg);
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
