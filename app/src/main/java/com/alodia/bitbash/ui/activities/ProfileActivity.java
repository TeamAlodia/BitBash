package com.alodia.bitbash.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AuthListenerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
    }
}
