package com.example.myorder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OdderDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
private LayoutInflater layoutInflater;
    private ArrayList<Oreder> oreders;


    public OdderDetailsAdapter(ArrayList<Oreder> oreders) {
        this.oreders = oreders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_orderlistview, parent, false);
        return new OfferHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        Oreder oreder = oreders.get(position);
        OfferHolder offerHolder = (OfferHolder) holder;

        TextView txtOfferName = offerHolder.getTxtOfferName();
        if (!TextUtils.isEmpty(oreder.getOrderTitle())) {
            txtOfferName.setText(oreder.getOrderTitle());

        }


        TextView txtOfferTime = offerHolder.getOfferTeime();
        if (!TextUtils.isEmpty(oreder.getAdded_on())) {
            txtOfferTime.setText(oreder.getOrderTitle());

        }
        TextView txtOfferId = offerHolder.getTxtOfferId();
        if (!TextUtils.isEmpty(oreder.getAdded_on())) {
            txtOfferId.setText(oreder.getAdded_on());

        }

        ImageView imgOffer = offerHolder.getImgOffer();

    }

    @Override
    public int getItemCount() {
        return oreders.size();
    }

    private class OfferHolder extends RecyclerView.ViewHolder {
        private TextView txtOfferTime, txtOfferName, txtOfferId,
                txtOfferDelveryStatus;
        private ImageView imgOffer;
        private FrameLayout frmContainer;

        private CardView container;

        OfferHolder(@NonNull View itemView) {
            super(itemView);
        }


        public TextView getOfferTeime() {
            if (txtOfferTime == null) {
                txtOfferTime = itemView.findViewById(R.id.txtOfferTime);
            }
            return txtOfferTime;
        }

        public TextView getTxtOfferName() {
            if (txtOfferName == null) {
                txtOfferName = itemView.findViewById(R.id.txtOfferName);
            }
            return txtOfferName;
        }

        public TextView getTxtOfferId() {
            if (txtOfferId == null) {
                txtOfferId = itemView.findViewById(R.id.txtOfferId);
            }
            return txtOfferId;
        }


        public ImageView getImgOffer() {
            if (imgOffer == null) {
                imgOffer = itemView.findViewById(R.id.imageOffer);
            }
            return imgOffer;
        }
    }
}


