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

    }

	}
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
