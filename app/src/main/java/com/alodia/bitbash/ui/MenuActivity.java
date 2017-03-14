package com.alodia.bitbash.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AuthListenerActivity implements View.OnClickListener{
    @BindView(R.id.button__Logout) Button mButton_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mButton_Logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mButton_Logout){
            logout();
        }
    }
}
