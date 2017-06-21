package com.example.abhayalekal.firebaseappfest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Adapters.PeopleAdapter;
import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

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

        updateCurrentUserObject();

        setAdapter(firebasePresenter);
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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    currentUser = new User(user.getEmail(), FirebaseInstanceId.getInstance().getToken());
                    currentUser.usersFollowing.clear();
                    currentUser.usersFollowing.addAll(users);

                    getAllUsers();

                } else {
                    Toast.makeText(PeopleActivity.this, "You are not logged in. Please logout and login again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure() {
                Toast.makeText(PeopleActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(FirebasePresenter firebasePresenter) {
        peopleAdapter = new PeopleAdapter(PeopleActivity.this, users, firebasePresenter);
    }

    private void setRecyclerView() {
        llm = new LinearLayoutManager(PeopleActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        peopleRecyclerView.setLayoutManager(llm);
        peopleRecyclerView.setHasFixedSize(false);
        peopleRecyclerView.setAdapter(peopleAdapter);
    }
}
