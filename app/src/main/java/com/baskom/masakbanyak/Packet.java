package com.baskom.masakbanyak;

import java.io.Serializable;

/**
 * Created by Castor on 4/5/2018.
 */

public class Packet implements Serializable {
    private String name;
    private int price;

    public Packet(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
