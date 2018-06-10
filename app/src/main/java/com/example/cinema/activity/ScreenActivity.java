package com.example.cinema.activity;

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

import com.example.cinema.base.BaseApplication;
import com.example.cinema.model.Movie;
import com.example.cinema.db.MovieDBOpenHelper;
import com.example.cinema.R;
import com.example.cinema.model.Screen;
import com.example.cinema.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 轻薄的呵呵哒 on 2018/6/4.
 */

public class ScreenActivity extends AppCompatActivity {
    private ListView screenListView;
    private List<Screen> screenList;
    private MovieDBOpenHelper movieDBOpenHelper;
    private SQLiteDatabase db;
    private ScreenAdapter screenAdapter;

    private Movie currentMovie;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_screen);
        initData();

    }

    private void initData(){
        currentMovie = (Movie) getIntent().getExtras().getSerializable(Constants.KEY_MOVIE);
        //创建MovieDBOpenHelper的实例
        movieDBOpenHelper = ((BaseApplication)getApplication()).getMovieDBOpenHelper();
        //得到数据库
        db = ((BaseApplication)getApplication()).getDb(true);
        screenList=new ArrayList<>();
        Query();

        screenListView=findViewById(R.id.list_screen);
        screenAdapter=new ScreenAdapter(this);
        screenListView.setAdapter(screenAdapter);

        TextView dname=findViewById(R.id.dname);
        TextView ddirector=findViewById(R.id.ddirector);
        TextView dactor=findViewById(R.id.dactor);
        TextView dprice=findViewById(R.id.dprice);
        TextView dtime=findViewById(R.id.dtime);
        TextView dtype=findViewById(R.id.dtype);
        TextView ddesc=findViewById(R.id.ddesc);

        dname.setText(currentMovie.getName());
        ddirector.setText(currentMovie.getDirector());
        dactor.setText(currentMovie.getActor());
        dprice.setText(String.format("%s 元",currentMovie.getPrice()));
        dtime.setText(String.format("%s 分钟",currentMovie.getTime()));
        dtype.setText(currentMovie.getType());
        ddesc.setText(currentMovie.getDescription());

    }


    public void Query(){
        Cursor cursor=db.query("screen",null,"movieId=?",new String[]{String.valueOf(currentMovie.getId())},null,null,null);
        while(cursor.moveToNext()){
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

            final Screen screen=screenList.get(position);
            if(currentMovie.getId() == screen.getMovieId()) {
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
                    Intent intent=new Intent(ScreenActivity.this,Movie_seat.class);
                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable(Constants.KEY_SCREEN,screen);
                    bundle1.putSerializable(Constants.KEY_MOVIE,currentMovie);
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
