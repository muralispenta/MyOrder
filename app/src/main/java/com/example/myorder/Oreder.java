package com.example.myorder;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Oreder implements Parcelable {

    public static final Creator<Oreder> CREATOR = new Creator<Oreder>() {
        @Override
        public Oreder createFromParcel(Parcel in) {
            return new Oreder(in);
        }

        @Override
        public Oreder[] newArray(int size) {
            return new Oreder[size];
        }
    };

    @SerializedName(Constans.ORDER_TITLE)
    private String orderTitle;
    @SerializedName(Constans.ADDED_ON)
    private String added_on;
    @SerializedName(Constans.ORDER_ID)
    private int id;
    @SerializedName(Constans.IMAGE)
    private String image;


    public String getOrderTitle() {
        return orderTitle;
    }

    public String getAdded_on() {
        return added_on;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderTitle);
        parcel.writeString(added_on);
        parcel.writeInt(id);
        parcel.writeString(image);

    }

    private Oreder(Parcel in) {
    orderTitle = in.readString();
    added_on = in.readString();
    id = in.readInt();
    image = in.readString();

    }

}
