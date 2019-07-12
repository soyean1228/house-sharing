package com.example.user.hanzip.mypage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.hanzip.Main.RegisterActivity;
import com.example.user.hanzip.R;
import com.example.user.hanzip.network.ApiValue;
import com.example.user.hanzip.network.response.MainResult;
import com.example.user.hanzip.vo.MainListVO;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.hanzip.login.LoginActivity.real_user_id;

public class MypageActivity extends AppCompatActivity {
    private MypageListAdapter mypageListAdapter;
    private RecyclerView recyclerView;
    private List<MainListVO> InfoData = new ArrayList<MainListVO>();
    Button plus_house;
    TextView mypage_user_name;

    public void setInfoData(ArrayList<MainListVO> InfoData){
        this.InfoData = InfoData;
    }

    private View.OnClickListener itemActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        mypage_user_name = findViewById(R.id.mypage_user_name);
        plus_house = findViewById(R.id.plus_house);
        recyclerView = findViewById(R.id.mypage_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mypageListAdapter = new MypageListAdapter(getApplicationContext(),itemActivityListener);
        recyclerView.setAdapter(mypageListAdapter);
        mypage_user_name.setText(real_user_id+" 님");

        plus_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypageActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mypageImg();

    }

    @Override
    public void onResume() {
        super.onResume();

        mypageListAdapter.notifyDataSetChanged();
    }


    public void mypageImg() {

        MypageListAsyncTask requestTask = new MypageListAsyncTask(new MypageListAsyncTask.MypageListAsyncTaskHandler() {
            @Override
            public void onSuccessAppAsyncTask(MainResult result) {
                if(result != null && result.success) {
                    mypageListAdapter.setImgData(result.mainHomeinfo);
                    mypageListAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    mypageListAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "서버 통신에 실패하였습니다.", Toast.LENGTH_LONG);
                }
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


        requestTask.execute(ApiValue.API_ZZIM+"/"+real_user_id);

    }
}
