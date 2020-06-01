package com.example.myorder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderInterface {
    public static final String BASE_URL_LIVE = "http://volive.in/dresscode_new/api/services/";

    @FormUrlEncoded
    @POST("Myorders")
    Call<Order> order(@FieldMap Map<String, Object> parameters);
}
