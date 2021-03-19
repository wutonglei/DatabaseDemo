package com.example.databasedemo.converter;

import androidx.room.TypeConverter;

public class DescConverter {

    @TypeConverter
    public static String preString(String data) {
        return "trh" + data;
    }

}
