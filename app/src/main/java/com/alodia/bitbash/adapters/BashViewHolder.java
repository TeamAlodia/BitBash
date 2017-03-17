package com.alodia.bitbash.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Bash;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/17/2017.
 */

public class BashViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_BashName)
    TextView mTextView_BashName;

    public BashViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindBash(Bash bash){
        Log.d("!!!!!", " in bindBash");
        mTextView_BashName.setText(bash.getName());
    }
}
