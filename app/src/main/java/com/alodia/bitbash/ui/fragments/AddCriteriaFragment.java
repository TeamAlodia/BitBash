package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.CriteriaListAdapter;
import com.alodia.bitbash.adapters.GameletListAdapter;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.models.HighScoreTable;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCriteriaFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.FAB_Done) FloatingActionButton mFAB_Done;
    @BindView(R.id.recyclerView_Criteria) RecyclerView mRecyclerView_Criteria;

    public CreateBashActivity parent;
    private ArrayList<HighScoreTable> mHighScoreTables = new ArrayList<>();
    private CriteriaListAdapter mCriteriaListAdapter;

    public AddCriteriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_criteria, container, false);
        ButterKnife.bind(this, view);
        parent = (CreateBashActivity) getActivity();

        setUpRecyclerView();
        mFAB_Done.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == mFAB_Done){
            parent.getNameAndDescription();
        }
    }

    public void setUpRecyclerView(){
        mCriteriaListAdapter = new CriteriaListAdapter(mHighScoreTables, parent);
        mRecyclerView_Criteria.setHasFixedSize(true);
        mRecyclerView_Criteria.setAdapter(mCriteriaListAdapter);
        mRecyclerView_Criteria.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    //Sets the pointers for the fragment version of the high score table as the same for the activity version, negating the need to transfer data.
    public void pairHighScoreTables(ArrayList<HighScoreTable> highScoreTables){
        mHighScoreTables = highScoreTables;
    }

    public void updateCriteriaAdapter(){
        mCriteriaListAdapter.notifyDataSetChanged();
    }
}
