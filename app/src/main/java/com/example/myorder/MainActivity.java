package com.example.myorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private OdderDetailsAdapter odderDetailsAdapter;
    private ArrayList<Oreder> oreders = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);

        odderDetailsAdapter = new OdderDetailsAdapter(oreders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(odderDetailsAdapter);

        fetchNextPageOffers();

    }

    
    private void  checkInternetConnection(){
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("intgernetError")
                    .setPositiveButton("OK", null).show();
        }
    }

    private void fetchNextPageOffers() {

     Oreder oreder = getIntent().getParcelableExtra("order");
        OrderInterface cancerApiService = OrderApiAdapter.getClient
                (getApplicationContext()).create(OrderInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constans.API_KEY, "2308691");
            jsonObject.put(Constans.LANGUAGE, "en");
            jsonObject.put(Constans.USERID, "15");
                   } catch (Exception ignored) {
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                jsonObject.toString());

        
        Call<OrderResponse> call = cancerApiService.order(oreder != null ? oreder.getOrderTitle() : null,
                oreder != null ? oreder.getAdded_on() : null, oreder != null ? oreder.getImage() : null);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(@NonNull Call<OrderResponse> call,
                                   @NonNull Response<OrderResponse> response) {

               if (response.body() != null && response.isSuccessful()){
                   setOfferAdapter(response.body().offerArrayList);
               }
            }

            @Override
            public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable t) {

            }
        });
    }



        private void setOfferAdapter(ArrayList<Oreder> offers) {


            odderDetailsAdapter = new OdderDetailsAdapter(oreders);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                    DividerItemDecoration.VERTICAL) {
                @Override
                public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                    // Do not draw the divider
                }
            });


            recyclerView.setAdapter(odderDetailsAdapter);
        }


}
