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
        Log.d("Searching for", rivalEmail);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Query dbQuery = dbRef.child(Constants.DB_SEARCH).orderByValue().equalTo(rivalEmail);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Toast.makeText(mContext, "Rival found", Toast.LENGTH_SHORT).show();
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
                searchForRival(s.toLowerCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}
