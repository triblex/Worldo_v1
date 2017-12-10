package com.co.triblex.worldo_v01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.GregorianCalendar;
//import com.google.android.gms.location.places;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener {

    int PLACE_PICKER_REQUEST = 1;
    int status;
    int daySelected;
    int monthSelected;
    int yearSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

   /*     status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
                GooglePlayServicesUtil.getErrorDialog(status, this,
                        100).show();
            }
        }
        if (status == ConnectionResult.SUCCESS) {
            int PLACE_PICKER_REQUEST = 199;
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            Context context = this;
            try {
                startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }*/

        Button searchBtn = (Button) findViewById(R.id.searchbtn);
        searchBtn.setOnClickListener(this);

        TextView findLocation = (TextView) findViewById(R.id.textView);
        findLocation.setOnClickListener(this);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,Toast.LENGTH_LONG).show();
                daySelected = date;
                monthSelected = month;
                yearSelected = year;
            }
        });



    }
    /*  @Override
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
                  Place place = PlacePicker.getPlace(data, this);
                  String toastMsg = String.format("Place: %s", place.getName());
                  Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
              }
              else {
                  String toastMsg = ("No location selected.");
                  Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
              }

          }
      }
  */
    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.textView)) {
            Intent i = new Intent(this, MapActivity.class);
            startActivity(i);
        }
        if(view == findViewById(R.id.searchbtn)){

        }
    }

    @Override
    public void onRestart()
    {  // After a pause OR at startup
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPref.getString("location", "Not Available");
        TextView findLocation = (TextView) findViewById(R.id.textView);
        findLocation.setText(location);
        super.onRestart();
        //Refresh your stuff here
    }
}
