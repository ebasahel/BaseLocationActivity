package sa.thiqah.emanbasahel.locationutilsapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.instantapps.PackageManagerWrapper;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends BaseLocationActivity implements OnMapReadyCallback ,BaseLocationActivity.onLocationConnected {

    private GoogleMap mMap;
    private MarkerOptions mMarker;
    private double mMarkerLat,mMarkerLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (permissionCheck== PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);
        else checkPermission();

        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        if (currentLocation!=null)
        {
            addMarker(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 15);
        }

//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latlng) {
//
//                if (mMarker != null) {
//                    mMap.clear();
//                }
//                addMarker(latlng, 15);
//            }
//        });
    }

    public void addMarker(final LatLng latlng, final int zoom) {
        mMarker = new MarkerOptions().position(latlng);
        mMarker.draggable(true);
        mMap.addMarker(mMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
    }


    @Override
    public void getCurrentLocation(Location location) {
        currentLocation=location;
    }
}
