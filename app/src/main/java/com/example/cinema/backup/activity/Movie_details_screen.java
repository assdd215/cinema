package com.example.cinema.backup.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cinema.activity.Movie_seat;
import com.example.cinema.model.Movie;
import com.example.cinema.db.MovieDBOpenHelper;
import com.example.cinema.R;
import com.example.cinema.model.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 轻薄的呵呵哒 on 2018/6/4.
 */

public class Movie_details_screen extends AppCompatActivity {
    private ListView screenListView;
    private List<Screen> screenList;
    private MovieDBOpenHelper movieDBOpenHelper;
    private SQLiteDatabase db;
    private ScreenAdapter screenAdapter;
    private List<Integer> movieId;
    private List<Integer> screenId;
    private List<String> screenDate;
    private List<String> screenTime;
    private List<String> screenPlace;

    private Movie currentMovie;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_screen);
        initArray();
        initData();


        screenListView=findViewById(R.id.list_screen);
        screenList=new ArrayList<>();
        movieDBOpenHelper=new MovieDBOpenHelper(this);
        db=movieDBOpenHelper.getWritableDatabase();

//        Insert();
        Query();

        screenAdapter=new ScreenAdapter(this);
        screenListView.setAdapter(screenAdapter);

    }

    private void initData(){
        currentMovie = (Movie) getIntent().getExtras().getSerializable("movie");
    }

    public void initArray(){
        movieId=new ArrayList<>();
        movieId.add(0);
        movieId.add(1);
        movieId.add(1);
        movieId.add(2);
        movieId.add(3);
        movieId.add(4);
        movieId.add(5);
        movieId.add(6);
        movieId.add(7);

        screenId=new ArrayList<>();
        screenId.add(1001);
        screenId.add(1002);
        screenId.add(1003);
        screenId.add(1004);
        screenId.add(1005);
        screenId.add(1006);
        screenId.add(1007);
        screenId.add(1008);
        screenId.add(1009);

        screenDate=new ArrayList<>();
        screenDate.add("2015/6/9");
        screenDate.add("2015/6/10");
        screenDate.add("2015/6/10");
        screenDate.add("2015/6/11");
        screenDate.add("2015/6/11");
        screenDate.add("2015/6/10");
        screenDate.add("2015/6/10");
        screenDate.add("2015/6/15");
        screenDate.add("2015/6/15");

        screenTime=new ArrayList<>();
        screenTime.add("17:30");
        screenTime.add("19:30");
        screenTime.add("18:30");
        screenTime.add("17:30");
        screenTime.add("08:30");
        screenTime.add("11:30");
        screenTime.add("13:30");
        screenTime.add("14:00");
        screenTime.add("19:00");

        screenPlace=new ArrayList<>();
        screenPlace.add("1号厅");
        screenPlace.add("2号厅");
        screenPlace.add("5号厅");
        screenPlace.add("IMAX厅");
        screenPlace.add("2号厅");
        screenPlace.add("9号厅");
        screenPlace.add("5号厅");
        screenPlace.add("6号厅");
        screenPlace.add("1号厅");
    }

    public void Insert(){

//        db.execSQL("DELETE FROM screen;");
        for(int i = 0; i < screenId.size(); ++i){
            ContentValues values=new ContentValues();
            values.put("movieId",movieId.get(i));
            values.put("screenId",screenId.get(i));
            values.put("screenDate",screenDate.get(i));
            values.put("screenPlace",screenPlace.get(i));
            values.put("screenTime",screenTime.get(i));
            //将这一行插入到数据库中的screen表中
            db.insert("screen",null,values);
        }
        db.close();
    }

    public void Query(){
        db=movieDBOpenHelper.getWritableDatabase();

        Cursor cursor=db.query("screen",null,"movieId=?",new String[]{String.valueOf(currentMovie.getId())},null,null,null);
        while(cursor.moveToNext()){
//            Integer movieId=cursor.getInt(0);
//            Integer screenId=cursor.getInt(1);
//            String screenDate=cursor.getString(2);
//            String screenTime=cursor.getString(3);
//            String screenPlace=cursor.getString(4);
//            Screen screen=new Screen(movieId,screenId,screenDate,screenTime,screenPlace);
//            screenList.add(screen);
            screenList.add(new Screen(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        }
    }

    public class ScreenAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater;
        private int movieId;

        public ScreenAdapter(Context context) {
                this.context=context;
                inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
//            Bundle bundle=getIntent().getExtras();
//            Movie movie=(Movie)bundle.getSerializable("movie");
//            movieId=movie.getId();
//            int count=0;
//            for(int i=0;i<screenList.size();++i){
//                if(movieId == screenList.get(i).getMovieId())
//                    count += 1;
//            }
//            return count;
            return screenList.size();
        }

        @Override
        public Object getItem(int position) {
            return screenList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder=new ViewHolder();
            if (convertView == null) {
                inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_screen_entry,null);

                viewHolder.screenId=convertView.findViewById(R.id.screenId);
                viewHolder.screenDate=convertView.findViewById(R.id.screenDate);
                viewHolder.screenTime=convertView.findViewById(R.id.screenTime);
                viewHolder.screenPlace=convertView.findViewById(R.id.screenPlace);

                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)convertView.getTag();
            }

            Bundle bundle=getIntent().getExtras();
            Movie movie=(Movie)bundle.getSerializable("movie");
            movieId=movie.getId();
            Log.i("ID","未执行setText之前的ID:"+movieId);
            TextView dname=findViewById(R.id.dname);
            dname.setText(movie.getName());
            Log.i("NAME",movie.getName());
            TextView ddirector=findViewById(R.id.ddirector);
            ddirector.setText(movie.getDirector());
            TextView dactor=findViewById(R.id.dactor);
            Log.i("ACTOR",movie.getActor());
            dactor.setText(movie.getActor());
            TextView dprice=findViewById(R.id.dprice);
            String priceOpti=String.valueOf(movie.getPrice())+" 元";
            dprice.setText(priceOpti);
            TextView dtime=findViewById(R.id.dtime);
            String timeOpti=movie.getTime()+" 分钟";
            dtime.setText(timeOpti);
            TextView dtype=findViewById(R.id.dtype);
            dtype.setText(movie.getType());
            TextView ddesc=findViewById(R.id.ddesc);
            ddesc.setText(movie.getDescription());
            final Screen screen=screenList.get(position);
            if(movieId == screen.getMovieId()) {
                Log.i("ID", "执行setText之后的ID:" + movieId);
                Log.i("screenID", "screen的ID" + screen.getMovieId());
                //设置文字
                viewHolder.screenId.setText(String.valueOf(screen.getScreenId()));
                viewHolder.screenDate.setText(screen.getScreenDate());
                viewHolder.screenTime.setText(screen.getScreenTime());
                viewHolder.screenPlace.setText(screen.getScreenPlace());
            }
            //设置点击事件
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Movie_details_screen.this,Movie_seat.class);
                    Bundle bundle1=new Bundle();
                    Bundle bundle=getIntent().getExtras();
                    bundle1.putSerializable("screen",screen);
                    Movie movie=(Movie)bundle.getSerializable("movie");
                    bundle1.putSerializable("movie",movie);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            });
            return convertView;
        }
        public class ViewHolder{
            public TextView screenId;
            public TextView screenDate;
            public TextView screenTime;
            public TextView screenPlace;
        }
    }
}
