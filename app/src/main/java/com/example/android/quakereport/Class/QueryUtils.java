package com.example.android.quakereport.Class;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.quakereport.Activity.EarthquakeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthquakeData} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<EarthquakeData> extractEarthquakes(String url) {
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(makeURL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * makeURL is a helper class to convert a string into a URL
     *
     * @param urlString is a URL as a string passed from the {@link EarthquakeLoader}
     * @return a {@link URL} to be used in the http request.
     */
    private static URL makeURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url;
    } //end makeURL

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving earthquake JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }// end makeHttpRequest

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lines = reader.readLine();
            while (lines != null) {
                output.append(lines);
                lines = reader.readLine();
            }
        }
        return output.toString();
    } //end readFromStream

    private static List<EarthquakeData> extractFeatureFromJson(String earthquakeJSON) {
        List<EarthquakeData> earthquakes = new ArrayList<>();
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }
        try {
            JSONObject earthquakeRoot = new JSONObject(earthquakeJSON);
            JSONArray earthQuakeArray = earthquakeRoot.optJSONArray("features");

            for (int i = 0; i < earthQuakeArray.length(); i++) {
                JSONObject arrayObject = earthQuakeArray.getJSONObject(i);
                JSONObject propertiesObject = arrayObject.getJSONObject("properties");

                float mag = Float.parseFloat(propertiesObject.optString("mag"));
                String place = propertiesObject.optString("place");
                long date = propertiesObject.getLong("time");
                String url = propertiesObject.getString("url");

                earthquakes.add(new EarthquakeData(place, date, mag, url));
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return earthquakes;
    }//end extractFeatureFromJson
}