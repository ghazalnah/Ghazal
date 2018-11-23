package com.example.user.ghazal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FIREBASESIGNUP";
    EditText supUser;
    EditText supEmail;
    EditText supPhone;
    EditText supPass;
    Button signUp2;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        supUser = (EditText) findViewById(R.id.SupUser);
        supEmail = (EditText) findViewById(R.id.SupEmail);
        supPhone = (EditText) findViewById(R.id.Suphone);
        supPass = (EditText) findViewById(R.id.SuPass);
        signUp2 = (Button) findViewById(R.id.SignUp2);
        signUp2.setOnClickListener(this);
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignUp.this, ExpensesActivity.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    @Override
    public void onClick(View v) {
        String email = supEmail.getText().toString();
        String pass = supPass.getText().toString();
        String phone = supPhone.getText().toString();
        String username = supUser.getText().toString();

        if(email.equals("") || pass.equals("") || phone.equals("") || username.equals("")){
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show();
        }else{
            createAccount(email, pass);
        }

    }
}
