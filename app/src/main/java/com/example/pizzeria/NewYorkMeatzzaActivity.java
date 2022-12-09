package com.example.pizzeria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewYorkMeatzzaActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_new_york_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New York Meatzza Pizza");

        pizzaFactory = new NYPizza();
        currentPizza = pizzaFactory.createMeatzza();
        currentPizzaOrder = MainActivity.currentPizzaOrder;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.new_york_size_selector);
        small = radioGroup.findViewById(R.id.new_york_small_btn);
        medium = radioGroup.findViewById(R.id.new_york_medium_btn);
        large = radioGroup.findViewById(R.id.new_york_large_btn);
        small.setChecked(true);

        pizzaPic = findViewById(R.id.new_york_pizza_image);
        pizzaPrice = findViewById(R.id.new_york_pizza_price);
        crust = findViewById(R.id.new_york_crust_label);
        flavor = findViewById(R.id.new_york_flavor);
        addToOrder = findViewById(R.id.new_york_add_to_order_btn);
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
                alert.setTitle("Add to order?");
                alert.setMessage(currentPizza.toString());
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        currentPizzaOrder.add(currentPizza);
                        reset();
                        Toast.makeText(view.getContext(),
                                "Pizza added! ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                "Pizza not added.", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                currentPizza.getToppings());
        toppings = (ListView) findViewById(R.id.new_york_toppings_list);
        toppings.setAdapter(adapter);
    }

    private void setCrust(){
        crust.setText("Crust: HAND TOSSED");
    }

    private void setFlavor(){
        flavor.setText("Meatzza");
        pizzaPic.setImageResource(R.drawable.ny_bbq_pizza);
    }

    private void setPrice(){
        if(small.isChecked())
            currentPizza.setSize("SMALL");
        else if(medium.isChecked())
            currentPizza.setSize("MEDIUM");
        else if(large.isChecked())
            currentPizza.setSize("LARGE");
        pizzaPrice.setText("");
        pizzaPrice.setText("$" + currentPizza.price());
    }

    private void reset(){
        small.setChecked(true);
        currentPizza = pizzaFactory.createBBQChicken();
        currentPizza.setSize("SMALL");
    }
}