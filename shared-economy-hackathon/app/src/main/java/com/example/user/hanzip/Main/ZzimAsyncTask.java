package com.example.user.hanzip.Main;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.hanzip.network.HttpRequest;
import com.example.user.hanzip.network.response.RegisterResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class ZzimAsyncTask extends AsyncTask<String, Integer, RegisterResult> {


    private ZzimAsyncTask.ZzimAsyncTaskHandler handler;


    public interface ZzimAsyncTaskHandler{
        public void onSuccessAppAsyncTask(RegisterResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public ZzimAsyncTask(ZzimAsyncTask.ZzimAsyncTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected RegisterResult doInBackground(String... strings) {
        String path = strings[0];
        String forSaleUserId =strings[1];
        String userId = strings[2];



        RegisterResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("forSaleUserId", forSaleUserId);
        params.put("userId", userId);


        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "POST", params);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, RegisterResult.class);

            Log.d("result = ", String.valueOf(result));
            Log.d("http", "str > " + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(RegisterResult serverSuccessCheckResult) {
        super.onPostExecute(serverSuccessCheckResult);


        if(serverSuccessCheckResult != null){
            handler.onSuccessAppAsyncTask(serverSuccessCheckResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
