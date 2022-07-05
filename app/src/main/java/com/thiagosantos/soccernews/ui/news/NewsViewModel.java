package com.thiagosantos.soccernews.ui.news;

import com.thiagosantos.soccernews.ui.domain.News;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Palmeiras perde feio", "Perdeu o jogo de ontem"));
        news.add(new News("São Paulo manda bem no jogo de ontem", "FGanhou de 3 a 0"));
        news.add(new News("Palmeiras perde feio para o São paulo", "Perdeu o jogo de ontem , São paulo garantiu 3 a 0"));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}