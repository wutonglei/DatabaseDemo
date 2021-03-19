package com.example.databasedemo.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.databasedemo.converter.DescConverter;
import com.example.databasedemo.converter.TimestampConverter;

import java.util.Date;

@Entity(tableName = "VipUserTable")
public class VipUser {

    @Override
    public String toString() {
        return "VipUser{" +
                "id=" + id +
                ", level=" + level +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", ignoreText='" + ignoreText + '\'' +
                '}';
    }

    //主键是否相同来判断是否是同一个对象。
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Integer level;
    public String data;
    public String title;

    //指定类的属性在表中列的名字，如果不指定，默认就是属性名。
//注意，存储在数据库中的类属性可见性必须是public的。
    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    public Date createdAt;



    @Ignore         //指示Room需要忽略的字段或方法
    public String ignoreText;


}
