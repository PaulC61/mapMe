package com.example.mapmeapp;

import android.content.Intent;
import android.os.Bundle;

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

        //loading markers
    }
}
