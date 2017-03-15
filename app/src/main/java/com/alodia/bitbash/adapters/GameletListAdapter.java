package com.alodia.bitbash.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/15/2017.
 */

public class GameletListAdapter extends RecyclerView.Adapter<GameletListAdapter.GameletViewHolder> {
    List<Gamelet> mGamelets = new ArrayList<>();
    CreateBashActivity mParent;

    public GameletListAdapter(List<Gamelet> gamelets, CreateBashActivity parent){
        mGamelets = gamelets;
        mParent = parent;
    }

    @Override
    public GameletListAdapter.GameletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gamelet, parent, false);
        GameletViewHolder viewHolder = new GameletViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameletListAdapter.GameletViewHolder holder, int position) {
        holder.bindGamelet(mGamelets.get(position));
    }

    @Override
    public int getItemCount() {
        return mGamelets.size();
    }

    public class GameletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.textView_Name) TextView mTextView_Name;
        @BindView(R.id.imageView_AddGame) ImageView mImageView_AddGame;

        public GameletViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
            mImageView_AddGame.setOnClickListener(this);

        }

        public void bindGamelet(Gamelet gamelet){
            Typeface tf = Typeface.createFromAsset(mParent.getAssets(), "fonts/PressStart2P-Regular.ttf");
            mTextView_Name.setTypeface(tf);
            mTextView_Name.setText(gamelet.getName());
        }

        @Override
        public void onClick(View view) {
            if(view == mImageView_AddGame){
                Gamelet gamelet = mGamelets.get(getAdapterPosition());
                mParent.addGame(gamelet.getGameId());
                Toast.makeText(mParent,  gamelet.getName() + " added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
