package com.alodia.bitbash.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
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
    @BindView(R.id.textView_NewBash) TextView mTextView_NewBash;
    @BindView(R.id.textView_LoadBash) TextView mTextView_LoadBash;
    @BindView(R.id.textView_Options) TextView mTextView_Options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        setFonts();
        setClickListeners();

        mButton_Logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mButton_Logout){
            logout();
        }else if(view == mTextView_NewBash){
            startActivity(new Intent(MenuActivity.this, CreateBashActivity.class));
        }else if(view == mTextView_LoadBash){

        }else if(view == mTextView_Options){

        }
    }

    private void setFonts(){
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");

        mTextView_NewBash.setTypeface(tf);
        mTextView_LoadBash.setTypeface(tf);
        mTextView_Options.setTypeface(tf);
    }

    private void setClickListeners(){
        mTextView_NewBash.setOnClickListener(this);
        mTextView_LoadBash.setOnClickListener(this);
        mTextView_Options.setOnClickListener(this);
    }
}
