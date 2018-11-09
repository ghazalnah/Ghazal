package com.example.user.ghazal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Map;

public class FireActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email, pass;
    Button button;
    TextView email2, profession;
    ListView lvUsers;
    ArrayList<String> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");

        users=new ArrayList<String>();
        lvUsers=(ListView)findViewById(R.id.lvUsers);
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        lvUsers.setAdapter(adapter);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lvUsers.setAdapter(arrayAdapter);


        email = (EditText) findViewById(R.id.etEmail);
        pass = (EditText) findViewById(R.id.etPass);

        button = (Button) findViewById(R.id.btSave);

        email2 = (TextView) findViewById(R.id.tvEmail);
        profession = (TextView) findViewById(R.id.tvProfession);
        users = new ArrayList<String>();
        lvUsers = (ListView) findViewById(R.id.lvUsers);

/*        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email3 = email.getText().toString();
                myRef.child("Name").setValue(email);
                myRef.child("Profession").setValue("Student");
                myRef.child("Name").push().setValue(email);

            }
        });
        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                email2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        button.setOnClickListener(this);
        ArrayAdapter adapter1 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        lvUsers.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.getValue(String.class);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                Log.v("E_VALUE", "Data: "+ dataSnapshot.getValue());

                String name = map.get("Name");
                String profession1 = map.get("Profession");
                email2.setText(name);
                profession.setText(profession1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    @Override
    public void onClick(View v) {

    }
}
