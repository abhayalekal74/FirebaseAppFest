package com.example.abhayalekal.firebaseappfest.util;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by abhayalekal on 22/09/16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        try {

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            if (refreshedToken != null && refreshedToken.length() > 0) {

//                new PushNotificationPresenter(this).registerUserForNotifications(refreshedToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
