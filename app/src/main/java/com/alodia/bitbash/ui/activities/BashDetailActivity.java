package com.alodia.bitbash.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.models.HighScoreTable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BashDetailActivity extends AuthListenerActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.textView_BashName) TextView mText_BashName;
    @BindView(R.id.spinner_Season) Spinner mSpinner_Season;
    @BindView(R.id.spinner_Game) Spinner mSpinner_Game;
    @BindView(R.id.textView_GameName) TextView mText_GameName;
    @BindView(R.id.textView_BashType) TextView mText_BashType;
    @BindView(R.id.recyclerView_Highscores) RecyclerView mRecycler_Highscores;

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference bashRef;
    List<HighScoreTable> games = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bash_detail);
        ButterKnife.bind(this);

        populateFields();

    }

    private void populateFields() {
        Intent importIntent = getIntent();
        String bashId = importIntent.getStringExtra("bashId");
        String bashName = importIntent.getStringExtra("bashName");

        bashRef = dbRef.child(Constants.DB_BASHES).child(bashId);

        bashRef.child(Constants.DB_SUBNODE_SEASONS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> seasons = new ArrayList<String>();

                int x = 1;
                for(DataSnapshot season: dataSnapshot.getChildren()) {
                    seasons.add("Season " + x);
                    ++x;
                }

                ArrayAdapter<String> seasonAdapter = new ArrayAdapter<String>(BashDetailActivity.this, android.R.layout.simple_spinner_item, seasons);
                seasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_Season.setAdapter(seasonAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mText_BashName.setText(bashName);
        mSpinner_Season.setOnItemSelectedListener(this);
        mSpinner_Game.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(parent.getId() == R.id.spinner_Season) {
            bashRef.child(Constants.DB_SUBNODE_SEASONS).child(Integer.toString(pos)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for(DataSnapshot game: dataSnapshot.getChildren()) {
                        games.add(game.getValue(HighScoreTable.class));
                    }

                    ArrayAdapter<HighScoreTable> gameAdapter = new ArrayAdapter<HighScoreTable>(BashDetailActivity.this, android.R.layout.simple_spinner_item, games);
                    gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_Game.setAdapter(gameAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(parent.getId() == R.id.spinner_Game) {
            mText_GameName.setText(games.get(pos).getGameName());
            mText_BashType.setText(games.get(pos).getType());
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
