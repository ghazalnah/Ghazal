package com.example.user.ghazal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayList<Item> itemArrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        listView = (ListView) findViewById(R.id.lvItem);

        itemArrayList = new ArrayList<>();

        itemArrayList.add(new Item("Work", "Categoty: Work", R.drawable.work));
        itemArrayList.add(new Item("Home Expenses", "Category: Home", R.drawable.home));
        itemArrayList.add(new Item("Traveling", "Category: Traveling", R.drawable.car));
        itemArrayList.add(new Item("Umiversity Expenses", "Category: University", R.drawable.uni));

        customAdapter = new CustomAdapter(this, R.layout.custom_row,itemArrayList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
