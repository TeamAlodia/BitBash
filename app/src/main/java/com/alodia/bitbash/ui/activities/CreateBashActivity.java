package com.alodia.bitbash.ui.activities;

import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.UniversalPagerAdapter;
import com.alodia.bitbash.ui.fragments.AddDetailsAndInviteFragment;
import com.alodia.bitbash.ui.fragments.AddGameDetailsFragment;
import com.alodia.bitbash.ui.fragments.AddGamesFragment;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateBashActivity extends AuthListenerActivity {
    @BindView(R.id.pager) ViewPager mPager;
    @BindView(R.id.tabs) PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bash);
        ButterKnife.bind(this);

        ArrayList<String> tabTitles= new ArrayList<>(Arrays.asList("Setup Bash", "Add Games", "Set Rules"));
        int pageCount = 3;
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(new AddDetailsAndInviteFragment(), new AddGamesFragment(), new AddGameDetailsFragment()));



        UniversalPagerAdapter adapter = new UniversalPagerAdapter(getSupportFragmentManager(), pageCount, tabTitles, fragments);
        mPager.setAdapter(adapter);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        mTabs.setTypeface(tf, Typeface.NORMAL);
        mTabs.setViewPager(mPager);
    }
}
