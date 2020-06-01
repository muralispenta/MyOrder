package com.example.myorder;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderApiAdapter {

    private static final String BASE_URL = Constans.BASE_URL_LIVE;

    private static Retrofit retrofit = null;
    private static Retrofit regionRetrofit = null;
    private static volatile OkHttpClient okHttpClient;

    public static Retrofit getClient(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRegionApiClient(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (regionRetrofit == null) {
            regionRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return regionRetrofit;
    }



    private static Interceptor getHttpLoggingInterceptor(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(getHttpLoggingLevel(context));
        return interceptor;
    }

    private static HttpLoggingInterceptor.Level getHttpLoggingLevel(Context context) {
        try {
            return HttpLoggingInterceptor.Level.valueOf(String.valueOf(HttpLoggingInterceptor.Level.BODY));
        } catch (Throwable t) {
            //Ignore
        }
        return HttpLoggingInterceptor.Level.NONE;
    }


}

