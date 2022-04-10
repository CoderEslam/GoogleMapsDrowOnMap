package com.doubleclick.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.doubleclick.googlemaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback ,
        GoogleMap.OnMarkerClickListener ,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener
{

    private GoogleMap mMap;
    private Marker marker;
//    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng m1 = new LatLng(50, 50);
        LatLng m2 = new LatLng(25, 45);
        LatLng m3 = new LatLng(30, 100);
        LatLng m4 = new LatLng(20, 20);
//        LatLng m4 = new LatLng(200, 600);

        marker = mMap.addMarker(new MarkerOptions()
                .position(m1)
                .draggable(true)
                .title("M1"));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {//3
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
//                Toast.makeText(getApplicationContext(), "" + latLng, Toast.LENGTH_LONG).show();
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {//1
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                LatLng  latLng =  marker.getPosition();
                Toast.makeText(getApplicationContext(), "" + latLng, Toast.LENGTH_LONG).show();

                return false;
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {//2
            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {

                //getting the new position lat and lun
                LatLng  latLng =  marker.getPosition();
                Toast.makeText(getApplicationContext(), "the new postion" + latLng, Toast.LENGTH_LONG).show();


            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {//4
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) { //when i clicked on window above thr marker

                Toast.makeText(getApplicationContext(), "Info window is clicked " , Toast.LENGTH_LONG).show();

            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(m1));
//        Marker m =  mMap.addMarker(new MarkerOptions().position(sydney)
//                .draggable(true)// to determinde a location
//                .alpha(1)//to change opacity
//                .snippet("My Location is here")// additional tags that display below the title
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.facebook))
//                .visible(true) // to allow user see the marker
//                .rotation(90.0f) // to rotate the marker by 90 degree
//                .zIndex(1.0f) // if i have many of marker i will see the deference
//                .title("Marker in Sydney"));
//        Toast.makeText(getApplicationContext(),"Marker = "+m,Toast.LENGTH_LONG).show();
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //PolyLine.
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4)
                .width(50.0f)//to change width the line .
                .color(R.color.purple_200);// to change color of line.
        Polyline polyline = mMap.addPolyline(polylineOptions);
        mMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {//5
            @Override
            public void onPolylineClick(@NonNull Polyline polyline) {

            }
        });

        ArrayList<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(0,0));
        latLngs.add(new LatLng(5,0));
        latLngs.add(new LatLng(5,5));
        latLngs.add(new LatLng(0,5));


        //Polygons.
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4)
                .fillColor(R.color.purple_700)
                .strokeColor(Color.RED)
                .addHole(latLngs);// take a list of latlng
        Polygon polygon = mMap.addPolygon(polygonOptions);

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {//6
            @Override
            public void onPolygonClick(@NonNull Polygon polygon) {

            }
        });

        //Circle
        CircleOptions circleOptions = new CircleOptions()
                .center(m3)
                .radius(100000); //in meter

        Circle circle = mMap.addCircle(circleOptions);

        //List
        List<PatternItem> patternItems = Arrays.asList(
                new Dot(),
                new Gap(10)
                ,new Dash(30)
        );
        //Polyine.
        polyline.setPattern(patternItems);

    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {//1

        //Do here what you need .
        LatLng  latLng =  marker.getPosition();
        Toast.makeText(getApplicationContext(), "" + latLng, Toast.LENGTH_LONG).show();

        return false;
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {//2

    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {//2

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {//2

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {//3

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {//4

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {//5

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {//6

    }
}