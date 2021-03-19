package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.databasedemo.db.AppDatabase;
import com.example.databasedemo.model.User;
import com.example.databasedemo.model.VipUser;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 1.创建数据库  AppDatabase
 * 2.增删改查    UserDao
 * 3.数据库升级  AppDatabase::MIGRATION_1_2
 *
 * db2  User data
 * db3 VipUser
 * <p>
 * https://www.jianshu.com/p/7014d9fc0549/
 * https://blog.csdn.net/BunnyCoffer/article/details/103633246
 */
public class MainActivity extends AppCompatActivity {
    private EditText mEt_id;
    private EditText mEt_title;

    private AppDatabase appDatabase;

    TextView textView;
    ScrollView sv;
    long currentTime = System.currentTimeMillis();

    private void showData(String log) {
        if (textView == null)
            textView = findViewById(R.id.tv_log);
        textView.append(System.currentTimeMillis() - currentTime + ":" + log + "\n");
        sv.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.get();
        mEt_id = (EditText) findViewById(R.id.et_id);
        mEt_title = (EditText) findViewById(R.id.et_title);
        textView = findViewById(R.id.tv_log);
        sv = findViewById(R.id.sv);
    }

    /**
     * insert    OnConflictStrategy.ABORT   UNIQUE constraint failed: UserTable.id (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)
     *
     * @param v
     */
    public void onClick(View v) {
        textView.setText("");
        User user = new User();
        VipUser vipUser = new VipUser();
        String s = mEt_id.getText().toString();
        int i=0;
        if (!TextUtils.isEmpty(s))
            i = Integer.parseInt(s);
        switch (v.getId()) {
            case R.id.btn_insert: {
                user.id = i;
                user.title = mEt_title.getText().toString();
                user.data = mEt_title.getText().toString();
                user.description = "dasdasdas";
                appDatabase.getUser().insert(user);
                vipUser.level = i;
//                appDatabase.getVipUser().insert(vipUser);
                break;
            }
            case R.id.btn_delete: {

                user.id = Integer.parseInt(mEt_id.getText().toString());
                appDatabase.getUser().delete(user);
                break;
            }
            case R.id.btn_update: {
                user.id = Integer.parseInt(mEt_id.getText().toString());
                user.title = mEt_title.getText().toString();
                appDatabase.getUser().update(user);
                break;
            }

            case R.id.btn_search: {
                int id = Integer.parseInt(mEt_id.getText().toString());
                User user1 = appDatabase.getUser().getUser(id);
                if (user1 == null)
                    return;
                showData("");
                showData("" + user1.toString());
                break;
            }

        }

        List<User> allUsers = appDatabase.getUser().findAllUsers();

        if (allUsers == null || allUsers.size() == 0)
            return;
        for (User u : allUsers) {
            showData(u.toString());
        }

        List<VipUser> allVipUsers = appDatabase.getVipUser().findAllUsers();

        if (allVipUsers == null || allVipUsers.size() == 0)
            return;
        for (VipUser u : allVipUsers) {
            showData(u.toString());
        }


    }
}