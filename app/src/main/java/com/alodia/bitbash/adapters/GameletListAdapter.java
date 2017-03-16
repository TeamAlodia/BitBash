package com.alodia.bitbash.adapters;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Game;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.services.GamesDbService;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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

        private View mItemView;

        public GameletViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;

            itemView.setOnClickListener(this);
            mImageView_AddGame.setOnClickListener(this);

        }

        public void bindGamelet(Gamelet gamelet){
            Typeface tf = Typeface.createFromAsset(mParent.getAssets(), "fonts/PressStart2P-Regular.ttf");
            mTextView_Name.setTypeface(tf);
            mTextView_Name.setText(gamelet.getName());
        }

        @Override
        public void onClick(View view) {
            Gamelet gamelet = mGamelets.get(getAdapterPosition());
            if(view == mImageView_AddGame){
                mParent.addGame(gamelet.getGameId(), gamelet.getName());
                Toast.makeText(mParent,  gamelet.getName() + " added", Toast.LENGTH_SHORT).show();
            }else if(view == mItemView){
                fetchGame(gamelet.getGameId());
            }
        }

        public void fetchGame(final String query){
            final GamesDbService apiService = new GamesDbService();
            apiService.findGameById(query, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(mParent, "Unable to fetch game from GamesDB", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    JSONObject jsonObj = null;
                    apiService.processResultById(response, query, mParent);
                }
            });

        }
    }
}
