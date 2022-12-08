package com.example.pizzeria;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChicagoBBQChickenActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener {

    //private RecyclerView recyclerViewAvailable;
    private Spinner FlavorSpinner;
    private Button addButton;
    private Button removeButton;
    private static RadioButton smallButton;
    private static RadioButton mediumButton;
    private static RadioButton largeButton;
    private static TextView pizzaPrice;
    private TextView crustText;

    private ListView listview;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_layout);

        MainActivity.pizzaFactory = new ChicagoPizza();
        MainActivity.currentPizza = MainActivity.pizzaFactory.createBBQChicken();

        smallButton = findViewById(R.id.smallButton);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sizeSelection);
        smallButton = radioGroup.findViewById(R.id.smallButton);
        mediumButton = radioGroup.findViewById(R.id.mediumButton);
        largeButton = radioGroup.findViewById(R.id.largeButton);
        smallButton.setChecked(true);

        pizzaPrice = findViewById(R.id.pizzaPrice);
        crustText = findViewById(R.id.crustText);
        setPrice();
        setCrust();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setPrice();
            }
        });


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentPizza.getToppings());
        listview = (ListView) findViewById(R.id.listViewToppings);
        listview.setAdapter(adapter);

    }


    private void setCrust(){
        crustText.setText("Crust: " + MainActivity.currentPizza.getCrust().toString());
    }

    public static void setPrice(){
        if(smallButton.isChecked())
            MainActivity.currentPizza.setSize("SMALL");
        else if(mediumButton.isChecked())
            MainActivity.currentPizza.setSize("MEDIUM");
        else if(largeButton.isChecked())
            MainActivity.currentPizza.setSize("LARGE");
        pizzaPrice.setText("");
        pizzaPrice.setText("" + MainActivity.currentPizza.price());
    }


//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//        // nothing XD
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        //hi(adapterView, view, i, l);
//    }

}