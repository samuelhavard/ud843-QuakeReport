package com.example.android.quakereport.Class;

/**
 * EarthquakeData class is used to store information about individual earthquakes.
 */
public class EarthquakeData {

    private String mLocation;
    private long mDate;
    private float mMag;
    private String mURL;

    public EarthquakeData(String Location, long Date, float Mag, String URL) {
        this.mLocation = Location;
        this.mDate = Date;
        this.mMag = Mag;
        this.mURL = URL;
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

    public String getURL() {
        return mURL;
    }
}
