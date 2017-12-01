package com.co.triblex.worldo_v01;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewProfileFrag extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public boolean updated;

    TextView age,phone,country,language,description, email, username, editProfile;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view_profile2, container, false);


        Spinner spinner = (Spinner) root.findViewById(R.id.spinner3);
        List<String> onlineStatus = new ArrayList<String>();
        onlineStatus.add("Available");
        onlineStatus.add("Away");
        onlineStatus.add("Invisible");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, onlineStatus);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(this);


        age = (TextView) root.findViewById(R.id.ageTxt3);
        phone = (TextView) root.findViewById(R.id.phoneTxt3);
        country = (TextView) root.findViewById(R.id.countryTxt3);
        language = (TextView) root.findViewById(R.id.languageTxt3);
        //description = (TextView) root.findViewById(R.id.descriptionTxt);
        email = (TextView) root.findViewById(R.id.emailTxt3);
        username = (TextView) root.findViewById(R.id.usernameTxt3);
        editProfile = (TextView) root.findViewById(R.id.editProfile);

        //FOR READING USER DATA FROM FIREBASE
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = database.getReference("my_app_user/"+fUser.getUid());

        ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    Log.d("Firebase!", user.age);
                    age.setText(user.age);
                    Log.d("Firebase!", user.country);
                    country.setText(user.country);
                    Log.d("Firebase!", user.language);
                    language.setText(user.language);
                    Log.d("Firebase!", user.phone);
                    phone.setText(user.phone);
                }
                else {
                    Log.d(">>","failed<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        email.setText(getString(R.string.google_status_fmt, fUser.getEmail()));
        username.setText(getString(R.string.user_display_name, fUser.getDisplayName()));

        editProfile.setOnClickListener(this);

        return root;
    }

    //ON CLICKS
    @Override
    public void onClick(View view) {
        getFragmentManager().beginTransaction()
                .replace(R.id.ProfileFrameView, new EditProfileFrag())
                .addToBackStack(null)
                .commit();
    }


    //FOR SPINNER
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
    public void updateUserInfo(String AGE, String COUNTRY, String LANGUAGE, String PHONE) {

        Log.d("ViewProfileFrag", AGE);
        Log.d("ViewProfileFrag", COUNTRY);
        Log.d("ViewProfileFrag", LANGUAGE);
        Log.d("ViewProfileFrag", PHONE);

        this.AGE = AGE;
        this.COUNTRY = COUNTRY;
        this.LANGUAGE = LANGUAGE;
        this.PHONE = PHONE;

        updated = true;


        age.setText(AGE);
        country.setText(COUNTRY);
        language.setText(LANGUAGE);
        phone.setText(PHONE);

    }

    public void updateUI (){
        age.setText(AGE);
        country.setText(COUNTRY);
        language.setText(LANGUAGE);
        phone.setText(PHONE);
        updated = false;
    }
    */
}
