package com.baskom.masakbanyak;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Castor on 4/5/2018.
 */

public class Catering implements Serializable{
    private String name;
    private String address;
    private int rate;
    private ArrayList<Packet> packetList = new ArrayList<>();

    public Catering(String name, String address, int rate, ArrayList<Packet> packetList) {
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.packetList = packetList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public ArrayList<Packet> getPacketList() {
        return packetList;
    }

    public void setPacketList(ArrayList<Packet> packetList) {
        this.packetList = packetList;
    }
}
