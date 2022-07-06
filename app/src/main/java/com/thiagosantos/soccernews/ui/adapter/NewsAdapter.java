package com.thiagosantos.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.thiagosantos.soccernews.databinding.NewsItemBinding;
import com.thiagosantos.soccernews.ui.domain.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class  NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private final List<News> news;

    public NewsAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
        Picasso.get().load(news.getImage())
                .fit()
                .into(holder.binding.ivThumbnail);
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(i);
        });

        //implementação de funcionalidade de "Compartilhar"
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Share"));
        });
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
