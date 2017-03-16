package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alodia.bitbash.R;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCriteriaFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.FAB_Done) FloatingActionButton mFAB_Done;

    public CreateBashActivity parent;

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

        mFAB_Done.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == mFAB_Done){
            parent.getNameAndDescription();
        }
    }
}
