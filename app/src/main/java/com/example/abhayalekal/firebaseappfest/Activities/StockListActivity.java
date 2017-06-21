package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.abhayalekal.firebaseappfest.Adapters.StockListAdapter;
import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;

import java.util.ArrayList;

/**
 * Created by faheem on 21/06/17.
 */
public class StockListActivity extends AppCompatActivity{
    private RecyclerView stockContainer;
    private StockListAdapter stockListAdapter;
    private ArrayList<StockObject> stockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list);

        setViews();
        setAdapter();

    }

    private void setAdapter() {


    }

    private void setViews() {
        stockContainer = (RecyclerView) findViewById(R.id.stockContainer);
    }

}
