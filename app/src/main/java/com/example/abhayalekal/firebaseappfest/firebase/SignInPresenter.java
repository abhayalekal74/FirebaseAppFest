package com.example.abhayalekal.firebaseappfest.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gunjit on 21/06/17.
 */

public class SignInPresenter {

    Context context;
    private FirebaseAuth mAuth;
    SignInInterface signInInterface;
    public SignInPresenter(Context context, SignInInterface signInInterface)
    {
        this.context = context;
        this.signInInterface = signInInterface;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInUser(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            signInInterface.onSignUpSuccess(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Firebase", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            signInInterface.onSignUpFailure(""+task.getException().getMessage());

                        }
                    }
                });
    }

    public interface SignInInterface
    {
        void onSignUpSuccess(FirebaseUser user);
        void onSignUpFailure(String error);
    }
}
