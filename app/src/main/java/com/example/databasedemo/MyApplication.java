package com.example.databasedemo;

import android.app.Application;

import androidx.room.Room;

import com.example.databasedemo.db.AppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "database-name")
//                .allowMainThreadQueries()//允许在主线程中查询
//                .build();

        init();
    }

    private void init() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }
}
