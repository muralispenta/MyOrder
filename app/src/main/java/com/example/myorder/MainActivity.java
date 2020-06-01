package com.example.myorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private OdderDetailsAdapter odderDetailsAdapter;
    private RecyclerView recyclerView;
    OrderInterface orderInterface;
    List<Datum> datumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datumList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(OrderInterface.BASE_URL_LIVE).addConverterFactory(GsonConverterFactory.create()).build();
        orderInterface = retrofit.create(OrderInterface.class);


        fetchNextPageOffers();

    }


    private void checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("intgernetError").show();
        }
    }

    private void fetchNextPageOffers() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Please check your internet connections").show();
        } else {

            datumList.clear();

            Map<String, Object> parametes = new HashMap<String, Object>();
            parametes.put(Constans.API_KEY, 2308691);
            parametes.put(Constans.LANGUAGE, "en");
            parametes.put(Constans.USERID, 15);
            Call<Order> call = orderInterface.order(parametes);
            call.enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if (response.body() != null && response.isSuccessful() && response.body().getStatus().equals(1)) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            Datum datum = new Datum();
                            datum.setImage(response.body().getData().get(i).getImage());
                            datum.setAddedOn(response.body().getData().get(i).getAddedOn());
                            datum.setOrderId(response.body().getData().get(i).getOrderId());
                            datum.setOrderTitle(response.body().getData().get(i).getOrderTitle());
                            datum.setOrderStatus(response.body().getData().get(i).getOrderStatus());
                            datum.setPaidAmount(response.body().getData().get(i).getPaidAmount());
                            datumList.add(datum);
                            //Toast.makeText(MainActivity.this, "" + response.body().getData().get(i).getOrderTitle(), Toast.LENGTH_SHORT).show();

                        }
                        odderDetailsAdapter = new OdderDetailsAdapter(MainActivity.this, datumList, response.body().getBaseUrl());
                        recyclerView.setAdapter(odderDetailsAdapter);
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    // Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


        }

    }
}
