package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.CriteriaListAdapter;
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
            parent.getNameAndDescriptionFromFragment();
            if(validateBash()){
                parent.createBash();
            }
        }
    }

    public boolean validateBash(){
        boolean isValid = true;

        String name = parent.getName();
        String description = parent.getDescription();

        if(name == null || name.length() == 0){
            isValid = false;
            Toast.makeText(parent, "Please enter a name for your bash", Toast.LENGTH_SHORT).show();
        }else if(description == null || description.length() == 0){
            isValid = false;
            Toast.makeText(parent, "Please enter a description for your bash", Toast.LENGTH_SHORT).show();
        }else if(mHighScoreTables.size() == 0){
            isValid = false;
            Toast.makeText(parent, "Please select one or more games", Toast.LENGTH_SHORT).show();
        }

        return isValid;
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
