package com.example.user.ghazal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        ImageButton imagebtnyou, imagebtnpeople, imagebtnwork;

        imagebtnyou = (ImageButton) findViewById(R.id.imagebtnyou);
        imagebtnpeople = (ImageButton) findViewById(R.id.imagebtnpeople);
        imagebtnwork = (ImageButton) findViewById(R.id.imagebtnwork);

        imagebtnyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=CU4l_rs50Kk")));
            }
        });


        imagebtnpeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mtcontrolling.com/self-employed-2")));
            }
        });


    }





}
