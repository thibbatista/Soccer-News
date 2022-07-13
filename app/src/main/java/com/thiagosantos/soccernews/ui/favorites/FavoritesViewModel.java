package com.thiagosantos.soccernews.ui.favorites;

import android.os.AsyncTask;

import com.thiagosantos.soccernews.data.SoccerNewsRepository;
import com.thiagosantos.soccernews.domain.News;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }

}