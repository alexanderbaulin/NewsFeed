package com.baulin.alexander.newsfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.model.posts.NewsItem;
import com.baulin.alexander.newsfeed.model.posts.RootObject;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    Context context;
    RootObject posts;

    public PostAdapter(Context context, RootObject posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        NewsItem item = posts.getNewsItem().get(position);

        holder.title.setText(item.getHeadLine());
        if(!item.getStory().isEmpty())
            holder.content.setText(new StringBuilder(item.getStory().substring(0, 40).concat("...")));
        else
            holder.content.setText("");
        holder.date.setText(item.getDateLine());
    }

    @Override
    public int getItemCount() {
        return posts.getNewsItem().size();
    }
}
