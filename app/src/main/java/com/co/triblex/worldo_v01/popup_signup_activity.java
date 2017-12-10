package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Triblex on 16-11-2017.
 */

public class popup_signup_activity extends AppCompatActivity implements View.OnClickListener{

    public Button sign_up_button;
    private FirebaseAuth mAuth;
    public EditText usernameTxt, emailTxt, passTxt, phoneTxt;

    @Override
    protected void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);

        setContentView(R.layout.activity_popup_signup);

        mAuth = FirebaseAuth.getInstance();

        sign_up_button = (Button) findViewById(R.id.signUp);
        sign_up_button.setOnClickListener(this);

        usernameTxt = (EditText) findViewById(R.id.username);
        emailTxt = (EditText) findViewById(R.id.email);
        passTxt = (EditText) findViewById(R.id.password);
        phoneTxt = (EditText) findViewById(R.id.phone);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8) , (int) (height*.6));
    }


    @Override
    public void onClick(View view) {
        if (view == sign_up_button) {
            if (emailTxt.getText().toString().matches("") || passTxt.getText().toString().matches("") || usernameTxt.getText().toString().matches("") || phoneTxt.getText().toString().matches("")) {
                Toast.makeText(popup_signup_activity.this, "You can't sign up without proper credentials.", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString(), passTxt.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SignUp", "createUserWithEmail:success");
                            Toast.makeText(popup_signup_activity.this, "Authentication success", Toast.LENGTH_SHORT).show();
                            userProfile(usernameTxt.getText().toString().trim());
                            Intent i = new Intent(popup_signup_activity.this, LogIn_Firebase_Activity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(popup_signup_activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
    private void userProfile(String displayName){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TEST", "User profile updated.");
                        }
                    }
                });
        mAuth.signOut();
    }
}
