package com.example.user.ghazal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FIREBASE";
    EditText username,password;
    Button logIn,signUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        logIn= (Button) findViewById(R.id.LogIn);
        logIn.setOnClickListener( this);

        signUp= (Button) findViewById(R.id.SignUp);
        signUp.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);
    }
    @Override
    public void onClick(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(view == signUp){
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);
        }else {
            if (user.equals("") || pass.equals("")) {
                Toast.makeText(this, "Empty User name or passwored", Toast.LENGTH_LONG).show();
            } else {
                signIn(user, pass);

            }
        }
    }
        public void signIn(String email, String password){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i =new Intent(MainActivity.this,ExpensesActivity.class);
                                startActivity(i);
                                //   updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                //    updateUI(null);
                            }

                            // ...
                        }
                    });
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;

        }

    }
