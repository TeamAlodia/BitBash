package com.alodia.bitbash.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.models.HighScoreTable;
import com.alodia.bitbash.ui.activities.CreateBashActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alaina Traxler on 3/15/2017.
 */

public class CriteriaListAdapter extends RecyclerView.Adapter<CriteriaListAdapter.CriteriaViewHolder> {
    List<HighScoreTable> mHighScoreTables = new ArrayList<>();
    CreateBashActivity mParent;

    public CriteriaListAdapter(List<HighScoreTable> highScoreTables, CreateBashActivity parent){
        mHighScoreTables = highScoreTables;
        mParent = parent;
    }

    @Override
    public CriteriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_criteria, parent, false);
        CriteriaViewHolder viewHolder = new CriteriaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CriteriaViewHolder holder, int position) {
        holder.bindCriteria(mHighScoreTables.get(position));
    }

    @Override
    public int getItemCount() {
        return mHighScoreTables.size();
    }

    public class CriteriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.textView_Name) TextView mTextView_Name;
        @BindView(R.id.spinner_Type) Spinner mSpinner_Type;
        @BindView(R.id.editText_CriteriaDescription) EditText mEditTextCriteriaDescription;

        private ArrayAdapter mSpinnerAdapter;

        public CriteriaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mSpinner_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i == 0){
                        mHighScoreTables.get(getAdapterPosition()).setType(Constants.TYPE_SCOREATTACK);
                        Log.d("Set" + mHighScoreTables.get(getAdapterPosition()).getGameName(), "to " + Constants.TYPE_SCOREATTACK);
                    }else if(i == 1){
                        mHighScoreTables.get(getAdapterPosition()).setType(Constants.TYPE_TIMETRIAL);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mSpinner_Type.setSelection(0);

            mEditTextCriteriaDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                //TODO: Maybe put raw whitespace protection in? Do we really need to hold their hands so much?
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length() == 0){
                        mHighScoreTables.get(getAdapterPosition()).setDescription(null);
                    }else{
                        mHighScoreTables.get(getAdapterPosition()).setDescription(charSequence.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        public void bindCriteria(HighScoreTable highScoreTable){
            Typeface tf = Typeface.createFromAsset(mParent.getAssets(), "fonts/PressStart2P-Regular.ttf");
            mTextView_Name.setTypeface(tf);
            mTextView_Name.setText(highScoreTable.getGameName());

            String criteriaDescription = highScoreTable.getDescription();
            if(criteriaDescription != null && criteriaDescription.length() != 0){
                mEditTextCriteriaDescription.setText(highScoreTable.getDescription());
            }
        }

        @Override
        public void onClick(View view) {
        }

        public void setUpSpinner(){
            mSpinner_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
