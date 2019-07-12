package com.example.user.hanzip.login;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.hanzip.network.HttpRequest;
import com.example.user.hanzip.network.response.JoinResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class JoinAsyncTask extends AsyncTask<String, Integer, JoinResult> {


    private JoinResultHandler handler;


    public interface JoinResultHandler{
        public void onSuccessAppAsyncTask(JoinResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public JoinAsyncTask(JoinResultHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JoinResult doInBackground(String... strings) {
        String path = strings[0];
        String address = strings[1];
        int age = Integer.parseInt(strings[2]);
        String id = strings[3];
        String name = strings[4];
        String phoneNumber = strings[5];
        String pw = strings[6];
        String role = strings[7];
        String sex = strings[8];

        JoinResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("address", address);
        params.put("age", age);
        params.put("id", id);
        params.put("name", name);
        params.put("phoneNumber", phoneNumber);
        params.put("pw", pw);
        params.put("role", role);
        params.put("sex", sex);

        Log.d("address",address);
        Log.d("age", String.valueOf(age));
        Log.d("id",id);
        Log.d("name",name);
        Log.d("phoneNumber",phoneNumber);
        Log.d("pw",pw);
        Log.d("role",role);
        Log.d("sex",sex);


        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, JoinResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(JoinResult serverSuccessCheckResult) {
        super.onPostExecute(serverSuccessCheckResult);


        if(serverSuccessCheckResult != null){
            handler.onSuccessAppAsyncTask(serverSuccessCheckResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
