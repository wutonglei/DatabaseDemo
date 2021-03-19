package com.example.databasedemo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databasedemo.model.User;
import com.example.databasedemo.model.VipUser;

import java.util.List;

@Dao
public  interface VipUserDao {

    @Insert( onConflict=OnConflictStrategy.IGNORE)
    void insert(VipUser user);

    @Query("select *  FROM VipUserTable  ORDER BY created_at desc")
    List<VipUser> findAllUsers();

    @Query("SELECT * FROM VipUserTable WHERE id =:taskId")
    VipUser getUser(int taskId);

    @Update()
    void update(VipUser user);

    @Delete
    void delete(VipUser user);
}
