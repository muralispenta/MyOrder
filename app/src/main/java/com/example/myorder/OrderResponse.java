package com.example.myorder;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderResponse {

    @SerializedName(Constans.STATUS)
    public String status;
    @SerializedName(Constans.MESSAGE)
    public String message;

    @SerializedName(Constans.Data)
    public ArrayList<Oreder> offerArrayList;


}
