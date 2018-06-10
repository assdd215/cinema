package com.example.cinema.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.cinema.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView movieListView;
    private List<Movie> movieList;
    private MovieDBOpenHelper movieDBOpenHelper;
    private SQLiteDatabase db;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData(){
        movieListView=findViewById(R.id.listView);
        //创建movie类的实例的链表
        movieList=new ArrayList<Movie>();
        //创建MovieDBOpenHelper的实例
        movieDBOpenHelper = ((BaseApplication)getApplication()).getMovieDBOpenHelper();
        //得到数据库
        db = ((BaseApplication)getApplication()).getDb(true);
        Query();
        //创建MovieAdapter实例
        movieAdapter=new MovieAdapter(this);
        //设置适配器
        movieListView.setAdapter(movieAdapter);
    }

    private void Query() {
        Cursor cursor=db.query("movie",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            movieList.add(new Movie(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
        }
    }

    //内部类  MovieAdapter适配器
    public class MovieAdapter extends BaseAdapter{
        private Context context;
        private LayoutInflater inflater;

        public MovieAdapter(Context context) {
            this.context=context;
            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return movieList.size();
        }

        @Override
        public Object getItem(int position) {
            return movieList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //设置缓冲区
            final Movie movie=movieList.get(position);
            ViewHolder viewHolder=new ViewHolder();
            if(convertView == null) {
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_movie_entry, null);

                viewHolder.name=convertView.findViewById(R.id.name);
                viewHolder.director=convertView.findViewById(R.id.director);
                viewHolder.price=convertView.findViewById(R.id.price);
                viewHolder.time=convertView.findViewById(R.id.time);
                viewHolder.description=convertView.findViewById(R.id.desc);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) convertView.getTag();
            }

            //设置文字
            viewHolder.name.setText(movie.getName());
            viewHolder.director.setText(movie.getDirector());
            String priceOpti=String.valueOf(movie.getPrice())+" 元";
            viewHolder.price.setText(priceOpti);
            String timeOpti=movie.getTime()+" 分钟";
            viewHolder.time.setText(timeOpti);
            viewHolder.description.setText(movie.getDescription());

            //设置条目点击事件
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //搜索使用intent跳转页面
                    Intent intent=new Intent(MainActivity.this, ScreenActivity.class);
                    //多余的listView不显示
                    Bundle bundle=new Bundle();
                    bundle.putSerializable(Constants.KEY_MOVIE,movie);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return convertView;
        }
        //缓冲区类
        public class ViewHolder{
            public TextView name;
            public TextView director;
            public TextView price;
            public TextView time;
            public TextView description;
        }
    }

}
