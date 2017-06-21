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

    public User(String email, String token, String uid) {
        this.email = email;
        this.notificationToken = token;
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;

    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.uid);
        dest.writeString(this.notificationToken);
        dest.writeTypedList(this.stocksBought);
        dest.writeTypedList(this.usersFollowing);
    }

    protected User(Parcel in) {
        this.email = in.readString();
        this.uid = in.readString();
        this.notificationToken = in.readString();
        this.stocksBought = in.createTypedArrayList(StockObject.CREATOR);
        this.usersFollowing = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

}
