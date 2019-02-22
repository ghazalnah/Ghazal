package com.example.user.ghazal;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener{

    ListView lvExpences;
    ArrayList<Item> expenses;
    CustomAdapter adapter;

    Button plus;
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseUser currentUser = mAuth.getCurrentUser();


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Expences");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        lvExpences = (ListView) findViewById(R.id.lvExpences);
        expenses = new ArrayList<>();
   //     expenses.add(new Item("aaa","bbbb",55));
        adapter = new CustomAdapter(this, R.layout.custom_row,expenses );

        lvExpences.setAdapter(adapter);
        lvExpences.setOnItemClickListener(this);
        lvExpences.setOnItemLongClickListener(this);
        plus= (Button) findViewById(R.id.plusbtn);
        plus.setOnClickListener(this);


        myRef.child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Item item =  dataSnapshot.getValue(Item.class);
                item.setKey(key);
                expenses.add(item);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {


        Intent i = new Intent(this, NewExpenseActivity.class);
        startActivity(i);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                myRef.child(currentUser.getUid()).child(expenses.get(position).getKey()).setValue(null);
                expenses.remove(expenses.get(position));
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();

        return false;
    }

}
