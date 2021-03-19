package com.example.databasedemo.utils;


import android.app.Application;

import androidx.room.Room;

import com.example.databasedemo.db.AppDatabase;

public class RoomFactory {

    private static final String DB_NAMWE = "mydatabase.db";

    public volatile static AppDatabase mAppDatabase;

    private RoomFactory() {
    }

    private static class Holder {
        private static RoomFactory instance = new RoomFactory();
    }

    public static RoomFactory getInstance() {
        return Holder.instance;
    }

    public void init(Application application) {
        mAppDatabase = Room.databaseBuilder(application, AppDatabase.class, DB_NAMWE)
                .allowMainThreadQueries() //支持主线程
                .build();
    }

    public AppDatabase getDatabase(){
        return RoomFactory.mAppDatabase;
    }
}
