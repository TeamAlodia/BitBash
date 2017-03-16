package com.alodia.bitbash.ui.activities;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.UniversalPagerAdapter;
import com.alodia.bitbash.models.HighScoreTable;
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.fragments.AddDetailsAndInviteFragment;
import com.alodia.bitbash.ui.fragments.AddCriteriaFragment;
import com.alodia.bitbash.ui.fragments.AddGamesFragment;
import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bash);
        ButterKnife.bind(this);

        setUpPagerAdaper();
        setUpTabs();
    }

    public void setUpPagerAdaper(){
        ArrayList<String> tabTitles= new ArrayList<>(Arrays.asList("Setup Bash", "Add Games", "Set Rules"));
        int pageCount = 3;
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(mAddDetailsAndInviteFragment, new AddGamesFragment(), new AddCriteriaFragment()));

        UniversalPagerAdapter adapter = new UniversalPagerAdapter(getSupportFragmentManager(), pageCount, tabTitles, fragments);
        mPager.setAdapter(adapter);
    }

    public void setUpTabs(){
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        mTabs.setTypeface(tf, Typeface.NORMAL);
        mTabs.setViewPager(mPager);
    }

    public void addGame(String gameId){
        HighScoreTable highScoreTable = new HighScoreTable();
        highScoreTable.setGameId(gameId);
        mHighScoreTables.add(highScoreTable);
        Log.d("Added:", gameId);
        Log.d("Number of tables", String.valueOf(mHighScoreTables.size()));
    }

    public void setName(String name){
        mName = name;
        Log.d("Name:", name);
    }

    public void setDescription(String description){
        mDescription = description;
    }

    public void getNameAndDescription(){
        mName = mAddDetailsAndInviteFragment.getName();
        mDescription = mAddDetailsAndInviteFragment.getDescription();
    }
}