package com.example.android.quakereport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Class.EarthquakeData;
import com.example.android.quakereport.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by samue_000 on 8/22/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<EarthquakeData> {
    private final String DATE = "date";
    private final String TIME = "time";

    public EarthquakeAdapter(Context context, ArrayList<EarthquakeData> earthquakeData) {
        super(context, 0, earthquakeData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        EarthquakeData currentData = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        magnitudeTextView.setText(currentData.getMag());

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        locationTextView.setText(currentData.getLocation());

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
}
