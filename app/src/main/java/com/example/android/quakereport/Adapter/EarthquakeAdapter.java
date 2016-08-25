package com.example.android.quakereport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.Class.EarthquakeData;
import com.example.android.quakereport.R;

import java.util.ArrayList;

/**
 * Created by samue_000 on 8/22/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<EarthquakeData> {

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
        magnitudeTextView.setText(currentData.getMag() + "");

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        locationTextView.setText(currentData.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        String date = currentData.getDate() + "";
        dateTextView.setText(date);

        return listItemView;

    }
}
