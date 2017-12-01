package com.co.triblex.worldo_v01;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileFrag extends Fragment implements View.OnClickListener{

    EditText age, country, language, phone;
    Button save;

    private DatabaseReference mDatabase;


    //OnSaveListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        age = (EditText) root.findViewById(R.id.age);
        country = (EditText) root.findViewById(R.id.country);
        language = (EditText) root.findViewById(R.id.language);
        phone = (EditText) root.findViewById(R.id.phone);

        save = (Button) root.findViewById(R.id.save);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(this);

        return root;
    }

    /*
    public interface OnSaveListener {
        void onSave(String AGE, String COUNTRY, String LANGUAGE, String PHONE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSaveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSaveListener");
        }
    }
    */

    @Override
    public void onClick(View view) {
        //mCallback.onSave(age.getText().toString(), country.getText().toString(), language.getText().toString(), phone.getText().toString());

        User user = new User(age.getText().toString(), country.getText().toString(), language.getText().toString(), phone.getText().toString());
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("my_app_user").child(fUser.getUid()).setValue(user);

        ViewProfileFrag fragment = new ViewProfileFrag();
        getFragmentManager().beginTransaction()
                .replace(R.id.ProfileFrameView, fragment)
                .addToBackStack(null)
                .commit();
    }
}
