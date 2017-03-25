package com.alodia.bitbash.ui.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.adapters.PlayerletViewHolder;
import com.alodia.bitbash.models.Playerlet;
import com.alodia.bitbash.ui.activities.CreateBashActivity;
import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDetailsAndInviteFragment extends Fragment {
    @BindView(R.id.editText_Name) EditText mEditText_Name;
    @BindView(R.id.editText_Description) EditText mEditText_Description;
    @BindView(R.id.textView_ChooseYourRivals) TextView mEditText_ChooseYourRivals;
    @BindView(R.id.recyclerView_AddRivals) RecyclerView mRecyclerView_AddRivals;

    public CreateBashActivity parent;
    private Typeface PressStart;
    private FirebaseIndexRecyclerAdapter mAddRivalsFirebaseIndexAdapter;

    public AddDetailsAndInviteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_details_and_invite, container, false);
        ButterKnife.bind(this, view);

        parent = (CreateBashActivity) getActivity();

        //TODO: May need to use a text watcher for constant updates if other phones do not play nice with fragments.
        PressStart = Typeface.DEFAULT.createFromAsset(parent.getAssets(), "fonts/PressStart2P-Regular.ttf");
        mEditText_ChooseYourRivals.setTypeface(PressStart);

        setUpRivalsRecycler();

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

    public void setUpRivalsRecycler(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        String currentUserId = parent.mAuth.getCurrentUser().getUid();

        DatabaseReference keyRef = dbRef.child(Constants.DB_PLAYERS).child(currentUserId).child(Constants.DB_RIVALS);
        final DatabaseReference dataRef = dbRef.child(Constants.DB_PLAYERS);


        mRecyclerView_AddRivals.setHasFixedSize(false);
        mRecyclerView_AddRivals.setLayoutManager(new LinearLayoutManager(parent));

        mAddRivalsFirebaseIndexAdapter = new FirebaseIndexRecyclerAdapter<Playerlet, PlayerletViewHolder>(Playerlet.class, R.layout.list_item_playerlet, PlayerletViewHolder.class,
                keyRef, dataRef) {
            @Override
            protected void populateViewHolder(PlayerletViewHolder viewHolder, Playerlet model, int position) {
                viewHolder.bindPlayerlet(model, PressStart, true);
            }
        };

        mRecyclerView_AddRivals.setAdapter(mAddRivalsFirebaseIndexAdapter);
    }

    public String getName(){
        return mEditText_Name.getText().toString();
    }

    public String getDescription(){
        return mEditText_Description.getText().toString();
    }

    //TODO: Hide keyboard when navigating away
}
