package com.example.mapmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.MarkerOptions;

public class AddMarkerActivity extends AppCompatActivity {
    private static final String LOG_TAG = FavouritesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);

        Intent intent = getIntent();
        final MarkerOptions newMarker = intent.getParcelableExtra("MARKER");
        final EditText new_title = findViewById(R.id.input_marker_title);
        final EditText new_description = findViewById(R.id.input_marker_description);
        Button button_add_marker = findViewById(R.id.button_add_marker);

        button_add_marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveTitle = new_title.getText().toString();
                String saveDescription = new_description.getText().toString();
                newMarker.title(saveTitle);
                newMarker.snippet(saveDescription);
                Intent outputIntent = new Intent();
                outputIntent.putExtra("MARKER", newMarker);
                setResult(RESULT_OK, outputIntent);
                finish();
            }
        });
    }
}
