package com.example.user.hanzip.network.response;

import com.example.user.hanzip.vo.MainListVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainResult {

    @SerializedName("success")
    public boolean success = false;


    @SerializedName("forSaleInfos")
    public ArrayList<MainListVO> mainHomeinfo = new ArrayList<MainListVO>();

    public ArrayList<MainListVO> getMainHomeinfo() {
        return mainHomeinfo;
    }

    public void setMainHomeinfo(ArrayList<MainListVO> mainHomeinfo) {
        this.mainHomeinfo = mainHomeinfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }






}
