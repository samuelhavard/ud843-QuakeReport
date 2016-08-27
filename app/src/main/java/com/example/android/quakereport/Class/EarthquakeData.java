package com.example.android.quakereport.Class;

/**
 * Created by samue_000 on 8/22/2016.
 */
public class EarthquakeData {

    private String mLocation;
    private long mDate;
    private float mMag;

    public EarthquakeData(String mLocation, long mDate, float mMag) {
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mMag = mMag;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getDate() {
        return mDate;
    }

    public float getMag() {
        return mMag;
    }
}
