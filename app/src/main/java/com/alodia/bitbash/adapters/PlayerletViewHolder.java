package com.alodia.bitbash.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Bash;
import com.alodia.bitbash.models.Player;
import com.alodia.bitbash.models.Playerlet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/19/2017.
 */

public class PlayerletViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_PlayerName)
    TextView mTextView_PlayerName;

    public PlayerletViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindPlayerlet(Playerlet playerlet, Typeface Play, boolean displayAddButton){
        mTextView_PlayerName.setText(playerlet.getName());
        mTextView_PlayerName.setTypeface(Play);
    }
}
