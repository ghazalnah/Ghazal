package com.example.user.ghazal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WorkPlaces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_places);

        ImageButton imageshopping, imageeat;

        imageeat = (ImageButton) findViewById(R.id.imageeat);
        imageshopping = (ImageButton) findViewById(R.id.imageshopping);

        imageeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=aroma+haifa&npsic=0&rflfq=1&rlha=0&rllag=32794906,35001889,4130&tbm=lcl&ved=2ahUKEwj1ydWFzZvhAhWFZVAKHVFbDngQtgN6BAgKEAQ&tbs=lrf:!2m1!1e3!3sIAE,lf:1,lf_ui:4&rldoc=1#rlfi=hd:;si:;mv:!1m2!1d32.836983599999996!2d35.0781162!2m2!1d32.7570128!2d34.958683799999996;tbs:lrf:!2m1!1e3!3sIAE,lf:1,lf_ui:4")));
            }
        });

        imageshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?tbm=lcl&ei=B7mYXIfDK4PosAe7gZSYDA&q=adidas&oq=adidas&gs_l=psy-ab.3...5438.6257.0.6788.6.6.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..6.0.0....0.kRVhnsDosgM#rlfi=hd:;si:;mv:!1m2!1d32.8467401!2d35.095735999999995!2m2!1d32.7865445!2d35.0030417;tbs:lrf:!2m1!1e3!3sIAE,lf:1,lf_ui:4")));
            }
        });

    }



}
