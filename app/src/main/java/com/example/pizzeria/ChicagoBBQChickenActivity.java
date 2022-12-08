package com.example.pizzeria;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;

public class ChicagoBBQChickenActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener {
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
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chicago BBQ Chicken Pizza");

        pizzaFactory = new ChicagoPizza();
        currentPizza = pizzaFactory.createBBQChicken();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.chicago_size_selector);
        small = radioGroup.findViewById(R.id.chicago_small_btn);
        medium = radioGroup.findViewById(R.id.chicago_medium_btn);
        large = radioGroup.findViewById(R.id.chicago_large_btn);
        small.setChecked(true);

        pizzaPic = findViewById(R.id.chicago_pizza_image);
        pizzaPrice = findViewById(R.id.chicago_pizza_price);
        crust = findViewById(R.id.chicago_crust_label);
        flavor = findViewById(R.id.chicago_flavor);
        addToOrder = findViewById(R.id.chicago_add_to_order_btn);
        setPrice();
        setCrust();
        setFlavor();

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("CURRENT ORDER");

        //Order currentPizzaOrder = (Order) getIntent().getExtras().getSerializable("CURRENT_ORDER");

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
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = getIntent();
//                            Order order = (Order) intent.getSerializableExtra("CURRENT ORDER");

                            order.add(currentPizza);
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            intent.putExtra("CURRENT ORDER", order);
                            startActivity(intent);
//                            order.add(currentPizza);
                            //Log.d("myapp", ""+MainActivity.currentPizzaOrder.getOrderSize());
                            reset();
                            Toast.makeText(view.getContext(),
                                    "Pizza added! " + MainActivity.currentPizzaOrder.getOrderSize() + "", Toast.LENGTH_SHORT).show();
                        }
                        //handle the "NO" click
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
        toppings = (ListView) findViewById(R.id.chicago_toppings_list);
        toppings.setAdapter(adapter);

    }

    private void setCrust(){
        crust.setText("Crust: PAN");
    }

    private void setFlavor(){
        flavor.setText("BBQ Chicken");
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
        pizzaPrice.setText("$" + currentPizza.price());
    }

    private void reset(){
        small.setChecked(true);
        currentPizza = pizzaFactory.createBBQChicken();
        currentPizza.setSize("SMALL");
    }
}