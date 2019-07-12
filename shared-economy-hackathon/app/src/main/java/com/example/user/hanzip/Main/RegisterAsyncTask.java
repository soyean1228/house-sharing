package com.example.user.hanzip.Main;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.hanzip.network.HttpRequest;
import com.example.user.hanzip.network.response.RegisterResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class RegisterAsyncTask extends AsyncTask<String, Integer, RegisterResult> {


    private RegisterTaskHandler handler;


    public interface RegisterTaskHandler{
        public void onSuccessAppAsyncTask(RegisterResult result);
        public void onFailAppAsysncask();
        public void onCancelAppAsyncTask();
    }



    public RegisterAsyncTask(RegisterAsyncTask.RegisterTaskHandler handler){
        this.handler = handler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected RegisterResult doInBackground(String... strings) {
        String path = strings[0];
        String imagePath =strings[1];
        String name = strings[2];
        String offerFirst = strings[3];
        String offerSecond = strings[4];
        String precautionFirst = strings[5];
        String precautionSecond = strings[6];
        String preferredSex = strings[7];
        int price = Integer.parseInt(strings[8]);
        String userId = strings[9];


        RegisterResult result  = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("imagePath", imagePath);
        params.put("name", name);
        params.put("offerFirst",offerFirst);
        params.put("offerSecond", offerSecond);
        params.put("precautionFirst",precautionFirst);
        params.put("precautionSecond", precautionSecond);
        params.put("preferredSex",preferredSex);
        params.put("price", price);
        params.put("userId",userId);

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
