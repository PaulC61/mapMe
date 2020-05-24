package com.example.mapmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    private static final String LOG_TAG = FavouritesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Intent intent = getIntent();
        List<String> savedMarkerTitles = intent.getStringArrayListExtra(MapsActivity.EXTRA_MARKERS);

        //loading markers into list using ArrayAdapter
        ArrayAdapter<String> savedMarkerTitlesAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_2, savedMarkerTitles);
        ListView favouritesListView = findViewById(R.id.favourites_list);
        favouritesListView.setAdapter(savedMarkerTitlesAdapter);
    }
}
