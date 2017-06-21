package com.example.abhayalekal.firebaseappfest.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by faheem on 21/06/17.
 */
public class StockObject implements Parcelable {
    public String id;
    public String name;
    public float currentValue;
    public float openValue;
    public float high;
    public float low;
    public int boughtCount;
    public int soldCount;

    protected StockObject(Parcel in) {
        id = in.readString();
        name = in.readString();
        currentValue = in.readFloat();
        openValue = in.readFloat();
        high = in.readFloat();
        low = in.readFloat();
        boughtCount = in.readInt();
        soldCount = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeFloat(currentValue);
        dest.writeFloat(openValue);
        dest.writeFloat(high);
        dest.writeFloat(low);
        dest.writeInt(boughtCount);
        dest.writeInt(soldCount);
    }
}
