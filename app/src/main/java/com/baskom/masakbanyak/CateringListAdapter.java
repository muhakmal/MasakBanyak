package com.baskom.masakbanyak;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lil G on 4/6/2018.
 */

public class CateringListAdapter extends RecyclerView.Adapter<CateringListAdapter.CateringListViewHolder> {

    private ArrayList<Catering> cateringList = new ArrayList<>();

    public CateringListAdapter(ArrayList<Catering> cateringList) {
        this.cateringList = cateringList;
    }

    public ArrayList<Catering> getCateringList() {
        return cateringList;
    }

    public void setCateringList(ArrayList<Catering> cateringList) {
        this.cateringList = cateringList;
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
    public void onBindViewHolder(@NonNull CateringListViewHolder holder, int position) {
        holder.mTextView.setText(cateringList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cateringList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.itemview_catering;
    }
}
