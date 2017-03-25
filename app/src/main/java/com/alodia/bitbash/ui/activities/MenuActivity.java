package com.alodia.bitbash.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AuthListenerActivity implements View.OnClickListener{
    @BindView(R.id.textView_NewBash) TextView mTextView_NewBash;
    @BindView(R.id.textView_LoadBash) TextView mTextView_LoadBash;
    @BindView(R.id.textView_CustomizeFighter) TextView mTextView_CustomizeFighter;
    @BindView(R.id.textView_LoadProfile) TextView mTextView_LoadProfile;
    @BindView(R.id.textView_Rivals) TextView mTextView_Rivals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        setFonts();
        setClickListeners();
    }

    @Override
    public void onClick(View view) {
        if(view == mTextView_LoadProfile){
            logout();
        } else if(view == mTextView_NewBash) {
            startActivity(new Intent(MenuActivity.this, CreateBashActivity.class));
        } else if(view == mTextView_LoadBash) {
            startActivity(new Intent(MenuActivity.this, LoadBashActivity.class));
        } else if(view == mTextView_CustomizeFighter) {
            startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
        }else if(view == mTextView_Rivals){
            startActivity(new Intent(MenuActivity.this, RivalsActivity.class));
        }
    }

    private void setFonts(){
        Typeface PressStart = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");

        mTextView_NewBash.setTypeface(PressStart);
        mTextView_LoadBash.setTypeface(PressStart);
        mTextView_CustomizeFighter.setTypeface(PressStart);
        mTextView_LoadProfile.setTypeface(PressStart);
        mTextView_Rivals.setTypeface(PressStart);
    }

    private void setClickListeners(){
        mTextView_LoadProfile.setOnClickListener(this);
        mTextView_NewBash.setOnClickListener(this);
        mTextView_LoadBash.setOnClickListener(this);
        mTextView_CustomizeFighter.setOnClickListener(this);
        mTextView_Rivals.setOnClickListener(this);
    }
}
