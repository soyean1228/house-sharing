package com.example.user.hanzip.mypage;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.hanzip.Main.MainListAsyncTask;
import com.example.user.hanzip.network.HttpRequest;
import com.example.user.hanzip.network.response.MainResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MypageListAsyncTask extends AsyncTask<String, Integer, MainResult> {


    private MypageListAsyncTask.MypageListAsyncTaskHandler handler;


    public interface MypageListAsyncTaskHandler{
        public void onSuccessAppAsyncTask(MainResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public MypageListAsyncTask(MypageListAsyncTask.MypageListAsyncTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MainResult doInBackground(String... strings) {
        String path = strings[0];
        MainResult result  = null;

        HttpRequest request = new HttpRequest();

        try {
            String str = request.callRequestServer(path,  "GET",null);

            Gson gson = new GsonBuilder().create();
            result = gson.fromJson(str, MainResult.class);

            Log.d("result = ", String.valueOf(result));
            Log.d("http", "str > " + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(MainResult mainResult) {
        super.onPostExecute(mainResult);


        if(mainResult != null){
            handler.onSuccessAppAsyncTask(mainResult);

        }else{
            handler.onFailAppAsysncask();
        }
    }
}
