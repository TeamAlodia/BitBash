package com.alodia.bitbash.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Gamelet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/15/2017.
 */

public class GameletListAdapter extends RecyclerView.Adapter<GameletListAdapter.GameletViewHolder> {
    List<Gamelet> mGamelets = new ArrayList<>();

    public GameletListAdapter(List<Gamelet> gamelets){
        mGamelets = gamelets;
        Log.d("In Constructor", String.valueOf(mGamelets.size()));
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

    public class GameletViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textView_Name) TextView mTextView_Name;

        public GameletViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindGamelet(Gamelet gamelet){
            mTextView_Name.setText(gamelet.getName());
        }
    }
}
