package com.baulin.alexander.newsfeed.mvp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.baulin.alexander.newsfeed.R;

public class OfflinePost extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_offline_item);

        TextView v = findViewById(R.id.txtTitleOffline);
        v.setText(getIntent().getStringExtra("1"));

        TextView v2 = findViewById(R.id.txtStoryOffline);
        v2.setText(getIntent().getStringExtra("2"));

        Log.d("myLogs", "----------------------" + getIntent().getStringExtra("1"));
        Log.d("myLogs", "-----------------------------" + getIntent().getStringExtra("2"));
    }
}
