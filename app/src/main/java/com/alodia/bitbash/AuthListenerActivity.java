package com.alodia.bitbash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.alodia.bitbash.ui.LoginActivity;
import com.alodia.bitbash.ui.MenuActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Alaina Traxler on 3/13/2017.
 */

public class AuthListenerActivity extends AppCompatActivity{
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mContext = this;

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    if(mContext.getClass().getSimpleName().equals("LoginActivity")){
                        startActivity(new Intent(mContext, MenuActivity.class));
                    }
                } else {
                    if(!mContext.getClass().getSimpleName().equals("LoginActivity")){
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                    // User is signed out
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void logout(){
        mAuth.signOut();
    }
}
