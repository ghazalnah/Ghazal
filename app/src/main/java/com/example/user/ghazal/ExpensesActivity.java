package com.example.user.ghazal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lvExpences;
    ArrayList<Item> expenses;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        lvExpences = (ListView) findViewById(R.id.lvExpences);
        expenses = new ArrayList<>();

        expenses.add(new Item("Test1", "Test2", R.drawable.home));
        adapter = new CustomAdapter(this, R.layout.custom_row,expenses );

        lvExpences.setAdapter(adapter);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }
}
