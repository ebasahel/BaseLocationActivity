package sa.thiqah.emanbasahel.locationutilsapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends BaseLocationActivity implements OnMapReadyCallback, BaseLocationActivity.onLocationConnected {

    private GoogleMap mMap;
    private MarkerOptions mMarkerOptions;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displaying the map
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /**
         * to display the blue marker that indicated user's current location
         * (it needs user's permission)
         */
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);
        else checkPermission();

    }

    //region to add marker on map
    public void addMarker(final LatLng latlng, final int zoom) {
        mMarkerOptions = new MarkerOptions().position(latlng);
        mMarker= mMap.addMarker(mMarkerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
    }
    //endregion

    /**
     *
     * @param location Firstly, it's the last known location then it's received from the locationListener
     */
    @Override
    public void getCurrentLocation(Location location) {
        /**
         * if the marker is NULL add marker else update its position to the latest received location
         */
        if (mMarker == null) {
            addMarker(new LatLng(location.getLatitude(), location.getLongitude()), 15);
        }else
            mMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));

    }
}
