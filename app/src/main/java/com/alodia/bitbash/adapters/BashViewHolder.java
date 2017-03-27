package com.alodia.bitbash.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Bash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/17/2017.
 */

public class BashViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.textView_BashName) TextView mTextView_BashName;
    @BindView(R.id.imageView_AcceptInvite) ImageView mImageView_AcceptInvite;
    @BindView(R.id.imageView_RejectInvite) ImageView mImageView_RejectInvite;
    @BindView(R.id.textView_BashCreator) TextView mTextView_BashCreator;

    DatabaseReference dbRef;
    String mCurrentUserId;
    Bash mBash;

    public BashViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public void bindBash(Bash bash, String currentUserId){
        mTextView_BashName.setText(bash.getName());
        mTextView_BashCreator.setText(bash.getCreatedBy());
        mImageView_AcceptInvite.setOnClickListener(this);
        mImageView_RejectInvite.setOnClickListener(this);

        mBash = bash;
        mCurrentUserId = currentUserId;
    }

    public void activateInviteMode(){
        mImageView_AcceptInvite.setVisibility(View.VISIBLE);
        mImageView_RejectInvite.setVisibility(View.VISIBLE);

        //TODO: Store name of creator and populate here
        mTextView_BashCreator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        //TODO: Check to see if there are any remaining nodes/adapter positions, hide appropriate fields
        if(view == mImageView_AcceptInvite){
            acceptInvite();
        }else if(view == mImageView_RejectInvite){
            rejectInvite();
        }
    }

    private void acceptInvite(){
        dbRef.child(Constants.Db_INVITES).child(mCurrentUserId).child(mBash.getPushId()).removeValue();
        dbRef.child(Constants.DB_BASHES).child(mBash.getPushId()).child(Constants.DB_PLAYERS).child(mCurrentUserId).setValue(true);
    }

    private void rejectInvite(){
        dbRef.child(Constants.Db_INVITES).child(mCurrentUserId).child(mBash.getPushId()).removeValue();
        dbRef.child(Constants.DB_BASHES).child(mBash.getPushId()).child(Constants.DB_PLAYERS).child(mCurrentUserId).removeValue();
    }
}
