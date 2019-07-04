package com.example.data.persistence;

import android.content.Context;

import androidx.room.Room;

import com.example.data.db.DB_CONSTANT;

public class BasePersistence {
    private BasePersistenceDao basePersistenceDao;

    private Context context;

    public BasePersistence(Context context) {
        this.context = context;
    }

    public BasePersistenceDao getDatabase() {
        if (basePersistenceDao == null) {
            init();
        }
        return basePersistenceDao;
    }

    private void init() {
        basePersistenceDao = Room
                .databaseBuilder(context, BasePersistenceDao.class, DB_CONSTANT.DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
