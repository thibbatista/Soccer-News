package com.thiagosantos.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thiagosantos.soccernews.MainActivity;
import com.thiagosantos.soccernews.databinding.FragmentFavoritesBinding;
import com.thiagosantos.soccernews.domain.News;
import com.thiagosantos.soccernews.ui.adapter.NewsAdapter;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            List<News> favoriteNews = activity.getDb().newsDao().loadFavoriteNews();
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
                activity.getDb().newsDao().save(updatedNews);
                loadFavoriteNews();
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}