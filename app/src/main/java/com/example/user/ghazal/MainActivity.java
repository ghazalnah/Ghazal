package com.example.user.ghazal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    Button logIn,signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        logIn=findViewById(R.id.LogIn);
        logIn.setOnClickListener( this);

        signUp= findViewById(R.id.SignUp);
        signUp.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.LogIn:
                Intent i =new Intent(this,ExpensesActivity.class);
                startActivity(i);
                break;
            case R.id.SignUp:
                Intent i2 =new Intent(this,SignUp.class);
                startActivity(i2);
                break;
            default:
                break;

        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

}
