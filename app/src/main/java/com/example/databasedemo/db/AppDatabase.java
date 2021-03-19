package com.example.databasedemo.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


import com.example.databasedemo.dao.UserDao;
import com.example.databasedemo.dao.VipUserDao;
import com.example.databasedemo.model.User;
import com.example.databasedemo.model.VipUser;
import com.example.databasedemo.utils.AppGlobals;


@Database(entities = {User.class,VipUser.class}, version =3)
public abstract class AppDatabase extends RoomDatabase {
    private static final AppDatabase database;
    private static final String DATABASE_NAME = "app_database";

    //增加字段名
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table UserTable add column data TEXT");
        }
    };
    //    增加新表
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS VipUserTable " +
                            "(id INTEGER not null , level INTEGER, data TEXT, title TEXT,created_at TEXT,PRIMARY KEY(id))");
            database.execSQL(
                    "INSERT INTO VipUserTable (id, data,title,created_at) SELECT id, data,title,created_at FROM UserTable");
        }

    };

    static {
        database = Room.databaseBuilder(AppGlobals.getApplication(), AppDatabase.class, DATABASE_NAME)
                //是否允许在主线程进行查询
                .allowMainThreadQueries()
                //数据库创建和打开后的回调
                //.addCallback()
                //设置查询的线程池
                //.setQueryExecutor()
                //.openHelperFactory()
                //room的日志模式
                //.setJournalMode()
                //数据库升级异常之后的回滚
                .fallbackToDestructiveMigration()
                //数据库升级异常后根据指定版本进行回滚
                //.fallbackToDestructiveMigrationFrom()
                .addMigrations(AppDatabase.MIGRATION_1_2,MIGRATION_2_3)
                .build();
    }

    public abstract UserDao getUser();
    public abstract VipUserDao getVipUser();

    public static AppDatabase get() {
        return database;
    }

}
