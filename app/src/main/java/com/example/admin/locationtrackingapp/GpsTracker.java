package com.example.admin.locationtrackingapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import java.security.Permission;

public class GpsTracker extends Service implements LocationListener {

    private final Context context;
    boolean isGpsEnable = false;
    boolean isNetworkEnable = false;
    boolean canGetLocation = false;

    Location location;
    protected LocationManager locationManager;

    public GpsTracker(Context context) {

        this.context = context;
    }


    public Location getLocation() { // create getLocation method
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGpsEnable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isGpsEnable) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                if (location == null) {
                    if (isNetworkEnable) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }

                    }
                }
            }

        } catch (Exception e) {

        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onProviderDisabled(String provider) {

    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
