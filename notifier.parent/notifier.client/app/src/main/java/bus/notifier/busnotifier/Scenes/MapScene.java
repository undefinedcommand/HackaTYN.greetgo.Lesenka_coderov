package bus.notifier.busnotifier.Scenes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import bus.notifier.busnotifier.R;

public class MapScene extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private MapView mapView;
    private GoogleMap mapScreen;

    private static final int UPDATE_INTERVAL = 7000;
    private static final int FASTEST_INTERVAL = 7001;
    private static final int DISPLACEMENT = 10;

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int SERVICES_REQUEST_CODE = 300;

    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;

    private GeoFire mapHandler;

    private Location lastLocation;

    private Button button;

    public MapScene() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_scene, container, false);

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(this);

        setUpLocation();

            button = (Button) view.findViewById(R.id.click);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLocationAnalyze();
                    updateState();
                }
            });

        return view;
    }

    private void setUpLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMISSION_REQUEST_CODE);
            button.setText("error1");
        }
        button.setText("error2");
        if (checkPlayServices()) {
            createLocationRequest();
            buildGoogleApiClient();
            displayLocation();
            button.setText("error3");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkPlayServices()) {
                        buildGoogleApiClient();
                        createLocationRequest();
                        displayLocation();
                        button.setText("error4");
                    }
                    button.setText("error5");
                }
        }
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(), SERVICES_REQUEST_CODE).show();
            } else {
                Toast.makeText(getActivity(), "This device is not supported", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }

    private void updateState() {
        displayLocation();
        startLocationAnalyze();
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            button.setText("error");
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            final double longitude = lastLocation.getLongitude();
            final double latitude = lastLocation.getLatitude();
            button.setText(longitude + " " + latitude);
            Log.d("Position", longitude + " " + latitude);
            Marker currentLocation = mapScreen.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location)).position(new LatLng(longitude, latitude)));
            mapScreen.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(longitude, latitude), 15.0f));
        }
    }

    private void startLocationAnalyze() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        startLocationAnalyze();
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
        Log.d("starton", "start");
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        displayLocation();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapScreen = googleMap;

    }
}
