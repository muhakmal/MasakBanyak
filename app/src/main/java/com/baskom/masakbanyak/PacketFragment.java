package com.baskom.masakbanyak;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PacketFragment extends AppCompatDialogFragment {

    private static final String ARG_PARAM = "Packet";

    private Packet mPacket;

    private PacketFragmentInteractionListener mListener;

    private TextView mTextViewPacketName;
    private TextView mTextViewPacketPrice;

    public PacketFragment(){

    }

    public static PacketFragment newInstance(Packet packet) {
        PacketFragment fragment = new PacketFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, packet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mPacket = (Packet) getArguments().getSerializable(ARG_PARAM);
        }

        int style = DialogFragment.STYLE_NO_TITLE;
        int theme = android.R.style.Theme_DeviceDefault_Light_NoActionBar;
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_packet, container, false);

//        if (getDialog() != null && getDialog().getWindow() != null) {
//            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//            getDialog().getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        }

        mTextViewPacketName = view.findViewById(R.id.packet_name);
        mTextViewPacketName.setText(mPacket.getName());
        mTextViewPacketPrice = view.findViewById(R.id.packet_price);
        mTextViewPacketPrice.setText(Integer.toString(mPacket.getPrice()));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PacketFragmentInteractionListener) {
            mListener = (PacketFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PacketFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface PacketFragmentInteractionListener {
        void onPacketFragmentInteraction(Packet packet);
    }
}
