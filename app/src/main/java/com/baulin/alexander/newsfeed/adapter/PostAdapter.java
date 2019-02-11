package com.baulin.alexander.newsfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    Context context;
    List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
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
        holder.author.setText(String.valueOf(posts.get(position).id));
        holder.title.setText(String.valueOf(posts.get(position).title));
        holder.content.setText(new StringBuilder(posts.get(position).body.substring(0, 20)));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
