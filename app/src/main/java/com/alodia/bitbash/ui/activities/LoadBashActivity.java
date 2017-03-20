package com.alodia.bitbash.ui.activities;

import android.support.v7.app.AppCompatActivity;
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
import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadBashActivity extends AuthListenerActivity {
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

        //Not working for some reason. Double checked to make sure the keyRef was pointing at the right place. Never triggers populateViewHolder

        mFirebaseIndexAdapter = new FirebaseIndexRecyclerAdapter<Bash, BashViewHolder>(Bash.class, R.layout.list_item_bashes, BashViewHolder.class, keyRef, dataRef) {
            @Override
            protected void populateViewHolder(BashViewHolder viewHolder, Bash model, int position) {
                Log.d("!!!!!", "In populate");
                viewHolder.bindBash(model);
            }
        };

        mRecyclerView_Bashes.setAdapter(mFirebaseIndexAdapter);
    }
}
