package com.alodia.bitbash.ui.activities;

import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.UniversalPagerAdapter;
import com.alodia.bitbash.models.Bash;
import com.alodia.bitbash.models.HighScoreTable;
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.fragments.AddDetailsAndInviteFragment;
import com.alodia.bitbash.ui.fragments.AddCriteriaFragment;
import com.alodia.bitbash.ui.fragments.AddGamesFragment;
import com.astuetz.PagerSlidingTabStrip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateBashActivity extends AuthListenerActivity {
    @BindView(R.id.pager) ViewPager mPager;
    @BindView(R.id.tabs) PagerSlidingTabStrip mTabs;

    private ArrayList<HighScoreTable> mHighScoreTables = new ArrayList<>();
    private String mName;
    private String mDescription;
    private AddDetailsAndInviteFragment mAddDetailsAndInviteFragment = new AddDetailsAndInviteFragment();
    private AddCriteriaFragment mAddCriteriaFragment = new AddCriteriaFragment();
    private HashMap<String, Boolean> mPlayers = new HashMap<String, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bash);
        ButterKnife.bind(this);

        mAddCriteriaFragment.pairHighScoreTables(mHighScoreTables);

        setUpPagerAdaper();
        setUpTabs();
    }

    public void setUpPagerAdaper(){
        ArrayList<String> tabTitles= new ArrayList<>(Arrays.asList("Setup Bash", "Add Games", "Set Rules"));
        int pageCount = 3;
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(mAddDetailsAndInviteFragment, new AddGamesFragment(), mAddCriteriaFragment));

        UniversalPagerAdapter adapter = new UniversalPagerAdapter(getSupportFragmentManager(), pageCount, tabTitles, fragments);
        mPager.setAdapter(adapter);
    }

    public void setUpTabs(){
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        mTabs.setTypeface(tf, Typeface.NORMAL);
        mTabs.setViewPager(mPager);
    }

    public void addGame(String gameId, String gameName){
        HighScoreTable highScoreTable = new HighScoreTable();
        highScoreTable.setGameId(gameId);
        highScoreTable.setGameName(gameName);
        mHighScoreTables.add(highScoreTable);
        mAddCriteriaFragment.updateCriteriaAdapter();
    }

    public void getNameAndDescriptionFromFragment(){
        mName = mAddDetailsAndInviteFragment.getName();
        mDescription = mAddDetailsAndInviteFragment.getDescription();
    }

    public String getName(){
        return mName;
    }

    public String getDescription(){
        return mDescription;
    }

    public void createBash(){
        //TODO: Catch the exception if it fails so that e don't trigger false toasts.
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        String currentUserId = mAuth.getCurrentUser().getUid();

        //Set up initial season with high score tables
        ArrayList<ArrayList<HighScoreTable>> seasons = new ArrayList<>();
        seasons.add(mHighScoreTables);

        //Set up players
//        mPlayers.put(currentUserId, true);

        Bash bash = new Bash(mName, mDescription, currentUserId, mPlayers, seasons);

        DatabaseReference pushRef = dbRef.child(Constants.DB_BASHES).push();
        String bashPushId = pushRef.getKey();
        bash.setPushId(bashPushId);
        pushRef.setValue(bash);
        dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_BASHES).child(bashPushId).setValue(true);

        sendInvites(bashPushId);

        //TODO: redirect to bash details page
        Toast.makeText(mContext, "Bash created!", Toast.LENGTH_SHORT).show();
    }

    public void addRival(String rivalId){
        mPlayers.put(rivalId, false);
        Log.d("Added Rival", rivalId);
        Toast.makeText(mContext, "Rival added", Toast.LENGTH_SHORT).show();
    }
    public void sendInvites(String bashId){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        String currentUserId = mAuth.getCurrentUser().getUid();

        for(String rivalId : mPlayers.keySet()){
            if(!rivalId.equals(currentUserId)){
                dbRef.child(Constants.Db_INVITES).child(currentUserId).child(bashId).setValue(false);
            }
        }
    }
}