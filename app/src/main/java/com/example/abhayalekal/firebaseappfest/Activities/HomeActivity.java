package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView homeRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FirebasePresenter(this).fetchAllStocks(new FirebasePresenter.StocksFetchListener() {
            @Override
            public void success(ArrayList<StockObject> stocks) {

            }

            @Override
            public void failure() {

            }
        });
        setContentView(R.layout.activity_home);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        setViews();
        setAdapter();

    }

    private void setAdapter() {

    }

    private void setViews() {
        homeRecycler = (RecyclerView) findViewById(R.id.homeRecyclerView);
    }

}
