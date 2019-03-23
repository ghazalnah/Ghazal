package com.example.user.ghazal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera extends AppCompatActivity implements View.OnClickListener{
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 0;

    Bitmap photo;
    Bitmap bitmap;


    Button btCamera;


    ImageView imageView2;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference().child("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btCamera=findViewById(R.id.btCamera);
        btCamera.setOnClickListener(this);
        imageView2 = findViewById(R .id.camera);
    }

    @Override
    public void onClick(View v) {
        if(btCamera == v){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,SELECT_IMAGE);
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(photo);
            String imagePath= saveImage(photo);

            SharedPreferences pref = getSharedPreferences("myPref",MODE_PRIVATE);
            //open the file for editing
            SharedPreferences.Editor editor = pref.edit();
            //Keyword , value format of values to be saved in the sharedprefrences
            //can be several values
            editor.putString("image",imagePath);

            editor.commit();
        } else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    public String saveImage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG" + timeStamp + ".jpg";
        //creating an object from type file
        File file = new File(filePath);

        try {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();

        }
        return filePath;
    }

}
















