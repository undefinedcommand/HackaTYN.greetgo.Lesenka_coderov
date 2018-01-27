package bus.notifier.busnotifier.Scenes;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import org.ankit.gpslibrary.MyTracker;

import bus.notifier.busnotifier.R;
import bus.notifier.busnotifier.Services.LocationService;

public class MapScene extends Fragment {

    private SupportMapFragment mapView;
    private GoogleMap mapScene;
    private FusedLocationProviderClient mFusedLocationClient;


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    public MapScene() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_scene, container, false);

        mapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                mapScene = map;

                LocationService locationService = new LocationService(getContext());
                double lat = locationService.getLatitude();
                double lng = locationService.getLongitude();
                CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).bearing(90).tilt(30).target(new LatLng(lat, lng)).build();
                mapScene.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));
                mapScene.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return view;
    }
}
