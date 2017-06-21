package com.example.abhayalekal.firebaseappfest.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faheem on 21/06/17.
 */
public class StockObject implements Parcelable {
    @SerializedName("symbol")
    public String id;
    @SerializedName("ltp")
    public String currentValue;
    @SerializedName("open")
    public String openValue;
    @SerializedName("high")
    public String high;
    @SerializedName("low")
    public String low;
    @SerializedName("boughCount")
    public String boughtCount;
    @SerializedName("soldCount")
    public String soldCount;



    public StockObject() {

    }

    protected StockObject(Parcel in) {
        id = in.readString();
        currentValue = in.readString();
        openValue = in.readString();
        high = in.readString();
        low = in.readString();
        boughtCount = in.readString();
        soldCount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(currentValue);
        dest.writeString(openValue);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(boughtCount);
        dest.writeString(soldCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StockObject> CREATOR = new Creator<StockObject>() {
        @Override
        public StockObject createFromParcel(Parcel in) {
            return new StockObject(in);
        }

        @Override
        public StockObject[] newArray(int size) {
            return new StockObject[size];
        }
    };
}
