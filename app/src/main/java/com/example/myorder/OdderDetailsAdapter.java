package com.example.myorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class OdderDetailsAdapter extends RecyclerView.Adapter<OdderDetailsAdapter.diewHolder> {

    Context context;
    List<Datum> list;
    String baseurl;

    public OdderDetailsAdapter(Context context, List<Datum> list, String baseurl) {
        this.context = context;
        this.list = list;
        this.baseurl = baseurl;
    }

    class diewHolder extends RecyclerView.ViewHolder {
        TextView txtOfferName, txtOfferId, txtOfferDelveryStatus, txtOfferTime;
        ImageView imageOffer;

        public diewHolder(@NonNull View itemView) {
            super(itemView);
            txtOfferName = itemView.findViewById(R.id.txtOfferName);
            txtOfferId = itemView.findViewById(R.id.txtOfferId);
            txtOfferDelveryStatus = itemView.findViewById(R.id.txtOfferDelveryStatus);
            txtOfferTime = itemView.findViewById(R.id.txtOfferTime);
            imageOffer = itemView.findViewById(R.id.imageOffer);
        }
    }

    @NonNull
    @Override
    public OdderDetailsAdapter.diewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_orderlistview, parent, false);
        return new OdderDetailsAdapter.diewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diewHolder holder, int position) {
        holder.txtOfferName.setText(list.get(position).getOrderTitle());
        holder.txtOfferId.setText("OrderId #" + " " +
                list.get(position).getOrderId());

        // holder.txtOfferId.setText(list.get(position).getOrderId());
        holder.txtOfferDelveryStatus.setText(list.get(position).getOrderStatus());
        holder.txtOfferTime.setText(list.get(position).getAddedOn());
        Glide.with(context).load(baseurl + list.get(position).getImage()).into(holder.imageOffer)
        ;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}


