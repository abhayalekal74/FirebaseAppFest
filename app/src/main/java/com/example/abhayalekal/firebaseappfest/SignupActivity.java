package com.example.abhayalekal.firebaseappfest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.firebase.SignInPresenter;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPassword;
    Button signInBtn;
    SignInPresenter signInPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setViews();
    }

    private void setViews() {
        userEmail = (EditText) findViewById(R.id.user_email);
        userPassword = (EditText) findViewById(R.id.user_password);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userEmail.getText()!=null && userEmail.getText().length()>0 && userPassword.getText()!=null && userPassword.getText().length()>0) {
                    signInUser(userEmail.getText().toString(), userPassword.getText().toString());
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Please enter valid email id and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInUser(String email, String password)
    {
        if(signInPresenter==null)
        {
            signInPresenter = new SignInPresenter(SignupActivity.this, signInInterface);
        }
        signInPresenter.signInUser(email, password);
    }

    SignInPresenter.SignInInterface signInInterface = new SignInPresenter.SignInInterface() {
        @Override
        public void onSignUpSuccess(FirebaseUser user) {
            Intent i = new Intent(SignupActivity.this, )
        }

        @Override
        public void onSignUpFailure(String error) {

        }
    };

}
