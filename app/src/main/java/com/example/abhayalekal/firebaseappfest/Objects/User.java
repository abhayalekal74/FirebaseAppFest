package com.example.abhayalekal.firebaseappfest.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class User implements Parcelable{
    public String email;
    public ArrayList<StockObject> stocksBought;
    public ArrayList<User> usersFollowing;

    protected User(Parcel in) {
        email = in.readString();
        stocksBought = in.createTypedArrayList(StockObject.CREATOR);
        usersFollowing = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeTypedList(stocksBought);
        dest.writeTypedList(usersFollowing);
    }
}
