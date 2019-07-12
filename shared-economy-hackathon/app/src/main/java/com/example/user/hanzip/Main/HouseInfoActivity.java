package com.example.user.hanzip.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.hanzip.R;
import com.example.user.hanzip.network.ApiValue;
import com.example.user.hanzip.network.response.MainResult;
import com.example.user.hanzip.network.response.RegisterResult;

import static com.example.user.hanzip.login.LoginActivity.real_user_id;

public class HouseInfoActivity extends AppCompatActivity {
    ImageView house_img;
    TextView home_addr,home_price,home_sex,provide1,provide2,caution1,caution2;
    String s_a,s_p,s_s,s_p1,s_p2,s_c1,s_c2,s_img,user_id;
    ImageButton zzim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);

        home_addr = findViewById(R.id.home_addr);
        home_price = findViewById(R.id.home_price);
        home_sex = findViewById(R.id.home_sex);
        provide1 = findViewById(R.id.provide1);
        provide2 = findViewById(R.id.provide2);
        caution1 = findViewById(R.id.caution1);
        caution2 = findViewById(R.id.caution2);
        zzim = findViewById(R.id.zzim);

        house_img = findViewById(R.id.house_img);

        s_img = getIntent().getStringExtra("img");
        s_a = getIntent().getStringExtra("addr");
        s_p = getIntent().getStringExtra("price");
        s_p1 = getIntent().getStringExtra("offer1");
        s_p2= getIntent().getStringExtra("offer2");
        s_c1 = getIntent().getStringExtra("caution1");
        s_c2 = getIntent().getStringExtra("caution2");
        user_id = getIntent().getStringExtra("userId");

        home_addr.setText(s_a);
        home_price.setText(s_p+"만원/월");
        home_sex.setText("여");
        provide2.setText(s_p2);
        provide1.setText(s_p1);
        caution2.setText(s_c2);
        caution1.setText(s_c1);
        Glide.with(this).load(s_img).into( house_img);

        zzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zzim.setImageResource(R.drawable.heart2);

                server();
            }
        });

    }

    void server(){

        ZzimAsyncTask requestTask = new ZzimAsyncTask(new ZzimAsyncTask.ZzimAsyncTaskHandler() {
            @Override
            public void onSuccessAppAsyncTask(RegisterResult result) {
                Toast.makeText(getApplicationContext(), "찜하였습니다.", Toast.LENGTH_LONG);
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(getApplicationContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(getApplicationContext(), "사용자가 해당 작업을 중지하였습니다.", Toast.LENGTH_LONG);
            }

        });


        requestTask.execute(ApiValue.API_ZZIM_BUTTON,user_id,real_user_id);
    }


}
