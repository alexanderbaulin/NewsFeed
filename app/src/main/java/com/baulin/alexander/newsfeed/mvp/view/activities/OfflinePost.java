package com.baulin.alexander.newsfeed.mvp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.baulin.alexander.newsfeed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfflinePost extends AppCompatActivity {

    @BindView(R.id.txtTitleOffline)
    TextView title;
    @BindView(R.id.txtStoryOffline)
    TextView story;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_offline_item);

        ButterKnife.bind(this);

        title.setText(getIntent().getStringExtra("title"));
        story.setText(getIntent().getStringExtra("story"));
    }
}
