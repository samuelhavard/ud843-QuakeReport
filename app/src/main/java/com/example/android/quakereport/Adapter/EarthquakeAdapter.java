package com.example.android.quakereport.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Class.EarthquakeData;
import com.example.android.quakereport.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Earthquake adapter is used to display information about earthquakes around the world to
 * the user.  This information contains the magnitude, location, time, and date of each
 * earthquake.
 */
public class EarthquakeAdapter extends ArrayAdapter<EarthquakeData> {
    private static final String DATE = "date";
    private static final String TIME = "time";

    public EarthquakeAdapter(Context context, ArrayList<EarthquakeData> earthquakeData) {
        super(context, 0, earthquakeData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String[] location = new String[2];

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        EarthquakeData currentData = getItem(position);

        //
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formattedMag(currentData.getMag()));

        //
        if (currentData.getLocation().contains(",")) {
            location = currentData.getLocation().split(",");
        } else {
            location[0] = currentData.getLocation();
            location[1] = "Near the ";
        }

        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(location[0]);

        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetLocationTextView.setText(location[1]);

        Date earthquakeDate = new Date(currentData.getDate());

        //creates and sets a formatted version of date to be displayed to the user.
        String dateFormat = formattedDateTime(earthquakeDate, DATE);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(dateFormat);

        //creates and sets a formatted version of time to be displayed to the user
        String formattedTime = formattedDateTime(earthquakeDate, TIME);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formattedTime);

        //creates a magnitude circle to be displayed with a text of the magnitude
        //the color of this circle will adjust for a given magnitude
        GradientDrawable magCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magColor = getMagColor(currentData.getMag());
        magCircle.setColor(magColor);

        return listItemView;

    }

    /**
     *formattedDateTime is a helper method designed to take a Unix time and return a formatted string
     * for either date or time.  If nether date of time are indicated, then the method returns the
     * Sting "Error".
     *
     * @param dateObject is a {@link Date} object as parsed from a {@link org.json.JSONObject} obtained
     *                   from earthquake.usgs.gov.
     * @param selection is a string that informs the helper function determine if the {@link Date}
     *                  object passed is supposed to return as a date or a time.  Constant
     *                  {@link String} variables are used to ensure the proper selections are made.
     * @return a string formatted using {@link SimpleDateFormat} for the selection of either time
     *                   or date.
     */
    private String formattedDateTime (Date dateObject, String selection) {
        final String TIME_FORMAT_STRING = "h:mm a";
        final String DATE_FORMAT_STRING = "LLL dd, yyyy";

        switch (selection) {
            case TIME:
                SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT_STRING);
                return timeFormat.format(dateObject);
            case DATE:
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
                return dateFormat.format(dateObject);
            default:
                return "Error";
        }
    }

    /**
     * formattedMag is a helper method used to ensure that the magnitude of an earthquake is
     * displayed with only one decimal point in the app.
     *
     * @param mag is the magnitude from the JSON stored as a float.
     * @return the formatted magnitude to one decimal place.
     */
    private String formattedMag (float mag) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }

    /**
     * getMagColor is a helper method used to determine the background color for the magnitude
     * circle.
     * @param mag is a float that represents the magnitude of the earthquake as supplied in the
     *            JSON reply
     * @return the {@link ContextCompat} color to be displayed in the magnitude circle.
     */
    private int getMagColor (float mag) {
        int check = (int) Math.floor(mag);
        int magColor;
        switch (check) {
            case 0:
            case 1:
                 magColor = R.color.magnitude1;
                break;
            case 2:
                magColor = R.color.magnitude2;
                break;
            case 3:
                magColor = R.color.magnitude3;
                break;
            case 4:
                magColor = R.color.magnitude4;
                break;
            case 5:
                magColor = R.color.magnitude5;
                break;
            case 6:
                magColor = R.color.magnitude6;
                break;
            case 7:
                magColor = R.color.magnitude7;
                break;
            case 8:
                magColor = R.color.magnitude8;
                break;
            case 9:
                magColor = R.color.magnitude9;
                break;
            default:
                magColor = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magColor);
    }
}
