package com.example.cinema.backup.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cinema.activity.ScreenActivity;
import com.example.cinema.model.Movie;
import com.example.cinema.db.MovieDBOpenHelper;
import com.example.cinema.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView movieListView;
    private List<Movie> movieList;
    private MovieDBOpenHelper movieDBOpenHelper;
    private SQLiteDatabase db;
    private MovieAdapter movieAdapter;
    private List<String> movieName;
    private List<String> movieDirector;
    private List<String> movieActor;
    private List<Integer> moviePrice;
    private List<String> movieTime;
    private List<String> movieType;
    private List<String> movieDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据数组
        initArray();
        movieListView=findViewById(R.id.listView);
        //创建movie类的实例的链表
        movieList=new ArrayList<Movie>();
        //创建MovieDBOpenHelper的实例
        movieDBOpenHelper=new MovieDBOpenHelper(this);
        //得到数据库
        db=movieDBOpenHelper.getWritableDatabase();
//        Insert();
        Query();

        //创建MovieAdapter实例
        movieAdapter=new MovieAdapter(this);
        //设置适配器
        movieListView.setAdapter(movieAdapter);


    }

    private void initArray() {
        Log.i("INIT","start initial arraylist");
        movieName=new ArrayList<>();
        movieName.add("X战警：逆转未来");
        movieName.add("澳门风云");
        movieName.add("冰雪奇缘");
        movieName.add("超凡蜘蛛侠2");
        movieName.add("催眠大师");
        movieName.add("终结者：创世纪");
        movieName.add("人在囧途之泰囧");
        movieName.add("夏日大作战");

        movieDirector=new ArrayList<>();
        movieDirector.add("布莱恩·辛格");
        movieDirector.add("王晶");
        movieDirector.add("克里斯·巴克");
        movieDirector.add("马克·韦布");
        movieDirector.add("陈正道");
        movieDirector.add("阿兰·泰勒");
        movieDirector.add("徐峥");
        movieDirector.add("细田守");

        movieActor=new ArrayList<>();
        movieActor.add("休·杰克曼/迈克尔·法斯宾德");
        movieActor.add("周润发/谢霆锋/杜汶泽");
        movieActor.add("克里斯汀·贝尔/伊迪娜·门泽尔");
        movieActor.add("安德鲁·加菲尔德/艾玛·斯通");
        movieActor.add("徐峥/莫文蔚");
        movieActor.add("艾米莉亚·克拉克");
        movieActor.add("徐峥/王宝强/黄渤");
        movieActor.add("神木隆之介/谷村美月");

        moviePrice=new ArrayList<>();
        moviePrice.add(120);
        moviePrice.add(90);
        moviePrice.add(100);
        moviePrice.add(120);
        moviePrice.add(90);
        moviePrice.add(100);
        moviePrice.add(100);
        moviePrice.add(120);

        movieDesc=new ArrayList<>();
        movieDesc.add("故事的设定发生在当下，变种人族群遭到了前所未有的毁灭性打击，而这一切的根源是“魔形女”瑞文。。。。");
        movieDesc.add("影片讲述的是外号“赢尽天下无敌手”的石一坚和他的朋友家人一起布下并利用局从犯罪集团的手中逃脱的故事。");
        movieDesc.add("影片讲述一个严冬咒语令王国被冰天雪地永久覆盖，安娜和山民克里斯托夫以及他的驯鹿搭档组队出发，为寻找姐姐拯救王国展开一段冒险");
        movieDesc.add("影片讲述了彼得·帕克的生活依然很忙，而格温毕业后考虑去牛津大学继续深造。“电光人”出现后，彼得的生活更不得安宁、、、");
        movieDesc.add("影片讲述了知名心理治疗师徐瑞宁和棘手的女病人任小妍之间发生的故事。");
        movieDesc.add("超级电脑“天网”阻止人类抵抗领袖John Connor诞生的行动失败，时隔13年后，在“审判日”到来之前。。。");
        movieDesc.add("商业成功人士徐朗用了五年时间发明了一种叫“油霸”的神奇产品','商业成功人士徐朗用了五年时间发明了一种叫“油霸”的神奇产品、、");
        movieDesc.add("高中生小矶健二夏日的一天，他应邀来到美丽的学姐——阵内夏希的家乡打工。结果发现...");

        movieTime=new ArrayList<>();
        movieTime.add("129");
        movieTime.add("93");
        movieTime.add("102");
        movieTime.add("142");
        movieTime.add("102");
        movieTime.add("130");
        movieTime.add("105");
        movieTime.add("114");

        movieType=new ArrayList<>();
        movieType.add("动作/科幻");
        movieType.add("喜剧/动作");
        movieType.add("动画/冒险");
        movieType.add("科幻/奇幻");
        movieType.add("剧情/悬疑");
        movieType.add("动作/科幻");
        movieType.add("喜剧");
        movieType.add("喜剧/动画");
        Log.i("INIT","finish initial arraylist");
    }

//    private void Query() {
//        Cursor cursor=db.query("movie",null,null,null,null,null,null);
//        while(cursor.moveToNext()){
//            int id=cursor.getInt(0);
//            String name=cursor.getString(1);
//            String director=cursor.getString(2);
//            String actor=cursor.getString(3);
//            String price=cursor.getString(4);
//            String time=cursor.getString(5);
//            String type=cursor.getString(6);
//            String description=cursor.getString(7);
//            Movie movie=new Movie(id,name,director,actor,price,time,type,description);
//
//
//            movieList.add(movie);
//        }
//    }

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
                    bundle.putSerializable("movie",movie);
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
    public void Insert(){
        db.execSQL("DELETE FROM movie;");
        for(int i = 0; i < movieName.size(); ++i) {
            ContentValues values = new ContentValues();
            values.put("id", i);
            values.put("name", movieName.get(i));
            values.put("director", movieDirector.get(i));
            values.put("price", String.valueOf(moviePrice.get(i)));
            values.put("time", movieTime.get(i));
            values.put("description", movieDesc.get(i));
            values.put("actor", movieActor.get(i));
            values.put("type", movieType.get(i));
            Log.i("PRINT", movieName.get(i));
            db.insert("movie", null, values);
        }
    }
}
