package com.example.user.hanzip.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.hanzip.Main.MainActivity;
import com.example.user.hanzip.R;
import com.example.user.hanzip.network.ApiValue;
import com.example.user.hanzip.network.response.LoginResult;

public class LoginActivity extends AppCompatActivity {
    public static String real_user_id,real_name,real_phone,real_addr,real_role;
    public static int real_age;
    EditText user_id,user_pw;
    Button login,join;
    String u_id,u_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        login = findViewById(R.id.login);
        join = findViewById(R.id.join);

        u_id = user_id.getText().toString();
        u_pw = user_pw.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_id = user_id.getText().toString();
                u_pw = user_pw.getText().toString();
                if(u_id.isEmpty() || u_pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                login();

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void login(){
        UserLoginAsyncTask LoginAsyncTask = new UserLoginAsyncTask(new UserLoginAsyncTask.UserLoginResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(LoginResult result) {
                if(result != null){
                    if(result.success){
                        real_user_id = u_id;
                        real_name = result.getName().toString();
                        real_phone = result.getPhoneNumber().toString();
                        real_addr = result.getAddress().toString();
                        real_age = result.getAge();
                        real_role = result.getRole().toString();
                        Log.d("real_user_id", String.valueOf(real_user_id));
                        Log.d("real_user_name", String.valueOf(real_name));
                        Log.d("real_user_phone", String.valueOf(real_phone));
                        Log.d("real_user_addr", String.valueOf(real_addr));
                        Log.d("real_user_age", String.valueOf(real_age));
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{

                        Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.00", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(LoginActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });


        LoginAsyncTask.execute(ApiValue.API_Login,u_id,u_pw);
    }



}
