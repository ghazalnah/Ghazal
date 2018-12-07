package com.example.user.ghazal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class ExpensesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{

    ListView lvExpences;
    ArrayList<Item> expenses;
    CustomAdapter adapter;

    Button plus;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Users");
    FirebaseUser currentUser;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        lvExpences = (ListView) findViewById(R.id.lvExpences);
        expenses = new ArrayList<>();

        expenses.add(new Item("Test1", "Test2", R.drawable.home));
        adapter = new CustomAdapter(this, R.layout.custom_row,expenses );

        lvExpences.setAdapter(adapter);
        plus= (Button) findViewById(R.id.plusbtn);
        plus.setOnClickListener(this);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                String name = map.get("name");
                int expences = Integer.parseInt(map.get("expences"));
                String category = map.get("category");
                expenses.add(new Item(name, category, expences));
                adapter.notifyDataSetChanged();
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


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {


        Intent i = new Intent(this, NewExpenseActivity.class);
        startActivity(i);
    }
}
