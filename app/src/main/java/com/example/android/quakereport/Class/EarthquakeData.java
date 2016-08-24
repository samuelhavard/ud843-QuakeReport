package com.example.android.quakereport.Class;

import java.util.Date;

/**
 * Created by samue_000 on 8/22/2016.
 */
public class EarthquakeData {

    private String mLocation;
    private String mDate;
    private double mMag;

    public EarthquakeData(String mLocation, String mDate, double mMag) {
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mMag = mMag;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }

    public double getMag() {
        return mMag;
    }
}
