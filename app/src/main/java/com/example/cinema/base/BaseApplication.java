package com.example.cinema.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.cinema.db.MovieDBOpenHelper;

public class BaseApplication extends Application{

    private MovieDBOpenHelper movieDBOpenHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData(){
        movieDBOpenHelper=new MovieDBOpenHelper(this);
        //得到数据库
    }

    public MovieDBOpenHelper getMovieDBOpenHelper() {
        return movieDBOpenHelper;
    }

    public SQLiteDatabase getDb(boolean isWritable) {
        if (db != null) db.close();
        db = isWritable ? movieDBOpenHelper.getWritableDatabase():movieDBOpenHelper.getReadableDatabase();
        return db;
    }
}
