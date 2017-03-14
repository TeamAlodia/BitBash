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
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.fragments.AddDetailsAndInviteFragment;
import com.alodia.bitbash.ui.fragments.AddGameDetailsFragment;
import com.alodia.bitbash.ui.fragments.AddCriteriaFragment;
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

    public ArrayList<String> mPlatforms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bash);
        ButterKnife.bind(this);

        setUpPagerAdaper();
        setUpTabs();

        getPlatforms();
    }

    public void setUpPagerAdaper(){
        ArrayList<String> tabTitles= new ArrayList<>(Arrays.asList("Setup Bash", "Add Games", "Set Rules"));
        int pageCount = 3;
        ArrayList<Fragment> fragments = new ArrayList<>(Arrays.asList(new AddDetailsAndInviteFragment(), new AddCriteriaFragment(), new AddGameDetailsFragment()));

        UniversalPagerAdapter adapter = new UniversalPagerAdapter(getSupportFragmentManager(), pageCount, tabTitles, fragments);
        mPager.setAdapter(adapter);
    }

    public void setUpTabs(){
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        mTabs.setTypeface(tf, Typeface.NORMAL);
        mTabs.setViewPager(mPager);
    }

    public void getPlatforms(){
        GamesDbService apiService = new GamesDbService();
        apiService.findAllPlatforms(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "GamesDB is curently down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(response.body().string());
                    Log.d("Object:", jsonObj.toString());
                    JSONArray rawPlatforms = jsonObj.getJSONObject("Data").getJSONObject("Platforms").getJSONArray("Platform");
                    for(int i = 0; i < rawPlatforms.length(); i++){
                        String name = rawPlatforms.getJSONObject(i).getString("name");
                        mPlatforms.add(name);
                    }
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}