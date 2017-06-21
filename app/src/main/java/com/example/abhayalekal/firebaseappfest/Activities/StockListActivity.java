package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Adapters.StockListAdapter;
import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by faheem on 21/06/17.
 */
public class StockListActivity extends AppCompatActivity{
    private RecyclerView stockRecyclerView;
    private StockListAdapter stockListAdapter;
    LinearLayoutManager llm;
    private ArrayList<StockObject> stockList = new ArrayList<>();

    FirebasePresenter firebasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_list);
        firebasePresenter = new FirebasePresenter(StockListActivity.this);
        getIntent();
        setViews();
        setAdapter();

        getStockList();
    }

    private void getStockList() {

        firebasePresenter.fetchAllStocks(new FirebasePresenter.StocksFetchListener() {
            @Override
            public void success(ArrayList<StockObject> stocks) {

                Log.e("-----", ""+new Gson().toJson(stocks));

                StockListActivity.this.stockList.clear();
                StockListActivity.this.stockList.addAll(stocks);
                stockListAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure() {
                Toast.makeText(StockListActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        stockListAdapter = new StockListAdapter(StockListActivity.this, stockList, firebasePresenter);
    }

    private void setViews() {
        stockRecyclerView = (RecyclerView) findViewById(R.id.stockContainer);
        llm = new LinearLayoutManager(StockListActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        stockRecyclerView.setLayoutManager(llm);
        stockRecyclerView.setHasFixedSize(false);
        stockRecyclerView.setAdapter(stockListAdapter);
    }

}
