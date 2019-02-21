package com.candybreaker.user.labmaps;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Location location;
    private TextView locationTv;
    Button btn;
    Double lat;
    Double log;
    public String address;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; //5 secs
    //List for permission
    private ArrayList<String> permissionToRequest;
    private ArrayList<String> permissionRejected = new ArrayList<>();
    private ArrayList<String> permission = new ArrayList<>();
    //Integer for permission Request
    private static final int All_PERMISSION_RESULT = 1011;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTv = (TextView)findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button);
        permission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permission.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionToRequest = permissionToRequest(permission);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(permissionToRequest.size() > 0){
                requestPermissions(permissionToRequest.
                toArray(new String[permissionToRequest.size()]), All_PERMISSION_RESULT);
            }

        }
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
    }

    private ArrayList<String> permissionToRequest(ArrayList<String> wantedPermissions){
        ArrayList<String> result = new ArrayList<>();

        for(String perm: wantedPermissions){
            if(!hasPermission(perm)){
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(googleApiClient != null){
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        if(!checkPlayService()){
            locationTv.setText("you need to install Google Play Services");
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(googleApiClient != null && googleApiClient.isConnected()){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    private boolean checkPlayService(){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS){
            if(apiAvailability.isUserResolvableError(resultCode)){
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else{
                finish();
            }
            return false;
        }
        return true;

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;

        }
        //Last Location
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(location != null){
         //   locationTv.setText("Latitude: "+ location.getLatitude() + "\nLongitude: "+location.getLongitude());
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if(listAddress != null && listAddress.size() > 0){
                    address = " ";
                    if( listAddress.get(0).getThoroughfare() != null){
                        address += listAddress.get(0).getThoroughfare() + ", ";
                    }
                    if( listAddress.get(0).getAdminArea() != null){
                        address += listAddress.get(0).getLocality() + ", ";
                    }
                    if( listAddress.get(0).getLocality() != null){
                        address += listAddress.get(0).getLocality() + ", ";
                    }

                    if( listAddress.get(0).getCountryName() != null){
                        address += listAddress.get(0).getLocality() + ", ";
                    }
                    if( listAddress.get(0).getCountryCode() != null){
                        address += listAddress.get(0).getLocality() + ", ";
                    }
                    if( listAddress.get(0).getPostalCode() != null){
                        address += listAddress.get(0).getLocality() + " ";
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        startLocationUpdate();
    }

    private void startLocationUpdate(){
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this, "You need to enable Permission to display location", Toast.LENGTH_SHORT).show();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
           // locationTv.setText("Latitude: "+ location.getLatitude() + "\nLongitude: "+location.getLongitude());
            lat = location.getLatitude();
            log = location.getLongitude();
          //  Toast.makeText(this, Double.toString(lat)+"  "+Double.toString(log), Toast.LENGTH_SHORT).show();

        }

    }

    public void getLocation(View v){
        locationTv.setText(address);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case All_PERMISSION_RESULT:
               for (String perms : permissionToRequest){
                   if(!hasPermission(perms)){
                       permissionRejected.add(perms);
                   }
               }
               if(permissionRejected.size() > 0){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(shouldShowRequestPermissionRationale(permissionRejected.get(0))){
                            new AlertDialog.Builder(MainActivity.this).
                                    setMessage("These permissions are mandotory to get your location").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                requestPermissions(permissionRejected.toArray(new String[permissionRejected.size()]), All_PERMISSION_RESULT);
                                            }

                                        }
                                    }).setNegativeButton("Cancel", null).create().show();
                            return;
                        }
                    }

               } else {
                   if(googleApiClient != null){
                       googleApiClient.connect();
                   }
               }

                break;

        }
    }
}
