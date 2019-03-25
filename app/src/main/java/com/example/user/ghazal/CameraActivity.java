package com.example.user.ghazal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1;
    private static final int SELECT_IMAGE = 2;

    ImageView imageView;
    Button btGallery, btTakePhot;


    /*A method that does intent to the Camera Request(Camera) if we chose btTakePhot
 or intent to Select Image(Gallery) if we chose btCamera  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView=findViewById(R.id.camera);
        btGallery=findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);
        btTakePhot=findViewById(R.id.btCamera);
        btTakePhot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btTakePhot) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else{

            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if the request was from camera and the result was OK meaning the camera worked
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            //the image captured is saved in the data object
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //set image captured to be the new image
            imageView.setImageBitmap(bitmap);
        }else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            //URI - unified resource locator is something like URL but can hold different type of paths
            // examples: file:///something.txt, http://www.example.com/, ftp://example.com
            // A Uri object is usually used to tell a ContentProvider what we want to access by reference
            Uri targetUri = data.getData();
            try {
                //Decode an input stream into a bitmap.
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }




}