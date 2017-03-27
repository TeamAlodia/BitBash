package com.alodia.bitbash.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Playerlet;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/19/2017.
 */

public class PlayerletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.textView_PlayerName) TextView mTextView_PlayerName;
    @BindView(R.id.imageButton_AddRival) ImageButton mImageButton_AddRival;

    private CreateBashActivity mParent;
    private Playerlet mPlayerlet;

    public PlayerletViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindPlayerlet(Playerlet playerlet, Typeface Play){
        mPlayerlet = playerlet;

        mTextView_PlayerName.setText(playerlet.getName());
        mTextView_PlayerName.setTypeface(Play);
    }

    public void bindPlayerlet(Playerlet playerlet, Typeface Play, boolean displayAddButton, CreateBashActivity parent){
        mPlayerlet = playerlet;

        mTextView_PlayerName.setText(playerlet.getName());
        mTextView_PlayerName.setTypeface(Play);

        mParent = parent;

        if(displayAddButton){
            mImageButton_AddRival.setVisibility(View.VISIBLE);
        }

        mImageButton_AddRival.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mImageButton_AddRival){
            mParent.addRival(mPlayerlet.getPushId());
        }
    }
}
