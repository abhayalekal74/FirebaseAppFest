package com.example.abhayalekal.firebaseappfest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.BuildConfig;
import com.example.abhayalekal.firebaseappfest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by faheem on 21/06/17.
 */
public class AppLauncherActivity extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_launcher);
        AppLauncherActivity.this.setSupportActionBar(null);

        try {
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                    .setDeveloperModeEnabled(BuildConfig.DEBUG)
                    .build();
            mFirebaseRemoteConfig.setConfigSettings(configSettings);
            int cacheExpiration = 0;
            mFirebaseRemoteConfig.fetch(cacheExpiration)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // After config data is successfully fetched, it must be activated before newly fetched
                                // values are returned.
                                mFirebaseRemoteConfig.activateFetched();
                            } else {

                            }
                            displayWelcomeMessage();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                    AppLauncherActivity.this.startActivity(new Intent(AppLauncherActivity.this, StockListActivity.class));
                } else {
                    AppLauncherActivity.this.startActivity(new Intent(AppLauncherActivity.this, SignupActivity.class));

                }
                finish();
            }
        }, 700);
    }

    private void displayWelcomeMessage() {
        try {
            String welcomeMessage = mFirebaseRemoteConfig.getString("stockup_name");
            if(welcomeMessage!=null && !welcomeMessage.matches("")) {
                Toast.makeText(AppLauncherActivity.this, "Welcome to " + welcomeMessage,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
