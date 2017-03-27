package com.alodia.bitbash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.BashViewHolder;
import com.alodia.bitbash.models.Bash;
import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadBashActivity extends AuthListenerActivity implements View.OnClickListener {
    @BindView(R.id.recyclerView_Bashes) RecyclerView mRecyclerView_Bashes;
    @BindView(R.id.textView_HeaderInvites) TextView mTextView_HeaderInvites;
    @BindView(R.id.recyclerView_Invites) RecyclerView mRecyclerView_Invites;

    FirebaseIndexRecyclerAdapter mFirebaseIndexAdapter;
    DatabaseReference dbRef;
    String mCurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bash);
        ButterKnife.bind(this);

        mCurrentUserId = mAuth.getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference();

        //TODO: Convert to watcher in order to hide fields appropriately?
        dbRef.child(Constants.Db_INVITES).child(mCurrentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    mTextView_HeaderInvites.setVisibility(View.VISIBLE);
                    mRecyclerView_Invites.setVisibility(View.VISIBLE);
                    setUpInvitesFirebaseIndexAdapter();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        setUpBashesFirebaseIndexAdapter();


    }

    public void setUpInvitesFirebaseIndexAdapter(){
        DatabaseReference keyRef = dbRef.child(Constants.Db_INVITES).child(mCurrentUserId);
        DatabaseReference dataRef = dbRef.child(Constants.DB_BASHES);

        mRecyclerView_Invites.setHasFixedSize(false);
        mRecyclerView_Invites.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseIndexAdapter = new FirebaseIndexRecyclerAdapter<Bash, BashViewHolder>(Bash.class, R.layout.list_item_bashes, BashViewHolder.class, keyRef, dataRef) {
            @Override
            protected void populateViewHolder(BashViewHolder viewHolder, final Bash model, int position) {
                viewHolder.bindBash(model, mCurrentUserId);
                viewHolder.activateInviteMode();

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bashDetailIntent = new Intent(mContext, BashDetailActivity.class);
                        bashDetailIntent.putExtra("bashId", model.getPushId());
                        bashDetailIntent.putExtra("bashName", model.getName());
                        startActivity(bashDetailIntent);
                    }
                });
            }
        };

        mRecyclerView_Invites.setAdapter(mFirebaseIndexAdapter);
    }

    //TODO: Convert to Bashlets
    public void setUpBashesFirebaseIndexAdapter(){

        DatabaseReference keyRef = dbRef.child(Constants.DB_PLAYERS).child(mCurrentUserId).child(Constants.DB_BASHES);
        DatabaseReference dataRef = dbRef.child(Constants.DB_BASHES);

        mRecyclerView_Bashes.setHasFixedSize(false);
        mRecyclerView_Bashes.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseIndexAdapter = new FirebaseIndexRecyclerAdapter<Bash, BashViewHolder>(Bash.class, R.layout.list_item_bashes, BashViewHolder.class, keyRef, dataRef) {
            @Override
            protected void populateViewHolder(BashViewHolder viewHolder, final Bash model, int position) {
                viewHolder.bindBash(model, mCurrentUserId);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bashDetailIntent = new Intent(mContext, BashDetailActivity.class);
                        bashDetailIntent.putExtra("bashId", model.getPushId());
                        bashDetailIntent.putExtra("bashName", model.getName());
                        startActivity(bashDetailIntent);
                    }
                });
            }
        };

        mRecyclerView_Bashes.setAdapter(mFirebaseIndexAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
