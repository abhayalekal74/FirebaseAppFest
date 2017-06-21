package com.example.abhayalekal.firebaseappfest.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class User implements Parcelable{
    public String email;
    public String uid;
    public String notificationToken;
    public ArrayList<StockObject> stocksBought;
    public ArrayList<User> usersFollowing;

    public User(String email, String token) {
        this.email = email;
        this.notificationToken = token;
    }

    protected User(Parcel in) {
        email = in.readString();
        uid = in.readString();
        notificationToken = in.readString();
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

    public User() {

    }

    @Override
    public boolean equals(Object obj) {
        return uid.equals(((User)obj).uid);
    }

    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(uid);
        dest.writeString(notificationToken);
        dest.writeTypedList(stocksBought);
        dest.writeTypedList(usersFollowing);
    }
}
