package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Binders.CardHeaderBinder;
import com.example.abhayalekal.firebaseappfest.Binders.NothingToShowDataBinder;
import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;
import com.example.abhayalekal.firebaseappfest.dataBinder.MyStocksDataBinder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by faheem on 21/06/17.
 */
public class HomeAdapter extends DataBindAdapter {
    private static final Integer NOTHING_TO_SHOW = 1;
    private static final Integer MY_STOCKS = 2;
    private static final Integer TRENDING_LIST = 3;
    private static final Integer WATCH_LIST = 4;
    private static final Integer HEADER = 5;
    public HashMap<Integer, DataBinder> dataBinderHashMap = new HashMap<>();
    NothingToShowDataBinder nothingToShowDataBinder;
    MyStocksDataBinder watchListDataBinder;
    MyStocksDataBinder myStocksDataBinder;
    MyStocksDataBinder trendingDataBinder;
    CardHeaderBinder cardHeaderbinder;
    ArrayList<StockObject> watchList;
    ArrayList<StockObject> stockList;
    ArrayList<StockObject> trendingList;
    String listType = "";

    public HomeAdapter(Context context, ArrayList<StockObject> trendingList, ArrayList<StockObject> purchasedList, ArrayList<StockObject> watchList, String listType) {
        this.watchList = watchList;
        this.trendingList = trendingList;
        this.stockList = purchasedList;
        this.listType = listType;

        nothingToShowDataBinder = new NothingToShowDataBinder(this, listType);
        watchListDataBinder = new MyStocksDataBinder(this, context, watchList, listType);
        myStocksDataBinder = new MyStocksDataBinder(this, context, purchasedList, listType);
        trendingDataBinder = new MyStocksDataBinder(this, context, trendingList, listType);
        cardHeaderbinder = new CardHeaderBinder(this, listType, context);

        dataBinderHashMap.put(NOTHING_TO_SHOW, nothingToShowDataBinder);
        dataBinderHashMap.put(MY_STOCKS, myStocksDataBinder);
        dataBinderHashMap.put(TRENDING_LIST, trendingDataBinder);
        dataBinderHashMap.put(WATCH_LIST, watchListDataBinder);
        dataBinderHashMap.put(HEADER, cardHeaderbinder);


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            listType = "Watch List";
            return HEADER;
        }
        if (position == 1) {
            if (watchList == null || watchList.size() == 0) {
                listType = "Watch";
                return NOTHING_TO_SHOW;

            } else {
                return WATCH_LIST;
            }
        }
        if (position == 2) {
            listType = "My Stocks";
            return HEADER;
        } else if (position == 3) {
            if (stockList == null || stockList.size() == 0) {
                listType = "MyStock";
                return NOTHING_TO_SHOW;
            } else {
                return MY_STOCKS;
            }
        }
        if (position == 4) {
            listType = "Trending List";
            return HEADER;
        } else if (position == 5) {
            if (trendingList == null || trendingList.size() == 0) {
                listType = "Trending";
                return NOTHING_TO_SHOW;
            } else {
                return TRENDING_LIST;
            }
        }
        return 0;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return null;
    }
}
