package com.example.user.hanzip.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.hanzip.R;
import com.example.user.hanzip.network.ApiValue;
import com.example.user.hanzip.network.response.RegisterResult;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.example.user.hanzip.login.LoginActivity.real_user_id;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button register;
    ImageButton input_image;
    String base_img;
    SharedPreferences mPref;
    private boolean isSelectImage = false;
    EditText input_addr,input_provide,input_caution,input_gender,input_price,input_name,input_provide2,input_caution2;
    String r_price,r_name,r_addr,r_provide1,r_caution1,r_gender,r_provide2,r_caution2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        input_addr = findViewById(R.id.input_home_addr);
        input_caution = findViewById(R.id.input_caution);
        input_gender = findViewById(R.id.input_home_sex);
        input_price = findViewById(R.id.input_home_price);
        input_provide = findViewById(R.id.input_provide);
        input_name = findViewById(R.id.house_title);
        input_provide2 = findViewById(R.id.input_provide2);
        input_caution2 = findViewById(R.id.input_caution2);

        input_image = findViewById(R.id.input_house_img);
        register = findViewById(R.id.register_btn1);


        input_image.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.input_house_img:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;

            case R.id.register_btn1:
                r_name = input_name.getText().toString();
                r_addr = input_addr.getText().toString();
                r_caution1 = input_caution.getText().toString();
                r_caution2 = input_caution2.getText().toString();
                r_gender = input_gender.getText().toString();
                r_price = input_price.getText().toString();
                r_provide1 = input_provide.getText().toString();
                r_provide2 = input_provide2.getText().toString();
                server();
                break;
        }
    }

    public static String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getApplicationContext().getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    base_img = getBase64String(img);
                    Log.d("base img",base_img);
                    SharedPreferences.Editor editor1 = mPref.edit();
                    editor1.putString("image", base_img);

                    editor1.commit();

                    in.close();
                    // 이미지 표시
                    isSelectImage = true;
                    input_image.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void server() {
        RegisterAsyncTask requestTask = new RegisterAsyncTask(new RegisterAsyncTask.RegisterTaskHandler() {

            @Override
            public void onSuccessAppAsyncTask(RegisterResult result) {
                if(result!=null) {
                    if(result.success){
                        Toast.makeText(getApplicationContext(), "매물 등록을 완료하였습니다.", Toast.LENGTH_LONG);
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                }
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


        requestTask.execute(ApiValue.API_REGISTER, base_img, r_name,r_provide1,r_provide2
        ,r_caution1,r_caution2,r_gender,r_price, real_user_id);


    }
}
