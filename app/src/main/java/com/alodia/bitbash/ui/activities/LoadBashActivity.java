package com.alodia.bitbash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.BashViewHolder;
import com.alodia.bitbash.models.Bash;
import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadBashActivity extends AuthListenerActivity implements View.OnClickListener {
    @BindView(R.id.recyclerView_Bashes) RecyclerView mRecyclerView_Bashes;

    FirebaseIndexRecyclerAdapter mFirebaseIndexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bash);
        ButterKnife.bind(this);


        setUpFirebaseIndexAdapter();
    }

    public void setUpFirebaseIndexAdapter(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        String currentUserId = mAuth.getCurrentUser().getUid();

        DatabaseReference keyRef = dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_BASHES);
        DatabaseReference dataRef = dbRef.child(Constants.DB_BASHES);

        mRecyclerView_Bashes.setHasFixedSize(true);
        mRecyclerView_Bashes.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseIndexAdapter = new FirebaseIndexRecyclerAdapter<Bash, BashViewHolder>(Bash.class, R.layout.list_item_bashes, BashViewHolder.class, keyRef, dataRef) {
            @Override
            protected void populateViewHolder(BashViewHolder viewHolder, final Bash model, int position) {
                Log.d("!!!!!", "In populate");
                viewHolder.bindBash(model);
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
