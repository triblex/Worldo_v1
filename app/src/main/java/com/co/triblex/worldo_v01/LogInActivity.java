package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    Button logInBTN, signUnBTN;
    EditText username, password;
    singleton sing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sing = new singleton();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        logInBTN = (Button) findViewById(R.id.loginbtn);
        logInBTN.setOnClickListener(this);

        signUnBTN = (Button) findViewById(R.id.signupbtn);
        signUnBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == logInBTN) {
            if(checkUser()) {
                Intent i = new Intent(this, StartsideActivity.class);
                startActivity(i);
            }
            else {
                Toast toast = Toast.makeText(this, "Login did not match!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if(view == signUnBTN) {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
    }

    private boolean checkUser(){
        for (User temp: sing.users) {
            Log.d("Users", temp.username);
            Log.d("Users", temp.password);
            if(username.getText().toString().equals(temp.username) && password.getText().toString().equals(temp.password)){
                return true;
            }
        }
        return false;
    }
}
