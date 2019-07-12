package com.example.user.hanzip.login;

import android.os.AsyncTask;

import com.example.user.hanzip.network.HttpRequest;
import com.example.user.hanzip.network.response.LoginResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class UserLoginAsyncTask extends AsyncTask<String, String, LoginResult> {

    private UserLoginResultHandler handler;


    public interface UserLoginResultHandler{
        public void onSuccessAppAsyncTask(LoginResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }


    public UserLoginAsyncTask(UserLoginResultHandler handler){
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected LoginResult doInBackground(String... strings) {
        String path = strings[0];
        String id = strings[1];
        String pw = strings[2];

        LoginResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("pw", pw);

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, LoginResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }


    @Override
    protected void onPostExecute(LoginResult loginResult) {
        super.onPostExecute(loginResult);

        if(loginResult != null){
            handler.onSuccessAppAsyncTask(loginResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
