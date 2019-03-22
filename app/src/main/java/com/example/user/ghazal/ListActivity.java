package com.example.user.ghazal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvItems;
    CustomAdapter adapter;
    ArrayList<Item> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvItems = (ListView) findViewById(R.id.LvCountries);

        arrayList.add(new Item("Work","work", R.drawable.worklast));
        arrayList.add(new Item("University Expenses", "university" , R.drawable.unilast));
        arrayList.add(new Item("Traveling", "traveling" ,R.drawable.car));
        arrayList.add(new Item("Home Expenses","home", R.drawable.homelast));

        adapter = new CustomAdapter(this, R.layout.custom_row, arrayList);
        lvItems.setAdapter(adapter);
        lvItems.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){


        }
    }
}
