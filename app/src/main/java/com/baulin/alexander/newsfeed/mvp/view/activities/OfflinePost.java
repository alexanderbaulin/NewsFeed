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

        TextView title = findViewById(R.id.txtTitleOffline);
        title.setText(getIntent().getStringExtra("1"));

        TextView story = findViewById(R.id.txtStoryOffline);
        story.setText(getIntent().getStringExtra("2"));
    }
}
