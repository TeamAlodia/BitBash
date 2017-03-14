package com.alodia.bitbash.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AuthListenerActivity implements View.OnClickListener{
    @BindView(R.id.button__Logout) Button mButton_Logout;
    @BindView(R.id.newBashText) TextView mButton_NewBash;
    @BindView(R.id.loadBashText) TextView mButton_LoadBash;
    @BindView(R.id.optionsText) TextView mButton_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mButton_Logout.setOnClickListener(this);
        mButton_NewBash.setOnClickListener(this);
        mButton_LoadBash.setOnClickListener(this);
        mButton_Settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mButton_Logout){
            logout();
        } else if(view == mButton_NewBash) {
            newBash();
        } else if(view == mButton_LoadBash) {
            loadBash();
        } else if(view == mButton_Settings) {
            settings();
        }
    }

    private void newBash() {

    }

    private void loadBash() {

    }

    private void settings() {

    }
}
