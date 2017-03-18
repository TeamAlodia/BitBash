package com.alodia.bitbash.ui.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alodia.bitbash.AuthListenerActivity;
import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AuthListenerActivity {
    @BindView(R.id.textView_Name) TextView mTextView_Name;
    @BindView(R.id.textView_NameDetail) TextView mTextView_NameDetail;
    @BindView(R.id.textView_Origin) TextView mTextView_Origin;
    @BindView(R.id.textView_OriginDetail) TextView mTextView_OriginDetail;
    @BindView(R.id.textView_Initials) TextView mTextView_Initials;
    @BindView(R.id.textView_InitialsDetail) TextView mTextView_InitialsDetail;
    @BindView(R.id.textView_BloodType) TextView mTextView_BloodType;
    @BindView(R.id.textView_BloodTypeDetail) TextView mTextView_BloodTypeDetail;
    @BindView(R.id.textView_DateOfBirth) TextView mTextView_DateOfBirth;
    @BindView(R.id.textView_DateOfBirthDetail) TextView mTextView_DateOfBirthDetail;
    @BindView(R.id.textView_Height) TextView mTextView_Height;
    @BindView(R.id.textView_HeightDetail) TextView mTextView_HeightDetail;
    @BindView(R.id.textView_Weight) TextView mTextView_Weight;
    @BindView(R.id.textView_WeightDetail) TextView mTextView_WeightDetail;
    @BindView(R.id.textView_Bio) TextView mTextView_Bio;
    @BindView(R.id.textView_BioDetail) TextView mTextView_BioDetail;

    Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setTypefaces();
        getPlayer();
    }

    public void setTypefaces(){
        Typeface PressStart = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        Typeface PlayReg = Typeface.createFromAsset(getAssets(), "fonts/Play-Regular.ttf");

        mTextView_Name.setTypeface(PressStart);
        mTextView_Origin.setTypeface(PressStart);
        mTextView_Initials.setTypeface(PressStart);
        mTextView_BloodType.setTypeface(PressStart);
        mTextView_Height.setTypeface(PressStart);
        mTextView_DateOfBirth.setTypeface(PressStart);
        mTextView_Weight.setTypeface(PressStart);
        mTextView_Bio.setTypeface(PressStart);

        mTextView_NameDetail.setTypeface(PlayReg);
        mTextView_OriginDetail.setTypeface(PlayReg);
        mTextView_InitialsDetail.setTypeface(PlayReg);
        mTextView_BloodTypeDetail.setTypeface(PlayReg);
        mTextView_HeightDetail.setTypeface(PlayReg);
        mTextView_DateOfBirthDetail.setTypeface(PlayReg);
        mTextView_WeightDetail.setTypeface(PlayReg);
        mTextView_BioDetail.setTypeface(PlayReg);

    }

    public void getPlayer(){
        String currentUserId = mAuth.getCurrentUser().getUid();

        //TODO: Check for intent, otherwise display via currentUserId

        FirebaseDatabase.getInstance().getReference().child(Constants.DB_PLAYERS).child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPlayer = dataSnapshot.getValue(Player.class);
                setFields();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setFields(){
        mTextView_NameDetail.setText(mPlayer.getName());
        mTextView_OriginDetail.setText(mPlayer.getOrigin());
        mTextView_InitialsDetail.setText(mPlayer.getInitials());
        mTextView_BloodTypeDetail.setText(mPlayer.getBloodType());
        mTextView_DateOfBirthDetail.setText(mPlayer.getDateOfBirth());
        mTextView_HeightDetail.setText(mPlayer.getHeight());
        mTextView_WeightDetail.setText(mPlayer.getWeight());
        mTextView_BioDetail.setText(mPlayer.getBio());
    }
}
