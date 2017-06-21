package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;
import com.example.abhayalekal.firebaseappfest.dataBinder.MyStocksDataBinder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by faheem on 21/06/17.
 */
public class StockListAdapter extends DataBindAdapter {
    private static final Integer STOCK_LIST = 19 ;
    private final String listType;
    private final Context context;
    ArrayList<StockObject> stockList;
    MyStocksDataBinder mysStocksDataBinder;
    public HashMap<Integer, DataBinder> dataBinderHashMap = new HashMap<>();
    public StockListAdapter(Context context, ArrayList<StockObject> stockList, String listType)
    {
        this.context = context;
        this.listType = listType;
        this.stockList = stockList;
        mysStocksDataBinder = new MyStocksDataBinder(this, context, stockList, listType);
        dataBinderHashMap.put(STOCK_LIST, mysStocksDataBinder);



    }
    @Override
    public int getItemCount() {

        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
        {
            return STOCK_LIST;
        }
        return 0;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return null;
    }
}
