package com.thiagosantos.soccernews.data.local;

import com.thiagosantos.soccernews.domain.News;
import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {News.class}, version = 1)
public abstract class SoccerNewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}
