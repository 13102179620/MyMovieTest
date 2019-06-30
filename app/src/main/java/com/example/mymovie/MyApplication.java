package com.example.mymovie;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.mymovie.Dao.DaoMaster;
import com.example.mymovie.Dao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDao();

    }

    private void initDao() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "movies.db");
        Database db = devOpenHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();


    }
}
