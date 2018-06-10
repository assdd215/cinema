package com.example.cinema.backup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.cinema.model.Movie;
import com.example.cinema.model.Screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 轻薄的呵呵哒 on 2018/6/4.
 */

public class MovieDBOpenHelper extends SQLiteOpenHelper {

    //public static final String path= Environment.getExternalStorageDirectory()+"/cinema.db";
    public MovieDBOpenHelper(Context context) {
        //创建数据
        //
        super(context, "cinema.db" ,null, 1);
        // TODO Auto-generated constructor stub
        System.out.println("MovieDBOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS movie;");
        db.execSQL("DROP TABLE IF EXISTS screen;");
        db.execSQL("CREATE TABLE movie("+
                        "id INT PRIMARY KEY  ASC NOT NULL UNIQUE,"+
                        "name VARCHAR(64) NOT NULL UNIQUE,"+
                        "director VARCHAR(64) NOT NULL,"+
                        "actor VARCHAR(64) NOT NULL,"+
                        "price INT NOT NULL,"+
                        "time VARCHAR(32) NOT NULL,"+
                        "type VARCHAR(32) NOT NULL,"+
                        "description VARCHAR(128)) WITHOUT ROWID;");
        db.execSQL("CREATE TABLE screen(" +
                "  screenId INT PRIMARY KEY NOT NULL, " +
                "  movieId INT NOT NULL , " +
                "  screenDate VARCHAR(64) NOT NULL, " +
                "  screenTime VARCHAR(32) NOT NULL, " +
                "  screenPlace VARCHAR(32) NOT NULL, " +
                "  FOREIGN KEY (movieId) references movie(id));");
        insertData(db);
    }


    private void insertData(SQLiteDatabase db){
        List<Screen> screens = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie(0,"X战警：逆转未来","布莱恩·辛格","休·杰克曼/迈克尔·法斯宾德","120","129","动作/科幻","故事的设定发生在当下，变种人族群遭到了前所未有的毁灭性打击，而这一切的根源是“魔形女”瑞文。。。。");
        movies.add(movie);
        movie = new Movie(1,"澳门风云","王晶","周润发/谢霆锋/杜汶泽","90","93","喜剧/动作","影片讲述的是外号“赢尽天下无敌手”的石一坚和他的朋友家人一起布下并利用局从犯罪集团的手中逃脱的故事。");
        movies.add(movie);

        StringBuilder insertMovieSql = new StringBuilder("INSERT INTO movie(id,name,director,actor,price,time,type,description) VALUES ");
        for (Movie insertMovie:movies){
            insertMovieSql.append("(" + insertMovie.getId() + ",'" + insertMovie.getName() + "','" + insertMovie.getDirector() + "','" + insertMovie.getActor() + "'," +
            insertMovie.getPrice() + ",'" + insertMovie.getTime() + "','" + insertMovie.getType() + "','" + insertMovie.getDescription() + "'),");
        }
        insertMovieSql.deleteCharAt(insertMovieSql.length() - 1).append(";"); // 删掉最后的逗号
        db.execSQL(insertMovieSql.toString());

        //TODO 这里你再补充你的电影

        Screen screen = new Screen(1001,0,"2015/6/9","17:30","1号厅");
        screens.add(screen);
        screen = new Screen(1002,1,"2015/6/10","19:30","2号厅");
        screens.add(screen);
        screen = new Screen(1003,1,"2015/6/10","18:30","5号厅");
        screens.add(screen);
        //TODO 补充你的场次

        StringBuilder insertScreenSql = new StringBuilder("INSERT INTO screen VALUES ");
        for (Screen insertScreen:screens){
            insertScreenSql.append("(" + insertScreen.getScreenId() + "," + insertScreen.getMovieId() + ",'" + insertScreen.getScreenDate() + "','" + insertScreen.getScreenTime() +
                    "','" + insertScreen.getScreenPlace() + "'),");
        }
        insertScreenSql.deleteCharAt(insertScreenSql.length() - 1);
        insertScreenSql.append(";");
        db.execSQL(insertScreenSql.toString());



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d()
    }
}
