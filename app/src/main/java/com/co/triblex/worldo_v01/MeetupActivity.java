package com.co.triblex.worldo_v01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class MeetupActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 8675309;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PICTURE_REQUEST = 0;

    ImageView imageView;
    Button buttonLoadImage;
    Button buttonCamera;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);

        imageView = (ImageView)findViewById(R.id.image);

        buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(this);

        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view == buttonLoadImage) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        if(view == buttonCamera) {
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            } else {
                String[] permissionRequest = {Manifest.permission.CAMERA};
                requestPermissions(permissionRequest, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                Log.d("imageView", "Setting imageView to selected image!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == TAKE_PICTURE_REQUEST && data != null ) {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            Log.d("imageView", "Setting imageView to captured image!");
        }

    }

    private void invokeCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                invokeCamera();
            }
        }
    }
}

