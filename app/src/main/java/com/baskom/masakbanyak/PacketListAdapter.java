package com.baskom.masakbanyak;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PacketListAdapter extends RecyclerView.Adapter<PacketListAdapter.PacketListViewHolder> {

    private ArrayList<Packet> mPacketList = new ArrayList<>();
    private CateringFragment.CateringFragmentInteractionListener mListener;

    public PacketListAdapter(ArrayList<Packet> mPacketList, CateringFragment.CateringFragmentInteractionListener mListener){
        this.mPacketList = mPacketList;
        this.mListener = mListener;
    }

    public ArrayList<Packet> getPacketList() {
        return mPacketList;
    }

    public void setPacketList(ArrayList<Packet> mPacketList) {
        this.mPacketList = mPacketList;
    }

    public class PacketListViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public PacketListViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.packet_name);
        }
    }

    @NonNull
    @Override
    public PacketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PacketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PacketListViewHolder holder, final int position) {
        holder.mTextView.setText(mPacketList.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCateringFragmentInteraction(mPacketList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPacketList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.itemview_packet;
    }
}
