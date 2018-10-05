package com.example.user.ghazal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    EditText supUser;
    EditText supEmail;
    EditText supPhone;
    EditText supPass;
    Button signUp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        supUser = (EditText) findViewById(R.id.SupUser);
        supEmail = (EditText) findViewById(R.id.SupEmail);
        supPhone = (EditText) findViewById(R.id.Suphone);
        supPass = (EditText) findViewById(R.id.SuPass);
        signUp2 = (Button) findViewById(R.id.SignUp2);
    }
}
