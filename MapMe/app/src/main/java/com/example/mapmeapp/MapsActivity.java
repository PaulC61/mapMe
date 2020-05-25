package com.example.mapmeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, Serializable {

    private static final String LOG_TAG = MapsActivity.class.getSimpleName();
    public static final String EXTRA_MARKERS = "com.example.android.mapmeapp.extra.MARKERS";
    private static final int MARKER_REQUEST = 1;

    private GoogleMap mMap;
    private List<MarkerOptions> myMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState != null) {
            myMarkers = (List<MarkerOptions>) savedInstanceState.getSerializable(EXTRA_MARKERS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem favourite) {
        if (favourite.getItemId() == R.id.menu_favourites_button) {
            Intent intent = new Intent(MapsActivity.this, FavouritesActivity.class);
            intent.putStringArrayListExtra(EXTRA_MARKERS, getMarkerTitles(myMarkers));
            startActivity(intent);
        }
        return true;
    }

    public ArrayList<String> getMarkerTitles(List<MarkerOptions> myMarkers) {
        ArrayList<String> markerTitles = new ArrayList<>();
        for (MarkerOptions marker : myMarkers) {
            markerTitles.add(marker.getTitle());
        }
        return markerTitles;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLang) {
                Intent intent = new Intent(MapsActivity.this, AddMarkerActivity.class);
                MarkerOptions newMarker = new MarkerOptions().position(latLang);
                intent.putExtra("MARKER", newMarker);
                startActivityForResult(intent, MARKER_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int request, int result, Intent intent) {
        super.onActivityResult(request, result, intent);
        if (request == MARKER_REQUEST) {
            if (result == RESULT_OK) {
                MarkerOptions newMarker = intent.getParcelableExtra("MARKER");
                mMap.addMarker(newMarker);
                myMarkers.add(newMarker);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(newMarker.getPosition()));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        if(myMarkers.size()>1){
            outState.putSerializable(EXTRA_MARKERS, (Serializable) myMarkers);
        }
    }
}
