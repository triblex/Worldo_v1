package com.co.triblex.worldo_v01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    singleton sing;
    Button signUnBTN;
    EditText name, age, password, username, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.nameTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        age = (EditText) findViewById(R.id.ageTxt);
        username = (EditText) findViewById(R.id.usernameTxt);
        email = (EditText) findViewById(R.id.emailTxt);

        signUnBTN = (Button) findViewById(R.id.signupbtn);
        signUnBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == signUnBTN) {
                User newUser = new User();
                newUser.name = name.getText().toString();
                newUser.password = password.getText().toString();
                newUser.username = username.getText().toString();
                newUser.age = age.getText().toString();
                newUser.email = email.getText().toString();

            if(!checkExisting()) {
                sing.users.add(newUser);

                Log.d("Users", newUser.username);
                Log.d("Users", newUser.password);

                Toast toast = Toast.makeText(this, "User created!", Toast.LENGTH_SHORT);
                toast.show();

                Intent back = new Intent(this, LogInActivity.class);
                startActivity(back);
            } else {
                Toast toast = Toast.makeText(this, "User already existst, try again", Toast.LENGTH_SHORT);
                toast.show();
                name.clearComposingText();
                age.clearComposingText();
                password.clearComposingText();
                username.clearComposingText();
                email.clearComposingText();
            }
        }
    }

    private boolean checkExisting() {
        System.out.println("Users in total: "+sing.users.size());
        for (User temp:sing.users) {
            if(temp.username.equals(username.getText().toString())){
                return true;
            }
        }
        return false;
    }
}

