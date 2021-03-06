package com.example.cinema.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.model.Movie;
import com.example.cinema.model.Screen;
import com.example.cinema.utils.Constants;

//TODO 这里是还没写完吗
public class OrderSuccessActivity extends AppCompatActivity {
    private TextView orderName;
    private TextView place;
    private TextView date;
    private TextView time;
    private TextView seat;
    private Button btn_success;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();

        Bundle bundle=getIntent().getExtras();
        Movie movie=(Movie)bundle.getSerializable(Constants.KEY_MOVIE);
        Screen screen=(Screen)bundle.getSerializable(Constants.KEY_SCREEN);
        String seats=getIntent().getStringExtra(Constants.KEY_SEAT);

        seat.setText(seats);
        orderName.setText(movie.getName());
        place.setText(screen.getScreenPlace());
        date.setText(screen.getScreenDate());
        time.setText(screen.getScreenTime());

        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderSuccessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        orderName=findViewById(R.id.orderName);
        place=findViewById(R.id.Place);
        date=findViewById(R.id.Date);
        time=findViewById(R.id.Time);
        seat=findViewById(R.id.success_seat);
        btn_success=findViewById(R.id.btn_success);
    }
}
