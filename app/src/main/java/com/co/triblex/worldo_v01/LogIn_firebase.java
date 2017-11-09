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

/**
 * Created by Triblex on 09-11-2017.
 */

public class LogIn_firebase extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    public TextView mStatusTextView, mDetailTextView, sign_up_link;
    public Button sign_in_button, sign_out_and_disconnect;
    public EditText emailTxt, passTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in_firebase);

        mAuth = FirebaseAuth.getInstance();

        emailTxt = (EditText) findViewById(R.id.emailTxt);
        passTxt = (EditText) findViewById(R.id.passTxt);

        mDetailTextView = (TextView) findViewById(R.id.mDetailTextView);
        mStatusTextView = (TextView) findViewById(R.id.mStatusTextView);

        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        sign_out_and_disconnect = (Button) findViewById(R.id.sign_out_and_disconnect);

        sign_up_link = (TextView) findViewById(R.id.sign_up_link);

        sign_up_link.setOnClickListener(this);
        sign_in_button.setOnClickListener(this);
        sign_out_and_disconnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == sign_up_link){
            mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString(), passTxt.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SignUp", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getBaseContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
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

            sign_in_button.setVisibility(View.GONE);
            sign_out_and_disconnect.setVisibility(View.VISIBLE);
        } else {

            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            sign_in_button.setVisibility(View.VISIBLE);
            sign_out_and_disconnect.setVisibility(View.GONE);
        }
    }
}
