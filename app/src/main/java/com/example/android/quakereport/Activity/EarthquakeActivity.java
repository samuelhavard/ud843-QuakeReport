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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapter.EarthquakeAdapter;
import com.example.android.quakereport.Class.EarthquakeData;
import com.example.android.quakereport.R;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<EarthquakeData> earthquakes = new ArrayList<>();
        // location, date, mag
        earthquakes.add(new EarthquakeData("San Francisco","8/22/201", 7.2));
        earthquakes.add(new EarthquakeData("London", "8/22/201", 6.1));
        earthquakes.add(new EarthquakeData("Tokyo", "8/22/201", 3.9));
        earthquakes.add(new EarthquakeData("Mexico City", "8/22/201", 5.4));
        earthquakes.add(new EarthquakeData("Moscow", "8/22/201", 2.8));
        earthquakes.add(new EarthquakeData("Rio de Janeiro", "8/22/201", 4.9));
        earthquakes.add(new EarthquakeData("Paris", "8/22/201", 1.6));

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
