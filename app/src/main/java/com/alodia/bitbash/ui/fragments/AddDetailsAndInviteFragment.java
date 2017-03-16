package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alodia.bitbash.R;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDetailsAndInviteFragment extends Fragment {
    @BindView(R.id.editText_Name) EditText mEditText_Name;
    @BindView(R.id.editText_Description) EditText mEditText_Description;

    public CreateBashActivity parent;

    public AddDetailsAndInviteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_details_and_invite, container, false);
        ButterKnife.bind(this, view);

        parent = (CreateBashActivity) getActivity();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public String getName(){
        return mEditText_Name.getText().toString();
    }

    public String getDescription(){
        return mEditText_Description.getText().toString();
    }

    //TODO: Hide keyboard when navigating away
}
