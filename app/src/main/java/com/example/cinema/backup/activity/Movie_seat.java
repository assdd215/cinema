package com.example.cinema.backup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.cinema.activity.ScreenActivity;
import com.example.cinema.activity.OrderSuccessActivity;
import com.example.cinema.model.Movie;
import com.example.cinema.R;
import com.example.cinema.model.Screen;

public class Movie_seat extends AppCompatActivity {
    private static CheckBox seat1_1;
    private CheckBox seat1_2;
    private CheckBox seat1_3;
    private CheckBox seat1_4;
    private CheckBox seat1_5;
    private CheckBox seat1_6;
    private CheckBox seat2_1;
    private CheckBox seat2_2;
    private CheckBox seat2_3;
    private CheckBox seat2_4;
    private CheckBox seat2_5;
    private CheckBox seat2_6;
    private CheckBox seat3_1;
    private CheckBox seat3_2;
    private CheckBox seat3_3;
    private CheckBox seat3_4;
    private CheckBox seat3_5;
    private CheckBox seat3_6;
    private CheckBox seat4_1;
    private CheckBox seat4_2;
    private CheckBox seat4_3;
    private CheckBox seat4_4;
    private CheckBox seat4_5;
    private CheckBox seat4_6;
    private Button btn_sure;
    private Button btn_quit;
    private String seats="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_seat);

        initView();

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                //Movie movie=(Movie)bundle.getSerializable("movie");
                Intent intent=new Intent(Movie_seat.this,ScreenActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                Movie movie=(Movie)bundle.getSerializable("movie");
                Screen screen=(Screen) bundle.getSerializable("screen");
                Intent intent=new Intent(Movie_seat.this,OrderSuccessActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putSerializable("movie",movie);
                bundle1.putSerializable("screen",screen);
                intent.putExtra("seats",seats);
                intent.putExtras(bundle1);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        seat1_1=findViewById(R.id.seat1_1);
        seat1_2=findViewById(R.id.seat1_2);
        seat1_3=findViewById(R.id.seat1_3);
        seat1_4=findViewById(R.id.seat1_4);
        seat1_5=findViewById(R.id.seat1_5);
        seat1_6=findViewById(R.id.seat1_6);
        seat2_1=findViewById(R.id.seat2_1);
        seat2_2=findViewById(R.id.seat2_2);
        seat2_3=findViewById(R.id.seat2_3);
        seat2_4=findViewById(R.id.seat2_4);
        seat2_5=findViewById(R.id.seat2_5);
        seat2_6=findViewById(R.id.seat2_6);
        seat3_1=findViewById(R.id.seat3_1);
        seat3_2=findViewById(R.id.seat3_2);
        seat3_3=findViewById(R.id.seat3_3);
        seat3_4=findViewById(R.id.seat3_4);
        seat3_5=findViewById(R.id.seat3_5);
        seat3_6=findViewById(R.id.seat3_6);
        seat4_1=findViewById(R.id.seat4_1);
        seat4_2=findViewById(R.id.seat4_2);
        seat4_3=findViewById(R.id.seat4_3);
        seat4_4=findViewById(R.id.seat4_4);
        seat4_5=findViewById(R.id.seat4_5);
        seat4_6=findViewById(R.id.seat4_6);
        btn_sure=findViewById(R.id.btn_sure);
        btn_quit=findViewById(R.id.btn_quit);

        seat1_1.setOnCheckedChangeListener(cb);
        seat1_2.setOnCheckedChangeListener(cb);
        seat1_3.setOnCheckedChangeListener(cb);
        seat1_4.setOnCheckedChangeListener(cb);
        seat1_5.setOnCheckedChangeListener(cb);
        seat1_6.setOnCheckedChangeListener(cb);
        seat2_1.setOnCheckedChangeListener(cb);
        seat2_2.setOnCheckedChangeListener(cb);
        seat2_3.setOnCheckedChangeListener(cb);
        seat2_4.setOnCheckedChangeListener(cb);
        seat2_5.setOnCheckedChangeListener(cb);
        seat2_6.setOnCheckedChangeListener(cb);
        seat3_1.setOnCheckedChangeListener(cb);
        seat3_2.setOnCheckedChangeListener(cb);
        seat3_3.setOnCheckedChangeListener(cb);
        seat3_4.setOnCheckedChangeListener(cb);
        seat3_5.setOnCheckedChangeListener(cb);
        seat3_6.setOnCheckedChangeListener(cb);
        seat4_1.setOnCheckedChangeListener(cb);
        seat4_2.setOnCheckedChangeListener(cb);
        seat4_3.setOnCheckedChangeListener(cb);
        seat4_4.setOnCheckedChangeListener(cb);
        seat4_5.setOnCheckedChangeListener(cb);
        seat4_6.setOnCheckedChangeListener(cb);
    }

    private CompoundButton.OnCheckedChangeListener cb=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                switch (buttonView.getId()){
                    case R.id.seat1_1:
                        seat1_1.setChecked(true);
                        seats += "1排1座 ";
                        break;
                    case R.id.seat1_2:
                        seat1_2.setChecked(true);
                        seats += "1排2座 ";
                        break;
                    case R.id.seat1_3:
                        seat1_3.setChecked(true);
                        seats += "1排3座 ";
                        break;
                    case R.id.seat1_4:
                        seat1_4.setChecked(true);
                        seats += "1排4座 ";
                        break;
                    case R.id.seat1_5:
                        seat1_5.setChecked(true);
                        seats += "1排5座 ";
                        break;
                    case R.id.seat1_6:
                        seat1_6.setChecked(true);
                        seats += "1排6座 ";
                        break;
                    case R.id.seat2_1:
                        seat2_1.setChecked(true);
                        seats += "2排1座 ";
                        break;
                    case R.id.seat2_2:
                        seat2_2.setChecked(true);
                        seats += "2排2座 ";
                        break;
                    case R.id.seat2_3:
                        seat2_3.setChecked(true);
                        seats += "2排3座 ";
                        break;
                    case R.id.seat2_4:
                        seat2_4.setChecked(true);
                        seats += "2排4座 ";
                        break;
                    case R.id.seat2_5:
                        seat2_5.setChecked(true);
                        seats += "2排5座 ";
                        break;
                    case R.id.seat2_6:
                        seat2_6.setChecked(true);
                        seats += "2排6座 ";
                        break;
                    case R.id.seat3_1:
                        seat3_1.setChecked(true);
                        seats += "3排1座 ";
                        break;
                    case R.id.seat3_2:
                        seat3_2.setChecked(true);
                        seats += "3排2座 ";
                        break;
                    case R.id.seat3_3:
                        seat3_3.setChecked(true);
                        seats += "3排3座 ";
                        break;
                    case R.id.seat3_4:
                        seat3_4.setChecked(true);
                        seats += "3排4座 ";
                        break;
                    case R.id.seat3_5:
                        seat3_5.setChecked(true);
                        seats += "3排5座 ";
                        break;
                    case R.id.seat3_6:
                        seat3_6.setChecked(true);
                        seats += "3排6座 ";
                        break;
                    case R.id.seat4_1:
                        seat4_1.setChecked(true);
                        seats += "4排1座 ";
                        break;
                    case R.id.seat4_2:
                        seat4_2.setChecked(true);
                        seats += "4排2座 ";
                        break;
                    case R.id.seat4_3:
                        seat4_3.setChecked(true);
                        seats += "4排3座 ";
                        break;
                    case R.id.seat4_4:
                        seat4_4.setChecked(true);
                        seats += "4排4座 ";
                        break;
                    case R.id.seat4_5:
                        seat4_5.setChecked(true);
                        seats += "4排5座 ";
                        break;
                    case R.id.seat4_6:
                        seat4_6.setChecked(true);
                        seats += "4排6座 ";
                        break;
                    default:
                        break;
                }
            }
        }
    };
}
