package com.example.hikerwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView latitudeText;
    TextView longitudeText;
    TextView altitudeText;
    TextView accuracyText;
    TextView addressText;
    LocationManager locationManager;
    LocationListener locationListener;
    String latitudeDisplay = "Latitude: ";
    String longitudeDisplay = "Longitude: ";
    String altitudeDisplay = "Altitude: ";
    String accuracyDisplay = "Accuracy: ";

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }

        }

    }

    public void updateLocationInfo(Location location){
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String addressDisplay = "";

        try {

            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(addressList != null && addressList.size() > 0){

                latitudeDisplay = "Latitude: " + addressList.get(0).getLatitude();
                longitudeDisplay = "Longitude: " + addressList.get(0).getLongitude();
                altitudeDisplay = "Altitude: " +  location.getAltitude();
                accuracyDisplay = "Accuracy: " + location.getAccuracy();

                if(addressList.get(0).getSubThoroughfare() != null){
                    addressDisplay += addressList.get(0).getSubThoroughfare() + " ";
                }

                if(addressList.get(0).getThoroughfare() != null){
                    addressDisplay += addressList.get(0).getThoroughfare() + "\n";
                }

                if(addressList.get(0).getLocality() != null){
                    addressDisplay += addressList.get(0).getLocality() + "\n";
                }

                if(addressList.get(0).getPostalCode() != null){
                    addressDisplay += addressList.get(0).getPostalCode() + "\n";
                }

                if(addressList.get(0).getCountryName() != null){
                    addressDisplay += addressList.get(0).getCountryName() + "\n";
                }

                addressText.setText(addressDisplay);
                latitudeText.setText(latitudeDisplay);
                longitudeText.setText(longitudeDisplay);
                altitudeText.setText(altitudeDisplay);
                accuracyText.setText(accuracyDisplay);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeText = findViewById(R.id.textViewLatitude);
        longitudeText = findViewById(R.id.textViewLongitude);
        altitudeText = findViewById(R.id.textViewAltitude);
        accuracyText = findViewById(R.id.textViewAccuracy);
        addressText = findViewById(R.id.textViewAddressBody);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                updateLocationInfo(location);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updateLocationInfo(lastKnownLocation);

        }


    }
}
