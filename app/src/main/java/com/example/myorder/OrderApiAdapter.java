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
        OkHttpClient okHttpClient = getHttpClient(context);
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
        OkHttpClient okHttpClient = getHttpClient(context);
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

    private static OkHttpClient getHttpClient(Context context) {
        if (okHttpClient != null) return okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.MINUTES)
                //.callTimeout(0, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES);
      /*  builder.interceptors().add(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                return onOnIntercept(chain, listener);
            }
        });*/

      /*  Interceptor loggingInterceptor = getHttpLoggingInterceptor(context);
        if (loggingInterceptor != null) {
            builder.addInterceptor(loggingInterceptor);
        }*/
        okHttpClient = builder.build();
        return okHttpClient;
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


   /* private static Response onOnIntercept(Interceptor.Chain chain, OnConnectionTimeoutListener listener)
            throws IOException {
        try {
            Response response = chain.proceed(chain.request());
            String content = response.body().toString();
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).build();
        } catch (SocketTimeoutException exception) {
            exception.printStackTrace();
            if (listener != null)
                listener.onConnectionTimeout();
        }
        return chain.proceed(chain.request());
    }*/
}

