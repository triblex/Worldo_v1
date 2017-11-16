package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Triblex on 09-11-2017.
 */

public class LogIn_Firebase_Activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    public TextView mStatusTextView, mDetailTextView, mDisplayNameTextView;
    public Button sign_in_button, sign_out_and_disconnect, sign_up_button, continue_button;
    public EditText emailTxt, passTxt;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in_firebase);

        mAuth = FirebaseAuth.getInstance();

        emailTxt = (EditText) findViewById(R.id.emailTxt);
        passTxt = (EditText) findViewById(R.id.passTxt);

        mDetailTextView = (TextView) findViewById(R.id.mDetailTextView);
        mStatusTextView = (TextView) findViewById(R.id.mStatusTextView);
        mDisplayNameTextView = (TextView) findViewById(R.id.user_name);

        continue_button = (Button) findViewById(R.id.continue_button);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        sign_out_and_disconnect = (Button) findViewById(R.id.sign_out_and_disconnect);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);

        sign_up_button.setOnClickListener(this);
        sign_in_button.setOnClickListener(this);
        sign_out_and_disconnect.setOnClickListener(this);
        continue_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == sign_up_button) {
            Intent i = new Intent(LogIn_Firebase_Activity.this, popup_signup_activity.class);
            startActivity(i);
        }

        if (view == sign_out_and_disconnect) {
            mAuth.signOut();
            updateUI(null);
        }

        if (view == sign_in_button) {
            if (emailTxt.getText().toString().matches("") || passTxt.getText().toString().matches("")) {
                    Toast.makeText(LogIn_Firebase_Activity.this, "You can't sign without credentials.",
                            Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(emailTxt.getText().toString(), passTxt.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LogIn", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LogIn", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogIn_Firebase_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
            }
        }

        if (view == continue_button) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                Intent i = new Intent(LogIn_Firebase_Activity.this, StartsideActivity.class);
                startActivity(i);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
            mDisplayNameTextView.setText(getString(R.string.user_display_name, user.getDisplayName()));

            user.getPhoneNumber();

            sign_in_button.setVisibility(View.GONE);
            sign_out_and_disconnect.setVisibility(View.VISIBLE);
            sign_up_button.setVisibility(View.GONE);
            emailTxt.setVisibility(View.GONE);
            passTxt.setVisibility(View.GONE);
            continue_button.setVisibility(View.VISIBLE);


        } else {

            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            sign_in_button.setVisibility(View.VISIBLE);
            sign_out_and_disconnect.setVisibility(View.GONE);
            sign_up_button.setVisibility(View.VISIBLE);

            emailTxt.setVisibility(View.VISIBLE);
            passTxt.setVisibility(View.VISIBLE);
            continue_button.setVisibility(View.GONE);
        }
    }
}
