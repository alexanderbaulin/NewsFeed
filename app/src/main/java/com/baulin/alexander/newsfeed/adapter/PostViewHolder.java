package com.baulin.alexander.newsfeed.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baulin.alexander.newsfeed.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    TextView title, content, author;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.txtTitle);
        content = itemView.findViewById(R.id.txtContent);
        author = itemView.findViewById(R.id.txtAuthor);


    }
}
