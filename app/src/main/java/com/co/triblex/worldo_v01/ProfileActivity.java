package com.co.triblex.worldo_v01;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity /* implements EditProfileFrag.OnSaveListener */ {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        if (savedInstanceState == null) {
            Fragment fragment = new ViewProfileFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ProfileFrameView, fragment)  // tom container i layout
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }
/*
    @Override
    public void onSave(String AGE, String COUNTRY, String LANGUAGE, String PHONE) {

        User user = new User();
        user.age = AGE;
        user.country = COUNTRY;
        user.language = LANGUAGE;
        user.phone = PHONE;



        ViewProfileFrag fragment = new ViewProfileFrag();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ProfileFrameView, fragment)
                .addToBackStack(null)
                .commit();
        */
        /*

        Log.d("ProfileActivity", AGE);
        Log.d("ProfileActivity", COUNTRY);
        Log.d("ProfileActivity", LANGUAGE);
        Log.d("ProfileActivity", PHONE);

        fragment.updateUserInfo(AGE, COUNTRY, LANGUAGE, PHONE);

    }
    */
}
