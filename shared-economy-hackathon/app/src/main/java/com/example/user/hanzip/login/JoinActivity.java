package com.example.user.hanzip.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.hanzip.R;

public class JoinActivity extends AppCompatActivity {

    SharedPreferences mPref;
    EditText n_userid,n_userpw,n_username,n_phone;
    String new_userid,new_userpw,new_username,new_phone;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        n_userid = findViewById(R.id.nuser_id);
        n_userpw = findViewById(R.id.nuser_pw);
        n_username = findViewById(R.id.nuser_name);
        n_phone = findViewById(R.id.nuser_phone);
        next = findViewById(R.id.next);
        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_userid = n_userid.getText().toString();
                new_userpw = n_userpw.getText().toString();
                new_username = n_username.getText().toString();
                new_phone = n_phone.getText().toString();
                if(new_userid.isEmpty() || new_userpw.isEmpty() || new_username.isEmpty()||new_phone.isEmpty()){
                    Toast.makeText(JoinActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                next();
            }
        });

    }

    public void next(){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("new_id", new_userid);
        editor.putString("new_pw", new_userpw);
        editor.putString("new_name", new_username);
        editor.putString("new_phone", new_phone);
        editor.commit();
        Intent intent = new Intent(JoinActivity.this, JoinActivity2.class);
        startActivity(intent);
    }
}
