package com.baulin.alexander.newsfeed.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baulin.alexander.newsfeed.MyApplication;
import com.baulin.alexander.newsfeed.R;
import com.baulin.alexander.newsfeed.dagger2.components.AppComponent;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.NewsItem;
import com.baulin.alexander.newsfeed.mvp.model.fromJSON.RootNewsObject;
import com.baulin.alexander.newsfeed.mvp.view.activities.OfflinePost;

import java.util.List;

import javax.inject.Inject;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    @Inject
    Context context;
    List<NewsItem> posts;

    public PostAdapter() {
        AppComponent component = MyApplication.getComponent();
        if(component != null) component.injectPostAdapter(this);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_recycle_item, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<NewsItem> posts) {
        this.posts = posts;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        NewsItem item = posts.get(position);

        holder.title.setText(item.getHeadLine());
        holder.content.setText(item.getStory());
        holder.date.setText(item.getDateLine());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.haveNetworkConnection()) {
                    startGhromeTabs(item.getWebURL());
                    Log.d("myLogs", "onClick " + posts.get(holder.getAdapterPosition()).getHeadLine());
                } else {
                    startActivity(item);
                }
            }
        });

    }

    private void startActivity(NewsItem item) {
        Intent i = new Intent(context, OfflinePost.class);
        i.putExtra("1", item.getHeadLine());
        i.putExtra("2", item.getStory());
        context.startActivity(i);
    }

    private void startGhromeTabs(String webURL) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        
        builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        builder.setSecondaryToolbarColor(context.getResources().getColor(R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(webURL));
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
