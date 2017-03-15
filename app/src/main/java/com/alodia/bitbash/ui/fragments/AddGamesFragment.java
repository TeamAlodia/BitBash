package com.alodia.bitbash.ui.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alodia.bitbash.R;
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayAdapter<String> mAdapter;
    public CreateBashActivity parent;
    public ArrayList<String> mPlatforms = new ArrayList<>();

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


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        mSpinner.setSelection(1);
    }

    public void setUpSpinner(){
        mAdapter = new ArrayAdapter<String>(parent, android.R.layout.simple_spinner_item, mPlatforms);
        mSpinner.setAdapter(mAdapter);
    }

    public void getPlatforms(){
        GamesDbService apiService = new GamesDbService();
        apiService.findAllPlatforms(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(parent, "GamesDB is curently down", Toast.LENGTH_SHORT).show();
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
                    parent.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
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
