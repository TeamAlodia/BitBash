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
public class AddGameDetailsFragment extends Fragment {
    public CreateBashActivity parent;

    public AddGameDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parent = (CreateBashActivity) getActivity();

        return inflater.inflate(R.layout.fragment_add_game_details, container, false);
    }

}
