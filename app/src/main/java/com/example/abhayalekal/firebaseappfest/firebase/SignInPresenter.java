package com.example.abhayalekal.firebaseappfest.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

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

    public void signInUser(final String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            User userObj = new User(email, FirebaseInstanceId.getInstance().getToken(), FirebaseAuth.getInstance().getCurrentUser().getUid());


                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
                            String userId = mDatabase.push().getKey();
                            mDatabase.child(userId).setValue(userObj, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if(databaseError!=null)
                                    {
                                        Log.e("Firebase", ""+databaseError.getMessage());
                                    }
                                    else
                                    {
                                        Log.e("Firebase", "Success");
                                    }
                                }
                            });

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
