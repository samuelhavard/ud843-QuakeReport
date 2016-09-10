package com.example.android.quakereport.Class;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by samue_000 on 9/9/2016.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeData>> {

    private String mUrl;
    public static final String LOG_TAG = EarthquakeLoader.class.getName();

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "onStartLoading");
        forceLoad();
    }

    @Override
    public List<EarthquakeData> loadInBackground() {
        Log.v(LOG_TAG, "loadInBackground");
        if (mUrl == null) {
            return null;
        }
        List<EarthquakeData> earthquakeData = QueryUtils.extractEarthquakes(mUrl);
        return earthquakeData;
    }
}
