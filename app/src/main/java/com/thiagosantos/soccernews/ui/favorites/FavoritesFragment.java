package com.thiagosantos.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiagosantos.soccernews.databinding.FragmentFavoritesBinding;
import com.thiagosantos.soccernews.ui.adapter.NewsAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        favoritesViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews -> {
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(localNews, updatedNews -> {
                favoritesViewModel.saveNews(updatedNews);
                loadFavoriteNews();
            }));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}