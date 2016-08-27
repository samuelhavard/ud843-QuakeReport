/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quakereport.Adapter.EarthquakeAdapter;
import com.example.android.quakereport.Class.EarthquakeData;
import com.example.android.quakereport.Class.QueryUtils;
import com.example.android.quakereport.R;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        final ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes();

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        // Set the onItemClickListener and navigates the user to the USGS web page
        // that contains information about the selected earthquake.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Initialize the intent and earthquake object needed to navigate to the
                // correct URL for the clicked earthquake.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                EarthquakeData earthquakeObject = earthquakes.get(position);

                // Get the URI from the earthquake object for the clicked item and set the
                //data to the intent that will navigate to the USGS site in a browswer.
                Uri uri = Uri.parse(earthquakeObject.getURL());
                intent.setData(uri);

                startActivity(intent);
            }
        });
    }
}
