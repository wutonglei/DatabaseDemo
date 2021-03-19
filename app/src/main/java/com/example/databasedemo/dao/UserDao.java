package com.example.databasedemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databasedemo.model.User;

import java.util.List;

@Dao
public  interface UserDao {

    @Insert( onConflict=OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("select *  FROM UserTable  ORDER BY created_at desc")
    List<User> findAllUsers();

    @Query("SELECT * FROM UserTable WHERE id =:taskId")
    User getUser(int taskId);

    @Update()
    void update(User user);

    @Delete
    void delete(User user);
}
