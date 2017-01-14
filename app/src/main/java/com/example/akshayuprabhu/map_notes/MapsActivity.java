package com.example.akshayuprabhu.map_notes;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import static com.example.akshayuprabhu.map_notes.R.id.place;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    Button search;
    EditText place1;
    TextView lat,lon;
    public String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        place1 = (EditText) findViewById (R.id.place);
        lat = (TextView) findViewById(R.id.text1);
        lon = (TextView)findViewById(R.id.text2);
        search=(Button) findViewById(R.id.bsearch);

        View v=new View(this);
       // search.setOnClickListener(this);
    }

    public void onclickbtn(View v){

        String loc=(place1.getText().toString());
        extractLatLng obj=new extractLatLng();
        obj.place=loc;

        try{
            if((!(loc.equals(""))) || loc!=null )
            {
                 // arr=new ArrayList<String>();

                Log.e("\n\nobj.place = \n",obj.place);
               // String place=place1.getText().toString();
                //ArrayList<String> arr = extractLatLng.extractLatandLng(loc);
                obj.start();
                obj.join();

                String strlt=Double.toString(obj.latitude);
                String strln=Double.toString(obj.latitude);

                Log.v("\nLatitude by name : ",strlt);
//                Log.v("\nLatitude by method : ",obj.getLat().toString());
                Log.v("\nLongitude by name : ",strln);
//                Log.v("\nLongitude by method : ",obj.getLon());


                if(strlt!=null && strln!=null ){


//                    String a=obj.latitude.toString();
//                    String b=obj.longitude.toString();

                    double lt=obj.latitude;
                    double ln=obj.longitude;

                    lat.setText("latitude : "+strlt);
                    lon.setText("longitude : "+strln);

                    Log.v("double values of lt ",Double.toString(lt));
                    Log.v("double values of ln ",Double.toString(ln));


                    LatLng latlng = new LatLng(lt,ln);
                    mMap.addMarker(new MarkerOptions().position(latlng).draggable(true).title("Marker "));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                    //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                }else{
                    Toast.makeText(this,"lat and lng not retrieved",Toast.LENGTH_SHORT).show();

                }

            }else{
                Toast.makeText(this,"enter the location",Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

//    public void onClick(View v){
//
//       if(v == search){
//           //lat.setText("location is null" );
//           loc = place1.getText().toString();
//           //List<Address> addressList = null ;
//
//           //lat.setText("location is " + loc);
//
//           if(loc!=null && !loc.equals("") ){
//
//               lon.setText("lcation is " + loc);
//
//               Geocoder geocoder=new Geocoder(this);
//
//               try {
//                   addressList= geocoder.getFromLocationName(loc,10);
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//
//
//               Address address = addressList.get(0);
//
//               LatLng latlng=new LatLng(address.getLatitude(),address.getLongitude());
//               mMap.addMarker(new MarkerOptions().position(latlng).title("Marker in Sydney"));
//               mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
//
//           }else{
//               Toast.makeText(this,"enter the location",Toast.LENGTH_SHORT).show();
//           }
//       }else{
//           Toast.makeText(this,"press on the button",Toast.LENGTH_SHORT).show();
//
//       }
//    }


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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Add a marker in Sydney and move the camera
        LatLng shimoga = new LatLng(13.9299299, 75.568101);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shimoga,10));
        mMap.setMyLocationEnabled(true);


    }



}
