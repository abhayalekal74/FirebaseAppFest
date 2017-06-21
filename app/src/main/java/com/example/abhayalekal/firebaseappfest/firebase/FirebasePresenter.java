package com.example.abhayalekal.firebaseappfest.firebase;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.example.abhayalekal.firebaseappfest.util.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class FirebasePresenter {
    private static ArrayList<StockObject> stocks;
    private static ArrayList<User> users;
    private final Context context;

    public FirebasePresenter(Context context) {
        this.context = context;
    }


    public void fetchAllStocks(final StocksFetchListener stocksFetchListener) {
        if (stocks != null) {
            stocksFetchListener.success(stocks);
        } else if (Util.isConnected(context)) {
            getSnapshot(getStocksRef(), new SnapshotListener() {
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO implement
                }

                @Override
                public void failure() {
                    stocksFetchListener.failure();
                }
            });
        } else {
            stocksFetchListener.failure();
        }
    }

    public void fetchAllUsers(final PeopleFetchListener peopleFetchListener) {
        if (users != null) {
            peopleFetchListener.success(users);
        } else if (Util.isConnected(context)) {
            getSnapshot(getUsersRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO failure
                }

                @Override
                public void failure() {
                    peopleFetchListener.failure();
                }
            });
        } else {
            peopleFetchListener.failure();
        }
    }

    public void fetchFollowing(final PeopleFetchListener peopleFetchListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getFollowRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO
                }

                @Override
                public void failure() {
                    peopleFetchListener.failure();
                }
            });
        } else {
            peopleFetchListener.failure();
        }
    }

    public void fetchWatchlist(final StocksFetchListener stocksFetchListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getWatchlistRef(), new SnapshotListener() {
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO implement
                }

                @Override
                public void failure() {
                    stocksFetchListener.failure();
                }
            });
        } else {
            stocksFetchListener.failure();
        }
    }

    public void fetchStocksBought(final StocksFetchListener stocksFetchListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getBuyTransactionsRef(), new SnapshotListener() {
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO implement
                }

                @Override
                public void failure() {
                    stocksFetchListener.failure();
                }
            });
        } else {
            stocksFetchListener.failure();
        }
    }

    public void fetchStocksSold(final StocksFetchListener stocksFetchListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getSellTransactionsRef(), new SnapshotListener() {
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    // TODO implement
                }

                @Override
                public void failure() {
                    stocksFetchListener.failure();
                }
            });
        } else {
            stocksFetchListener.failure();
        }
    }

    public void follow(User user, final UserListener userListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getFollowRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {

                }

                @Override
                public void failure() {
                    userListener.failure();
                }
            });
        } else {
            userListener.failure();
        }
    }

    public void buyStock(StockObject stockObject, StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getBuyTransactionsRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {

                }

                @Override
                public void failure() {

                }
            });
        } else {
            stockListener.failure();
        }
    }

    public void sellStock(StockObject stockObject, final StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getSellTransactionsRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {

                }

                @Override
                public void failure() {
                    stockListener.failure();
                }
            });
        } else {
            stockListener.failure();
        }
    }

    public void watch(StockObject stockObject, final StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getWatchlistRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {

                }

                @Override
                public void failure() {
                    stockListener.failure();
                }
            });
        } else {
            stockListener.failure();
        }
    }

    private DatabaseReference getStocksRef() {
        return FirebaseDatabase.getInstance().getReference().child("stocks");
    }

    private DatabaseReference getUsersRef() {
        return FirebaseDatabase.getInstance().getReference().child("users");
    }

    private DatabaseReference getFollowRef() {
        return FirebaseDatabase.getInstance().getReference().child("follow");
    }

    private DatabaseReference getBuyTransactionsRef() {
        return FirebaseDatabase.getInstance().getReference().child("transactions").child("bought");
    }

    private DatabaseReference getSellTransactionsRef() {
        return FirebaseDatabase.getInstance().getReference().child("transactions").child("bought");
    }

    private DatabaseReference getWatchlistRef() {
        return FirebaseDatabase.getInstance().getReference().child("watchlist");
    }

    private void getSnapshot(DatabaseReference ref, SnapshotListener snapshotListener) {

    }

    public interface StocksFetchListener {
        void success(ArrayList<StockObject> stocks);

        void failure();
    }

    public interface PeopleFetchListener {
        void success(ArrayList<User> users);

        void failure();
    }

    public interface StockListener {
        void success(StockObject stockObject);

        void failure();
    }

    public interface UserListener {
        void success(User following);

        void failure();
    }

    private interface SnapshotListener {
        void success(DataSnapshot dataSnapshot);

        void failure();
    }
}
