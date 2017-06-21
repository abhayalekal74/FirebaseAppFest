package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Binders.CardHeaderBinder;
import com.example.abhayalekal.firebaseappfest.Binders.NothingToShowDataBinder;
import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;
import com.example.abhayalekal.firebaseappfest.dataBinder.MyStockDataBinder2;

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
    private static final int NOTHING_TO_SHOW_IN_WATCH = 23;
    private static final int NOTHING_TO_SHOW_IN_STOCKS = 24;
    private static final int NOTHING_TO_SHOW_IN_TRENDING = 25;
    private static final Integer HEADER_MY_STOCKS = 87 ;
    private static final Integer HEADER_TRENDING = 88;
    private static final Integer HEADER_WATCHED = 89;
    public HashMap<Integer, DataBinder> dataBinderHashMap = new HashMap<>();
    NothingToShowDataBinder nothingToShowDataBinder;
    MyStockDataBinder2 watchListDataBinder;
    MyStockDataBinder2 myStocksDataBinder;
    MyStockDataBinder2 trendingDataBinder;
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

        NothingToShowDataBinder nothingToShowDataBinderinWatchedList = new NothingToShowDataBinder(this, context,  "Watched List");
        NothingToShowDataBinder nothingToShowDataBinderinMyStocks = new NothingToShowDataBinder(this, context, "My Stock");
        NothingToShowDataBinder nothingToShowDataBinderinTrendingList = new NothingToShowDataBinder(this, context, "Trending List");
        watchListDataBinder = new MyStockDataBinder2(this, context, stockList, "Watch List");
        myStocksDataBinder = new MyStockDataBinder2(this, context, purchasedList, "My Stocks");
        trendingDataBinder = new MyStockDataBinder2(this, context, trendingList, "Trending List");
        CardHeaderBinder cardHeaderbinderWatchedList = new CardHeaderBinder(this, "Watched List", context);
        CardHeaderBinder cardHeaderbinderTrending = new CardHeaderBinder(this, "Trending List", context);
        CardHeaderBinder cardHeaderbinderMyStocks = new CardHeaderBinder(this, "My Stocks", context);

        dataBinderHashMap.put(NOTHING_TO_SHOW, nothingToShowDataBinder);
        dataBinderHashMap.put(NOTHING_TO_SHOW_IN_WATCH, nothingToShowDataBinderinWatchedList);
        dataBinderHashMap.put(NOTHING_TO_SHOW_IN_STOCKS, nothingToShowDataBinderinMyStocks);
        dataBinderHashMap.put(NOTHING_TO_SHOW_IN_TRENDING, nothingToShowDataBinderinTrendingList);
        dataBinderHashMap.put(NOTHING_TO_SHOW, nothingToShowDataBinder);
        dataBinderHashMap.put(MY_STOCKS, myStocksDataBinder);
        dataBinderHashMap.put(TRENDING_LIST, trendingDataBinder);
        dataBinderHashMap.put(WATCH_LIST, watchListDataBinder);
        dataBinderHashMap.put(HEADER_MY_STOCKS, cardHeaderbinderMyStocks);
        dataBinderHashMap.put(HEADER_TRENDING, cardHeaderbinderTrending);
        dataBinderHashMap.put(HEADER_WATCHED, cardHeaderbinderWatchedList);


    }

    @Override
    public int getItemCount() {
        return 3 + watchList.size()+ stockList.size() + trendingList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_WATCHED;
        }
        if (position == 1) {
            if (watchList == null || watchList.size() == 0) {
                return NOTHING_TO_SHOW_IN_WATCH;

            } else {
                return WATCH_LIST;
            }
        }
        if (position == 2) {

            return HEADER_MY_STOCKS;
        } else if (position == 3) {
            if (stockList == null || stockList.size() == 0) {
                return NOTHING_TO_SHOW_IN_STOCKS;
            } else {
                return MY_STOCKS;
            }
        }
        if (position == 4) {
            return HEADER_TRENDING;
        } else if (position == 5) {
            if (trendingList == null || trendingList.size() == 0) {
                return NOTHING_TO_SHOW_IN_TRENDING;
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
