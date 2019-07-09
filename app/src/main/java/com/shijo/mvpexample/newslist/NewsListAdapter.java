package com.shijo.mvpexample.newslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shijo.mvpexample.R;
import com.shijo.mvpexample.http.apimodel.Article;

import java.util.List;

public class NewsListAdapter  extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<Article> newsList;
    Context mContext;

    public NewsListAdapter(List<Article> list, Context context) {
        this.newsList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news_light, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder viewHolder, int i) {

        Article article = newsList.get(i);
        viewHolder.title.setText(article.getTitle());
        viewHolder.subtitle.setText(article.getContent());
        viewHolder.date.setText(article.getPublishedAt());
        Glide.with(mContext).load(article.getUrlToImage())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setItemAtPos(Article article) {
        this.newsList.add(article);
        notifyItemInserted(this.newsList.size()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView subtitle;
        public TextView date;
        public View lyt_parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            date = itemView.findViewById(R.id.date);
            lyt_parent = itemView.findViewById(R.id.lyt_parent);
        }
    }
}
