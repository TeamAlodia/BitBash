package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.GameletListAdapter;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddGamesFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.spinner) Spinner mSpinner;
    @BindView(R.id.recyclerView_Games) RecyclerView mRecyclerView_Gamelets;
    @BindView(R.id.searchView_ByName) SearchView mSearchView_ByName;

    public ArrayAdapter<String> mSpinnerAdapter;
    public CreateBashActivity parent;
    public ArrayList<String> mPlatforms = new ArrayList<>();
    public ArrayList<String> mPlatformIds = new ArrayList<>();
    public ArrayList<Gamelet> mGamelets = new ArrayList<>();
    private GameletListAdapter mGameletAdapter;
    private HashMap<String, ArrayList<Gamelet>> mGameletListStorage = new HashMap<>();
    private ArrayList<Gamelet> mFilteredGamelets = new ArrayList<>();

    public AddGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_games, container, false);
        ButterKnife.bind(this, view);

        parent = (CreateBashActivity) getActivity();

        setUpSpinner();
        getPlatforms();
        setUpRecyclerView();
        setRecyclerViewItemTouchListener();

        mSearchView_ByName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.length() != 0){
                    filterGamelets(s.toLowerCase());
                }
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void filterGamelets(String query){
        mFilteredGamelets.clear();
        for(Gamelet gamelet : mGamelets){
            if(gamelet.getName().toLowerCase().contains(query)){
                mFilteredGamelets.add(gamelet);
            }
        }
        mGameletAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        mSpinner.setSelection(1);
    }

    public void setUpSpinner(){
        mSpinnerAdapter = new ArrayAdapter<String>(parent, android.R.layout.simple_spinner_item, mPlatforms);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String platformId = mPlatformIds.get(i);

                mSearchView_ByName.setQuery("", false);
                mGamelets.clear();
                mFilteredGamelets.clear();

                if(mGameletListStorage.get(platformId) == null){
                    Log.d("Fetching", "From GamesDB");
                    findGamesByPlatform(platformId);
                }else{
                    Log.d("Fetching", "From Local Storage, id " + platformId);
                    for(Gamelet gamelet : mGameletListStorage.get(platformId)){
                        mGamelets.add(gamelet);
                        mFilteredGamelets.add(gamelet);
                    }

                    mGameletAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setRecyclerViewItemTouchListener(){
        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                if(swipeDir == 8){
                    mGameletAdapter.notifyDataSetChanged();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView_Gamelets);
    }

    public void setUpRecyclerView(){
        mGameletAdapter = new GameletListAdapter(mFilteredGamelets, parent);
        mRecyclerView_Gamelets.setHasFixedSize(true);
        mRecyclerView_Gamelets.setAdapter(mGameletAdapter);
        mRecyclerView_Gamelets.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    public void getPlatforms(){
        GamesDbService apiService = new GamesDbService();
        apiService.findAllPlatforms(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(parent, "Unable to fetch platforms from GamesDB", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(response.body().string());
//                    Log.d("Object:", jsonObj.toString());
                    JSONArray rawPlatforms = jsonObj.getJSONObject("Data").getJSONObject("Platforms").getJSONArray("Platform");
                    for(int i = 0; i < rawPlatforms.length(); i++){
                        String name = rawPlatforms.getJSONObject(i).getString("name");
                        String id = rawPlatforms.getJSONObject(i).getString("id");

                        mPlatforms.add(name);
                        mPlatformIds.add(id);
                    }
                    parent.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSpinnerAdapter.notifyDataSetChanged();
                            mSpinner.setSelection(mPlatformIds.indexOf("7"));
                        }
                    });
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public void findGamesByPlatform(final String platformId){
        final GamesDbService apiService = new GamesDbService();

        apiService.findGamesByPlatform(platformId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(parent, "Unable to fetch games from GamesDB", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(response.body().string());
                    Log.d("GamesByPlatformObject:", jsonObj.toString());
                    JSONArray rawGames = jsonObj.getJSONObject("Data").getJSONArray("Game");
                    for(int i = 0; i < rawGames.length(); i++){
                        JSONObject data = rawGames.getJSONObject(i);
                        String name = data.getString("GameTitle");
                        String id = data.getString("id");
                        if(name != null && id != null){
                            Gamelet gamelet = new Gamelet(name, id);
                            mGamelets.add(gamelet);
                        }
                    }
                    parent.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Collections.sort(mGamelets, new Comparator<Gamelet>() {
                                @Override
                                public int compare(Gamelet gamelet, Gamelet t1) {
                                    return gamelet.getName().compareTo(t1.getName());
                                }
                            });
                            mGameletListStorage.put(platformId, (ArrayList<Gamelet>) mGamelets.clone());

                            for(Gamelet gamelet : mGamelets){
                                mFilteredGamelets.add(gamelet);
                            }
                            mGameletAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
