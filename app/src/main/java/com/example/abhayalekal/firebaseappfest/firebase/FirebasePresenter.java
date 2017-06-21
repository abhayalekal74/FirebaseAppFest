package com.example.abhayalekal.firebaseappfest.firebase;

import android.content.Context;
import android.util.Log;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.example.abhayalekal.firebaseappfest.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhayalekal on 21/06/17.
 */
public class FirebasePresenter {
    private static ArrayList<StockObject> stocks;
    private static ArrayList<User> users;
    private static Gson gson;
    private static String userId;
    private final Context context;

    public FirebasePresenter(Context context) {
        this.context = context;
        keepSynced();
        gson = new Gson();
     //   FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    private void keepSynced() {
        getUsersRef().keepSynced(true);
        getUsersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = gson.fromJson(gson.toJson(userSnapshot.getValue()), User.class);
                    if (users == null) {
                        users = new ArrayList<User>();
                    }
                    if (!users.contains(user)) {
                        users.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void fetchAllStocks(final StocksFetchListener stocksFetchListener) {
        if (stocks != null) {
            stocksFetchListener.success(stocks);
        } else if (Util.isConnected(context)) {
            getSnapshot(getStocksRef(), new SnapshotListener() {
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        Log.d("allStocks", gson.toJson(dataSnapshot.getValue()));
                        ArrayList<StockObject> stockObjects = null;
                        for (DataSnapshot stocksSnapshot : dataSnapshot.getChildren()) {
                            StockObject stockObject = gson.fromJson(gson.toJson(stocksSnapshot.getValue()), StockObject.class);
                            if (stockObjects == null) {
                                stockObjects = new ArrayList<StockObject>();
                            }
                            stockObjects.add(stockObject);
                        }
                        if (stockObjects != null && stockObjects.size() > 0) {
                            stocksFetchListener.success(stockObjects);
                            return;
                        }
                    }
                    stocksFetchListener.failure();
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
                    if (dataSnapshot != null) {
                        Log.d("allUsers", gson.toJson(dataSnapshot.getValue()));
                        ArrayList<User> users = null;
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            User user = gson.fromJson(gson.toJson(userSnapshot.getValue()), User.class);
                            if (users == null) {
                                users = new ArrayList<User>();
                            }
                            users.add(user);
                        }
                        if (users != null && users.size() > 0) {
                            peopleFetchListener.success(users);
                            return;
                        }
                    }
                    peopleFetchListener.failure();
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
                    if (dataSnapshot != null) {
                        JsonElement jsonElement = gson.toJsonTree(dataSnapshot.getValue());
                        Log.d("fetchFollowing", jsonElement.toString());
                        ArrayList<User> followingList = null;
                        for (DataSnapshot following : dataSnapshot.getChildren()) {
                            User user = new User();
                            user.uid = following.getKey();
                            int index = users.indexOf(user);
                            User user1 = users.get(index);
                            if (followingList == null) {
                                followingList = new ArrayList<User>();
                            }
                            followingList.add(user1);
                        }
                        if (followingList != null && followingList.size() > 0) {
                            peopleFetchListener.success(followingList);
                            return;
                        }
                    }
                    peopleFetchListener.failure();
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
                    JsonElement jsonElement = gson.toJsonTree(dataSnapshot.getValue());
                    Log.d("fetchWatchList", jsonElement.toString());
                    ArrayList<StockObject> watchList = null;
                    for (DataSnapshot watching : dataSnapshot.getChildren()) {
                        StockObject stockObject = new StockObject();
                        stockObject.id = watching.getKey();
                        int index = stocks.indexOf(stockObject);
                        StockObject stock = stocks.get(index);
                        if (watchList == null) {
                            watchList = new ArrayList<>();
                        }
                        watchList.add(stock);
                    }
                    if (watchList != null && watchList.size() > 0) {
                        stocksFetchListener.success(watchList);
                        return;
                    }
                    stocksFetchListener.failure();
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
                    JsonElement jsonElement = gson.toJsonTree(dataSnapshot.getValue());
                    Log.d("fetchStocksBought", jsonElement.toString());
                    ArrayList<StockObject> boughtStocks = null;
                    for (DataSnapshot bought : dataSnapshot.getChildren()) {
                        StockObject stockObject = new StockObject();
                        stockObject.id = bought.getKey();
                        int index = stocks.indexOf(stockObject);
                        StockObject stock = stocks.get(index);
                        if (boughtStocks == null) {
                            boughtStocks = new ArrayList<>();
                        }
                        boughtStocks.add(stock);
                    }
                    if (boughtStocks != null && boughtStocks.size() > 0) {
                        stocksFetchListener.success(boughtStocks);
                        return;
                    }
                    stocksFetchListener.failure();
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
                    JsonElement jsonElement = gson.toJsonTree(dataSnapshot.getValue());
                    Log.d("fetchStocksSold", jsonElement.toString());
                    ArrayList<StockObject> soldStocks = null;
                    for (DataSnapshot sold : dataSnapshot.getChildren()) {
                        StockObject stockObject = new StockObject();
                        stockObject.id = sold.getKey();
                        int index = stocks.indexOf(stockObject);
                        StockObject stock = stocks.get(index);
                        if (soldStocks == null) {
                            soldStocks = new ArrayList<>();
                        }
                        soldStocks.add(stock);
                    }
                    if (soldStocks != null && soldStocks.size() > 0) {
                        stocksFetchListener.success(soldStocks);
                        return;
                    }
                    stocksFetchListener.failure();
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

    public void follow(final User user, final UserListener userListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getFollowRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    Map<String, Object> values = new HashMap<>();
                    values.put(user.email, true);
                    getFollowRef().updateChildren(values, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                userListener.success(user);
                            } else {
                                userListener.failure();
                            }
                        }
                    });
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

    public void buyStock(final StockObject stockObject, final StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getBuyTransactionsRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    Map<String, Object> values = new HashMap<>();
                    values.put(stockObject.id, stockObject.currentValue);
                    getBuyTransactionsRef().updateChildren(values, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                stockListener.success(stockObject);
                            } else {
                                stockListener.failure();
                            }
                        }
                    });

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

    public void sellStock(final StockObject stockObject, final StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getSellTransactionsRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    Map<String, Object> values = new HashMap<>();
                    values.put(stockObject.id, stockObject.currentValue);
                    getSellTransactionsRef().updateChildren(values, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                stockListener.success(stockObject);
                            } else {
                                stockListener.failure();
                            }
                        }
                    });
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

    public void watch(final StockObject stockObject, final StockListener stockListener) {
        if (Util.isConnected(context)) {
            getSnapshot(getWatchlistRef(), new SnapshotListener(){
                @Override
                public void success(DataSnapshot dataSnapshot) {
                    Map<String, Object> values = new HashMap<>();
                    values.put(stockObject.id, true);
                    getWatchlistRef().updateChildren(values, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                stockListener.success(stockObject);
                            } else {
                                stockListener.failure();
                            }
                        }
                    });
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
        return FirebaseDatabase.getInstance().getReference().child("follow").child(userId);
    }

    private DatabaseReference getBuyTransactionsRef() {
        return FirebaseDatabase.getInstance().getReference().child("transactions").child(userId).child("bought");
    }

    private DatabaseReference getSellTransactionsRef() {
        return FirebaseDatabase.getInstance().getReference().child("transactions").child(userId).child("sold");
    }

    private DatabaseReference getWatchlistRef() {
        return FirebaseDatabase.getInstance().getReference().child("watchlist").child(userId);
    }

    private void getSnapshot(DatabaseReference ref, final SnapshotListener snapshotListener) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshotListener.success(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                snapshotListener.failure();
            }
        });
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
