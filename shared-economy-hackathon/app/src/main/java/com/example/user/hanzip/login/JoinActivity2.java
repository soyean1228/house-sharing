package com.example.user.hanzip.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.hanzip.R;
import com.example.user.hanzip.network.ApiValue;
import com.example.user.hanzip.network.response.JoinResult;

public class JoinActivity2 extends AppCompatActivity {
    SharedPreferences mPref;
    EditText n_userage,n_addr;
    RadioButton n_m,n_w,n_y,n_o;
    String new_userage,new_usergender = "",new_userrole = "";
    Button join;
    RadioGroup rg_gender,rg_role;
    String new_id,new_pw,new_name,new_phone,new_addr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        rg_gender =findViewById(R.id.radioGroup_gender);
        rg_role = findViewById(R.id.radioGroup_role);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        n_userage = findViewById(R.id.nuser_age);
        n_addr = findViewById(R.id.nuser_addr);
        n_m = findViewById(R.id.nuser_m);
        n_w = findViewById(R.id.nuser_w);
        n_y = findViewById(R.id.nuser_y);
        n_o = findViewById(R.id.nuser_o);
        join = findViewById(R.id.join);
        new_id = mPref.getString("new_id", "");
        new_pw = mPref.getString("new_pw", "");
        new_name = mPref.getString("new_name", "");
        new_phone = mPref.getString("new_phone", "");

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nuser_m) {
                    new_usergender = "여";
                } else if (checkedId == R.id.nuser_w) {
                    new_usergender = "남";
                }
            }
        });

        rg_role.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.nuser_o) {
                    new_userrole = "청년";
                } else if (checkedId == R.id.nuser_y) {
                    new_userrole = "노인";
                }
            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_userage = n_userage.getText().toString();
                new_addr = n_addr.getText().toString();
                if (new_addr.isEmpty() || new_userage.isEmpty() || new_usergender.isEmpty() || new_userrole.isEmpty()) {
                    Toast.makeText(JoinActivity2.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                join_in();
            }
        });

    }


    public void join_in(){
        JoinAsyncTask joinAsyncTask = new JoinAsyncTask(new JoinAsyncTask.JoinResultHandler() {

            @Override
            public void onSuccessAppAsyncTask(JoinResult result) {

                if(result != null){
                    if(result.success){
                        Toast.makeText(JoinActivity2.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinActivity2.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(JoinActivity2.this, "중복된 아이디가 있습니다.다른 아이디로 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    Toast.makeText(JoinActivity2.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(JoinActivity2.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(JoinActivity2.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

            }
        });


        joinAsyncTask.execute(ApiValue.API_JOIN,new_addr,new_userage,new_id,new_name,new_phone, new_pw, new_userrole,new_usergender);
    }
}
