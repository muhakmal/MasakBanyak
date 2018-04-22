package com.baskom.masakbanyak;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CateringFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CateringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CateringFragment extends Fragment {

    private static final String ARG_PARAM = "Catering";

    private Catering mCatering;

    private CateringFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private PacketListAdapter mPacketListAdapter;

    public CateringFragment() {

    }

    public static CateringFragment newInstance(Catering catering) {
        CateringFragment fragment = new CateringFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, catering);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCatering = (Catering) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catering, container, false);

        mRecyclerView = view.findViewById(R.id.packet_list);
        mPacketListAdapter = new PacketListAdapter(mCatering.getPacketList(), mListener);
        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        mRecyclerView.setAdapter(mPacketListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CateringFragmentInteractionListener) {
            mListener = (CateringFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CateringFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface CateringFragmentInteractionListener {
        void onCateringFragmentInteraction(Packet packet);
    }
}
