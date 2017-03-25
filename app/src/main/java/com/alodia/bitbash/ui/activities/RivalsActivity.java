package com.alodia.bitbash.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RivalsActivity extends AuthListenerActivity {
    @BindView(R.id.searchView_FindRival) SearchView mSearchView_FindRival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rivals);
        ButterKnife.bind(this);

        setQueryListener();
    }

    public void searchForRival(String rivalEmail){
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Query currentPlayerQuery = dbRef.child(Constants.DB_SEARCH).child(Constants.DB_PLAYERS).orderByValue().equalTo(rivalEmail);
        final String currentUserId = mAuth.getCurrentUser().getUid();

        currentPlayerQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    //Rival is found in search
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        final String rivalId = snapshot.getKey();

                        //Check if already a rival
                        dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_RIVALS).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(rivalId)){
                                    Toast.makeText(RivalsActivity.this, "Already a rival!", Toast.LENGTH_SHORT).show();
                                }else{
                                    dbRef.child(Constants.DB_PLAYERS).child(rivalId).child(Constants.DB_RIVALS).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            //If rival has already added current player, then set both rival values to true to indicate, else set rival to false for current user. True value indicates mutual rivalry, allows interactions.
                                            if(dataSnapshot.hasChild(currentUserId)){
                                                dbRef.child(Constants.DB_PLAYERS).child(rivalId).child(Constants.DB_RIVALS).child(currentUserId).setValue(true);
                                                dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_RIVALS).child(rivalId).setValue(true);
                                            }else{
                                                dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_RIVALS).child(rivalId).setValue(false);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    Toast.makeText(mContext, "Rival found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }else{
                    Toast.makeText(mContext, "Rival not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setQueryListener(){
        mSearchView_FindRival.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String currentUserEmail = mAuth.getCurrentUser().getEmail().toLowerCase();

                if(currentUserEmail.equals(s.toLowerCase())){
                    Toast.makeText(mContext, "No shadowboxing allowed", Toast.LENGTH_SHORT).show();
                }else{
                    searchForRival(s.toLowerCase());   
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}
