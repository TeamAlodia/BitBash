package com.alodia.bitbash.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alodia.bitbash.R;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDetailsAndInviteFragment extends Fragment {
    public CreateBashActivity parent;

    public AddDetailsAndInviteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parent = (CreateBashActivity) getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_details_and_invite, container, false);
    }

}
