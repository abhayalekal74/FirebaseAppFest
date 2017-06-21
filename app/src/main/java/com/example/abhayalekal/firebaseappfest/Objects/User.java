package com.example.abhayalekal.firebaseappfest.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class User implements Parcelable{
    public String email;
    public String notificationToken;
    public ArrayList<StockObject> stocksBought;
    public ArrayList<User> usersFollowing;

    public User(Parcel in) {
        email = in.readString();
        notificationToken = in.readString();
        stocksBought = in.createTypedArrayList(StockObject.CREATOR);
        usersFollowing = in.createTypedArrayList(User.CREATOR);
    }

    public User(String email, String token) {
        this.email = email;
        this.notificationToken = token;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(notificationToken);
        dest.writeTypedList(stocksBought);
        dest.writeTypedList(usersFollowing);
    }

    @Override
    public int describeContents() {
        return 0;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
