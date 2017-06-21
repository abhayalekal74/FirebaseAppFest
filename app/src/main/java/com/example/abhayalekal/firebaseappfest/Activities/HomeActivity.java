package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Adapters.HomeAdapter;
import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView homeRecycler;
    private ArrayList<StockObject> trendingList = new ArrayList<>();
    private ArrayList<StockObject> watchedList= new ArrayList<>();
    private ArrayList<StockObject> myStocksList= new ArrayList<>();
    private HomeAdapter homeAdapter;
    String listType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setViews();
        setAdapter();
        fetchData();

    }

    private void fetchData() {
        FirebasePresenter fireBasePresenter = new FirebasePresenter(HomeActivity.this);
        fireBasePresenter.fetchWatchlist(new FirebasePresenter.StocksFetchListener() {
            @Override
            public void success(ArrayList<StockObject> stocks) {
                listType = "Watch List";
                watchedList.clear();
                watchedList.addAll(stocks);
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure() {

                Toast.makeText(HomeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


        fireBasePresenter.fetchStocksBought(new FirebasePresenter.StocksFetchListener() {
            @Override
            public void success(ArrayList<StockObject> stocks) {
                listType = "My Stocks";
                myStocksList.clear();
                myStocksList.addAll(stocks);
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure() {

                Toast.makeText(HomeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        fireBasePresenter.fetchAllStocks(new FirebasePresenter.StocksFetchListener() {
            @Override
            public void success(ArrayList<StockObject> stocks) {
                trendingList.clear();
                for(int i =0; i<3; i++)
                {
                    trendingList.add(stocks.get(i));

                }
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure() {

                Toast.makeText(HomeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void setAdapter() {
        homeAdapter = new HomeAdapter(HomeActivity.this, trendingList, myStocksList, watchedList, listType);

    }

    private void setViews() {
        homeRecycler = (RecyclerView) findViewById(R.id.homeRecyclerView);
    }

}
