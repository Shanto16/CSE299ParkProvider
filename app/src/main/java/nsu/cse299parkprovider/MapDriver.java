package nsu.cse299parkprovider;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MapDriver extends Fragment implements OnMapReadyCallback, LocationListener {


    private GoogleMap gMap;
    private MapView mapView;

    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    public double curr_lat;
    public double curr_long;

    public double lat;
    public double longno;


    // public Latlong l1;
    DatabaseReference databaseprovider;
    DatabaseReference databaseprovider2;
    private FirebaseAuth mAuth;
    Marker marker;
    public user u2= new user();

    public provider p = new provider();
   // ArrayList<provider> venueList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        //venueList = new ArrayList<info>();

        mapView = rootView.findViewById(R.id.mainMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        //mAuth = FirebaseAuth.getInstance();

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

        //Log.d("Starting","App is started");

        LatLng coordinate = new LatLng(curr_lat, curr_long); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 18);
        map.animateCamera(location);
//        map.addMarker(new MarkerOptions().position(new LatLng(curr_lat, curr_long)).title("Marker"));
        //map.addMarker(new MarkerOptions().position(new LatLng(lat, longno)).title("Marker"));
        u2.setEmail("rahbar916@gmail.com");
        u2.setPhone("01620206937");
        Gson gson = new Gson();
        final String myJson = gson.toJson(u2);

        databaseprovider = FirebaseDatabase.getInstance().getReference("provider");
//        databaseprovider2 = FirebaseDatabase.getInstance().getReference("user");
//        databaseprovider2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for ( DataSnapshot d : dataSnapshot.getChildren()){
//                    user u = new user();
//                    u = d.getValue(user.class);
//                    if(mAuth.getCurrentUser().getEmail()==u.getEmail()){
//                        u2=u;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        databaseprovider.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (  DataSnapshot s : dataSnapshot.getChildren()) {
                    // for (int i = 0; i < venueList.size(); i++)
                    //{
                    //  int i=0;

                    //provider p = new provider();
                    p = s.getValue(provider.class);
                    //venueList.add(p);
                    double d = Double.parseDouble(p.getLatno());
                    double d1 = Double.parseDouble(p.getLongno());

                     final Marker mMarker = map.addMarker(new MarkerOptions()
                            .position(new LatLng(d, d1))
                            .title(p.getArea()));
                   // mMarker.setTag(p.getEmail());
                   // Toast.makeText(getContext(), "list size" + p.getArea() + "\n" + "values " + s.getValue(), Toast.LENGTH_SHORT).show();

                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(final Marker marker) {

                            //provider prvdr = new provider();
                            //prvdr = s.getValue(provider.class);

                          // if(prvdr.getEmail()==mMarker.getTag()){

                    //        }


                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView(R.layout.model_marker_details);
                            dialog.setTitle(p.getArea());

                            TextView area = dialog.findViewById(R.id.area);
                            TextView phone = dialog.findViewById(R.id.phone);
                            TextView security = dialog.findViewById(R.id.security);
                            TextView availability = dialog.findViewById(R.id.availability);
                            TextView fare = dialog.findViewById(R.id.fare);
                            Button book = dialog.findViewById(R.id.book_btn);


                            area.setText("Area: " + p.getArea());
                            phone.setText("Phone: " + p.getPhone());
                            security.setText("Security: " +p.getSecurity());
                            //Toast.makeText(getContext(), "list size" + prvdr.getArea() + "\n" + "values " + prvdr.getEmail(), Toast.LENGTH_SHORT).show();
                            book.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getContext(), "Booked", Toast.LENGTH_LONG).show();


                                    Intent intent = new Intent(getActivity(),ProviderActivity.class);
                                    intent.putExtra("myjson",myJson);
                                    mMarker.remove();
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();

                            return false;
                        }
                    });

//                    map.addMarker(new MarkerOptions().position(new LatLng(d, d1)).title("Area: " + p.getArea() + "\nPhone: " + p.getPhone()));
                    // Toast.makeText(getContext(), "lat "+ d+ "long" + d1, Toas0t.LENGTH_LONG).show();


                    // Log.d("Lattitude",p.getLatno());
                    // Log.d("Longitude",p.getLongno());
                    //}


                   // Toast.makeText(getContext(), "list size" + p.getArea() + "\n" + "values " + s.getValue(), Toast.LENGTH_SHORT).show();

                    // i++;
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

