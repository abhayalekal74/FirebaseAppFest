package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;
import com.example.abhayalekal.firebaseappfest.dataBinder.MyStocksDataBinder;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by faheem on 21/06/17.
 */
public class StockListAdapter extends DataBindAdapter {
    private static final Integer STOCK_LIST = 19 ;
    private final Context context;
    ArrayList<StockObject> stockList;
    MyStocksDataBinder mysStocksDataBinder;
    public HashMap<Integer, DataBinder> dataBinderHashMap = new HashMap<>();
    public StockListAdapter(Context context, ArrayList<StockObject> stockList, FirebasePresenter firebasePresenter)
    {
        this.context = context;
        this.stockList = stockList;
        mysStocksDataBinder = new MyStocksDataBinder(this, context, stockList, firebasePresenter);
        dataBinderHashMap.put(STOCK_LIST, mysStocksDataBinder);



    }
    @Override
    public int getItemCount() {

        return stockList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return STOCK_LIST;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return (T) dataBinderHashMap.get(viewType);
    }
}
