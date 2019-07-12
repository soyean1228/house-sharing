package com.example.user.hanzip.vo;

import com.google.gson.annotations.SerializedName;

public class MainListVO {

    @SerializedName("imagePath")
    private String imagePath = "";

    @SerializedName("address")
    private String address = "";

    @SerializedName("price")
    private int price = 0;

    @SerializedName("age")
    private int age =0;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @SerializedName("name")
    private String name ="";

    @SerializedName("userId")
    private String userId ="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("offerFirst")
    private String offerFirst = "";

    @SerializedName("offerSecond")
    private String offerSecond = "";

    @SerializedName("phoneNumber")
    private String phoneNumber = "";

    @SerializedName("precautionFirst")
    private String precautionFirst = "";

    @SerializedName("precautionSecond")
    private String precautionSecond = "";


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOfferFirst() {
        return offerFirst;
    }

    public void setOfferFirst(String offerFirst) {
        this.offerFirst = offerFirst;
    }

    public String getOfferSecond() {
        return offerSecond;
    }

    public void setOfferSecond(String offerSecond) {
        this.offerSecond = offerSecond;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrecautionFirst() {
        return precautionFirst;
    }

    public void setPrecautionFirst(String precautionFirst) {
        this.precautionFirst = precautionFirst;
    }

    public String getPrecautionSecond() {
        return precautionSecond;
    }

    public void setPrecautionSecond(String precautionSecond) {
        this.precautionSecond = precautionSecond;
    }
}
