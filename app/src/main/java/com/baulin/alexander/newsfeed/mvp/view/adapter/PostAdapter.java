package com.baulin.alexander.newsfeed.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.mvp.model.posts.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.posts.RootObject;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    Context context;
    RootObject posts;

    public PostAdapter(Context context) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        NewsItem item = posts.getNewsItem().get(position);

        holder.title.setText(item.getHeadLine());
        if(!item.getStory().isEmpty())
            holder.content.setText(new StringBuilder(item.getStory().substring(0, 40).concat("...")));
        else
            holder.content.setText("");
        holder.date.setText(item.getDateLine());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLogs", "onClick " + posts.getNewsItem().get(holder.getAdapterPosition()).getHeadLine());
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.getNewsItem().size();
    }

    public void setPosts(RootObject posts) {
        this.posts = posts;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView title, content, date;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            content = itemView.findViewById(R.id.txtContent);
            date = itemView.findViewById(R.id.txtDate);

        }
    }

}
