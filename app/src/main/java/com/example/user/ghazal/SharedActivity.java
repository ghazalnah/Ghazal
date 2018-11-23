package com.example.user.ghazal;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.content.SharedPreferences.*;

public class SharedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);



    }
}
