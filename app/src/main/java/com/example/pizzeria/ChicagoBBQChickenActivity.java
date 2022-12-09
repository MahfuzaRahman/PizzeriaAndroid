package com.example.pizzeria;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

/**
 * ChicagoBBQChickenActivity creates an activity that allows users to order BBQChicken flavored Pizza.
 *
 *
 */
public class ChicagoBBQChickenActivity extends AppCompatActivity {
    private RadioButton small;
    private RadioButton medium;
    private RadioButton large;
    private ImageView pizzaPic;
    private TextView pizzaPrice;
    private TextView crust;
    private TextView flavor;
    private ListView toppings;
    private Button addToOrder;
    private ArrayAdapter<String> adapter;
    private Pizza currentPizza;
    private PizzaFactory pizzaFactory;
    private Order currentPizzaOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chicagobbq_title));

        pizzaFactory = new ChicagoPizza();
        currentPizza = pizzaFactory.createBBQChicken();
        currentPizzaOrder = MainActivity.currentPizzaOrder;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.chicago_size_selector);
        small = radioGroup.findViewById(R.id.chicago_small_btn);
        small.setText(getString(R.string.small_text));
        medium = radioGroup.findViewById(R.id.chicago_medium_btn);
        medium.setText(getString(R.string.medium_text));
        large = radioGroup.findViewById(R.id.chicago_large_btn);
        large.setText(getString(R.string.large_text));
        small.setChecked(true);

        pizzaPic = findViewById(R.id.chicago_pizza_image);
        pizzaPrice = findViewById(R.id.chicago_pizza_price);
        crust = findViewById(R.id.chicago_crust_label);
        flavor = findViewById(R.id.chicago_flavor);
        addToOrder = findViewById(R.id.chicago_add_to_order_btn);
        setPrice();
        setCrust();
        setFlavor();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setPrice();
            }
        });

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle(getString(R.string.alert_add_to_order));
                alert.setMessage(currentPizza.toString());
                //handle the "YES" click
                alert.setPositiveButton(getString(R.string.yes_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        currentPizzaOrder.add(currentPizza);
                        reset();
                        finish();
                    }
                    //handle the "NO" click
                }).setNegativeButton(getString(R.string.no_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                getString(R.string.pizza_not_added), Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                currentPizza.getToppings());
        toppings = (ListView) findViewById(R.id.chicago_toppings_list);
        toppings.setAdapter(adapter);

    }

    private void setCrust(){
        crust.setText(getString(R.string.crust_pan));
    }

    private void setFlavor(){
        flavor.setText(getString(R.string.bbq_chicken));
        pizzaPic.setImageResource(R.drawable.chicago_bbq_pizza);
    }

    private void setPrice(){
        if(small.isChecked())
            currentPizza.setSize("SMALL");
        else if(medium.isChecked())
            currentPizza.setSize("MEDIUM");
        else if(large.isChecked())
            currentPizza.setSize("LARGE");
        pizzaPrice.setText("");
        pizzaPrice.setText(getString(R.string.currency_text)  + currentPizza.price());
    }

    private void reset(){
        small.setChecked(true);
        currentPizza = pizzaFactory.createBBQChicken();
        currentPizza.setSize("SMALL");
    }
}