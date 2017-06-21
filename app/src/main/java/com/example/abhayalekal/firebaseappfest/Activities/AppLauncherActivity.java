package com.example.abhayalekal.firebaseappfest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.abhayalekal.firebaseappfest.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by faheem on 21/06/17.
 */
public class AppLauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_launcher);
        AppLauncherActivity.this.setSupportActionBar(null);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                    AppLauncherActivity.this.startActivity(new Intent(AppLauncherActivity.this, HomeActivity.class));
                } else {
                    AppLauncherActivity.this.startActivity(new Intent(AppLauncherActivity.this, SignupActivity.class));

                }
                finish();
            }
        }, 700);
    }

}
