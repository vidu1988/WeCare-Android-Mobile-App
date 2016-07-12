package com.androidbelieve.drawerwithswipetabs;

/**
 * Created by Shishir on 5/21/2016.
 */
public class BuddyData {


    int id, buddyPoints;
    String buddyName, buddyType, buddyAddress, buddyInfo;
    double latitude, longitude;

    public BuddyData(String buddyName, String buddyType, String buddyAddress, String buddyInfo, int id, int buddyPoints, double latitude, double longitude ) {
        this.buddyName = buddyName;
        this.buddyType = buddyType;
        this.buddyAddress = buddyAddress;

        this.buddyInfo = buddyInfo;
        this.id = id;
        this.buddyPoints = buddyPoints;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setBuddyName(String buddyName)
    {
        this.buddyName = buddyName;
    }

    public String getBuddyName()
    {

        return this.buddyName;
    }

    public void setBuddyType(String buddyType)
    {
        this.buddyType = buddyType;
    }

    public String getBuddyType()
    {
        return this.buddyType;
    }

    public void setBuddyAddress(String buddyAddress)
    {
        this.buddyAddress = buddyAddress;
    }

    public String getBuddyAddress()
    {
        return this.buddyAddress;
    }

    public void setBuddyInfo(String buddyInfo)
    {
        this.buddyInfo = buddyInfo;
    }

    public String getBuddyInfo()
    {
        return this.buddyInfo;
    }


    public void setBuddyPoints(int buddyPoints)
    {
        this.buddyPoints = buddyPoints;
    }

    public int getBuddyPoints()
    {
        return this.buddyPoints;
    }


    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLongitude()
    {
        return this.longitude;
    }
}
