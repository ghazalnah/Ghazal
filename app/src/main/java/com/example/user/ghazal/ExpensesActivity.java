package com.example.user.ghazal;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

    private static final int NOTIFICATION_REMINDER_NIGHT = 3;
    ListView lvExpences;
    ArrayList<Item> expenses;
    CustomAdapter adapter;

    double income =0.0, outcome = 0.0;
    TextView tvIncome, tvOutcome;

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

        tvIncome = findViewById(R.id.tvIncome);
        tvOutcome = findViewById(R.id.tvOutCome);

        myRef.child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                Item item =  dataSnapshot.getValue(Item.class);
                item.setKey(key);
                expenses.add(item);
                adapter.notifyDataSetChanged();

                if(item.getCategory().equals("Salary"))
                    income += item.getExpenses();
                else
                    outcome -= item.getExpenses();

                tvOutcome.setText("OutCome: "+outcome);
                tvIncome.setText("InCome: "+income);

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

        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(this, NewExpenseActivity.class);
        startActivity(intent);

        for(int i = 0; i<expenses.size();i++){

            if(expenses.get(i).getCategory().equals("Salary"))
                income += expenses.get(i).getExpenses();
            else
                outcome= expenses.get(i).getExpenses();
        }
        tvOutcome.setText("OutCome: "+outcome);
        tvIncome.setText("In Come: "+income);

    }

    //button for select radio options
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        if(expenses.get(position).getCategory().equals("Salary")){
            income += expenses.get(position).getExpenses();
            tvIncome.setText("Income: "+income);
        }else{
            outcome += expenses.get(position).getExpenses();
            tvOutcome.setText("Outcome: "+outcome);
        }



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
