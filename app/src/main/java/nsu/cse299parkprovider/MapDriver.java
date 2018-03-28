package nsu.cse299parkprovider;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class MapDriver extends Fragment implements OnMapReadyCallback, LocationListener {


    private GoogleMap gMap;
    private MapView mapView;

    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    public double curr_lat;
    public double curr_long;
   // public Latlong l1;
   DatabaseReference databaseprovider;
    Marker marker;
    List<provider> venueList;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);


        mapView = rootView.findViewById(R.id.mainMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationManager = (LocationManager) getContext().getSystemService(getContext().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);



        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onMapReady(final GoogleMap map) {



        LatLng coordinate = new LatLng(curr_lat, curr_long); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 18);
        map.animateCamera(location);
        map.addMarker(new MarkerOptions().position(new LatLng(curr_lat, curr_long)).title("Marker"));


        databaseprovider = FirebaseDatabase.getInstance().getReference("provider");
        databaseprovider.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    //getting artist
                    provider p = s.getValue(provider.class);
                    venueList.add(p);
                    for (int i = 0; i < venueList.size(); i++)
                    {
                        double d = Double.parseDouble(p.getLatno());
                        double d1 = Double.parseDouble(p.getLongno());
                        LatLng latLng = new LatLng(d,d1);
                        if (map != null) {
                            marker = map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                        }
                }


            }



                   }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }




        });




        //Latlong l2 = l1.getLatLong();
        //Toast.makeText(this, String.valueOf(l2.lat[0]),Toast.LENGTH_LONG).show();

        /*
        for(int a=0;a<=l1.count;a++){
            map.addMarker(new MarkerOptions().position(new LatLng(l1.lat[a],l1.lon[a])).title("Marker"));
        }
        */
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);







    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double longi = location.getLongitude();

        this.curr_lat = lat;
        this.curr_long = longi;

    }
}

