package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abhayalekal.firebaseappfest.Adapters.PeopleAdapter;
import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();
    public User currentUser;
    PeopleAdapter peopleAdapter;
    LinearLayoutManager llm;
    RecyclerView peopleRecyclerView;

    FirebasePresenter firebasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        firebasePresenter = new FirebasePresenter(PeopleActivity.this);

        getAllUsers();

        setAdapter();
        setRecyclerView();
    }

    private void getAllUsers() {
        firebasePresenter.fetchAllUsers(new FirebasePresenter.PeopleFetchListener() {
            @Override
            public void success(ArrayList<User> users) {
                PeopleActivity.this.users.clear();
                PeopleActivity.this.users.addAll(users);
                peopleAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure() {

            }
        });
    }


    private void updateCurrentUserObject() {
        firebasePresenter.fetchFollowing(new FirebasePresenter.PeopleFetchListener() {
            @Override
            public void success(ArrayList<User> users) {

            }

            @Override
            public void failure() {

            }
        });
    }

    private void setAdapter() {
        peopleAdapter = new PeopleAdapter(PeopleActivity.this, users);
    }

    private void setRecyclerView() {
        llm = new LinearLayoutManager(PeopleActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        peopleRecyclerView.setLayoutManager(llm);
        peopleRecyclerView.setHasFixedSize(false);
        peopleRecyclerView.setAdapter(peopleAdapter);
    }
}
