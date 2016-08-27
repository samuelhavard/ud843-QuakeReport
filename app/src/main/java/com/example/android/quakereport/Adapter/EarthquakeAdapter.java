package com.example.android.quakereport.Adapter;

import android.content.Context;
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
 * Created by samue_000 on 8/22/2016.
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
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        magnitudeTextView.setText(formattedMag(currentData.getMag()));

        //
        if (currentData.getLocation().contains(",")) {
            location = currentData.getLocation().split(",");
        } else {
            location[0] = currentData.getLocation();
            location[1] = "Near the ";
        }

        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location_text_view);
        primaryLocationTextView.setText(location[0]);

        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.location_offset_text_view);
        offsetLocationTextView.setText(location[1]);

        Date earthquakeDate = new Date(currentData.getDate());

        String dateFormat = formattedDateTime(earthquakeDate, DATE);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateTextView.setText(dateFormat);

        String formattedTime = formattedDateTime(earthquakeDate, TIME);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(formattedTime);

        return listItemView;

    }

    private String formattedDateTime (Date dateObject, String selection) {
        String timeFormatString = "h:mm a";
        String dateFormatString = "LLL dd, yyyy";

        switch (selection) {
            case TIME:
                SimpleDateFormat timeFormat = new SimpleDateFormat(timeFormatString);
                return timeFormat.format(dateObject);
            case DATE:
                SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
                return dateFormat.format(dateObject);
            default:
                return "Error";
        }
    }

    private String formattedMag (float mag) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }
}
