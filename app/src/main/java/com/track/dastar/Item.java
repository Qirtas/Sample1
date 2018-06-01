package com.track.dastar;

/**
 * Created by Malik Muhamad Qirtas on 5/16/2017.
 */

public class Item
{

    String pickUpHigh, PickUpLow , address, desc , quantity, name;

    public Item(String pickUpHigh, String pickUpLow, String address, String desc, String quantity, String name)
    {
        this.pickUpHigh = pickUpHigh;
        PickUpLow = pickUpLow;
        this.address = address;
        this.desc = desc;
        this.quantity = quantity;
        this.name = name;
    }

    public String getPickUpHigh()
    {
        return pickUpHigh;
    }

    public void setPickUpHigh(String pickUpHigh)
    {
        this.pickUpHigh = pickUpHigh;
    }

    public String getPickUpLow()
    {
        return PickUpLow;
    }

    public void setPickUpLow(String pickUpLow)
    {
        PickUpLow = pickUpLow;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
