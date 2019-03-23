package com.example.user.ghazal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.StringTokenizer;

public class NewExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,category,expenses;
    TextView tvCategory;
    Button add, btCategory;
    String categoryType;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Expences");
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);


        name =(EditText) findViewById(R.id.etName);
        expenses =(EditText) findViewById(R.id.etExpense);

        tvCategory = findViewById(R.id.tvCategory);
        add =(Button) findViewById(R.id.btAdd);
        add.setOnClickListener(this);

        btCategory =(Button) findViewById(R.id.btCategory);
        btCategory.setOnClickListener(this);

    }
// category --> listview
    @Override
    public void onClick(View v) {
        if(btCategory == v){
            showCategoryDialogButtonClicked(v);
        }else {
            String name1 = name.getText().toString();
            String category1 = tvCategory.getText().toString();
            int expenses1 = Integer.parseInt(expenses.getText().toString());
            Intent i = new Intent(this, ExpensesActivity.class);
            Item item = new Item(name1, category1, expenses1);
            if (category1.equals("Travel expenses")) {
                item.setImage(R.drawable.airplane);
            } else if (category1.equals("Home expenses")) {
                item.setImage(R.drawable.homelast);
            }
            else if (category1.equals("Salary")) {
                item.setImage(R.drawable.homelast);
            }
            else if (category1.equals("University expenses")) {
                item.setImage(R.drawable.unilast);
            }
            myRef.child(currentUser.getUid()).push().setValue(item);

            startActivity(i);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }
    public void showCategoryDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a category");

        // add a radio button list
        final String[] countries = {"Travel expenses", "Salary", "University expenses","Home expenses"};
        int checkedItem = 1; // flag
        builder.setSingleChoiceItems(countries, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvCategory.setText(countries[which]);
                categoryType = countries[which];

            }
        });

        // add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
