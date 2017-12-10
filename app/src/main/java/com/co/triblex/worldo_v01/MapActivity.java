package com.co.triblex.worldo_v01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
                GooglePlayServicesUtil.getErrorDialog(status, this,
                        100).show();
            }
        }
        if (status == ConnectionResult.SUCCESS) {
            int PLACE_PICKER_REQUEST = 199;
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Result code: "+resultCode);
        System.out.println("Request code: "+requestCode);
        if (requestCode == 100){
            status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        }
        if (requestCode == 199) {

            //process Intent......
            if(data != null) {
                double lat;
                double lng;
                Place place = PlacePicker.getPlace(data, this);
                lat = place.getLatLng().latitude;
                lng = place.getLatLng().longitude;
                System.out.println("lat: " + lat);
                System.out.println("lng: " + lng);
                Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(lat, lng, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.size() > 0) {
                    System.out.println("by: " + addresses.get(0).getLocality());
                    // Create object of SharedPreferences.
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                    //now get Editor
                    SharedPreferences.Editor editor = sharedPref.edit();
                    if(addresses.get(0).getLocality() == null){
                        editor.putString("location", place.getName().toString());
                    }else {
                        //put your value
                        editor.putString("location", addresses.get(0).getLocality());
                    }
                    //commits your edits
                    editor.commit();
                }
                else {
                    // do your stuff
                }
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                String toastMsg = ("No location selected.");
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }
}
