package com.example.abhayalekal.firebaseappfest.firebase;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.Objects.User;

import java.util.ArrayList;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class FirebasePresenter {
    private final Context context;

    FirebasePresenter(Context context) {
        this.context = context;
    }

    public void fetchAllStocks() {

    }



    public interface StocksFetchListener {
        void success(ArrayList<StockObject> stocks);

        void failure();
    }

    public interface PeopleFetchListener {
        void success(ArrayList<User> users);

        void failure();
    }


}
