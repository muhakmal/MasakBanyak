package com.baskom.masakbanyak;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lil G on 4/6/2018.
 */

public class CateringListAdapter extends RecyclerView.Adapter<CateringListAdapter.CateringListViewHolder> {

    private ArrayList<Catering> mCateringList = new ArrayList<>();
    private HomeFragment.HomeFragmentInteractionListener mListener;

    public CateringListAdapter(ArrayList<Catering> mCateringList, HomeFragment.HomeFragmentInteractionListener mListener) {
        this.mCateringList = mCateringList;
        this.mListener = mListener;
    }

    public ArrayList<Catering> getCateringList() {
        return mCateringList;
    }

    public void setCateringList(ArrayList<Catering> mCateringList) {
        this.mCateringList = mCateringList;
    }

    public class CateringListViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public CateringListViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.catering_name);
        }
    }

    @NonNull
    @Override
    public CateringListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CateringListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CateringListViewHolder holder, final int position) {
        holder.mTextView.setText(mCateringList.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onHomeFragmentInteraction(mCateringList.get(position).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCateringList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.itemview_catering;
    }

}
