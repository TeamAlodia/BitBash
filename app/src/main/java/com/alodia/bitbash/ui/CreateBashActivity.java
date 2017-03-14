package com.alodia.bitbash.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;

public class CreateBashActivity extends AuthListenerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bash);
    }
}
