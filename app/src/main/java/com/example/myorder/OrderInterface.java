package com.example.myorder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OrderInterface {

    @FormUrlEncoded
    @POST("/Myorders")
    Call<OrderResponse> order(@Field(Constans.ORDER_TITLE) String title,
                                           @Field(Constans.ADDED_ON) String addon,
                                           @Field(Constans.ORDER_ID) String oderId);
}
