package com.example.user.ghazal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.StringTokenizer;

public class NewExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,category,expenses;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        name =(EditText) findViewById(R.id.etName);
        category =(EditText) findViewById(R.id.etCategoty);
        expenses =(EditText) findViewById(R.id.etExpense);

        add =(Button) findViewById(R.id.btAdd);
        add.setOnClickListener(this);

    }
// category --> listview
    @Override
    public void onClick(View v) {
        String name1 = name.getText().toString();
        String category1 = category.getText().toString();
        int expenses1 = Integer.parseInt(expenses.getText().toString());
        Intent i = new Intent(this, ExpensesActivity.class);
        Item item = new Item(name1, category1, expenses1);
        startActivity(i);
    }
}
