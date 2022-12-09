package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class NewYorkBYOActivity extends AppCompatActivity {
    private RadioButton small;
    private RadioButton medium;
    private RadioButton large;
    private ImageView pizzaPic;
    private TextView pizzaPrice;
    private TextView crust;
    private TextView flavor;
    private ListView toppingsAvailable;
    private ListView toppingsSelected;
    private Button addButton;
    private Button removeButton;
    private Button addToOrder;
    private ArrayAdapter<String> adapter;
    private Pizza currentPizza;
    private PizzaFactory pizzaFactory;
    private ArrayList<String> availableToppings;
    private ArrayList<String> selectedToppings;
    private Order currentPizzaOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_byo_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New York Build Your Own Pizza");

        availableToppings = Topping.STRINGS;
        selectedToppings = new ArrayList<String>();

        pizzaFactory = new NYPizza();
        currentPizza = pizzaFactory.createBuildYourOwn();
        currentPizzaOrder = MainActivity.currentPizzaOrder;
        pizzaPic = findViewById(R.id.new_york_byo_pizza_image);
        flavor = findViewById(R.id.new_york_byo_flavor);
        addButton = findViewById(R.id.ny_add_topping_btn);
        removeButton = findViewById(R.id.ny_remove_topping_btn);
        addToOrder = findViewById(R.id.new_york_byo_add_to_order_btn);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.new_york_byo_size_selector);
        small = radioGroup.findViewById(R.id.new_york_byo_small_btn);
        medium = radioGroup.findViewById(R.id.new_york_byo_medium_btn);
        large = radioGroup.findViewById(R.id.new_york_byo_large_btn);
        small.setChecked(true);

        pizzaPrice = findViewById(R.id.new_york_byo_pizza_price);
        crust = findViewById(R.id.new_york_byo_crust);
        setPrice();
        setCrust();
        setFlavor();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setPrice();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                availableToppings);
        toppingsAvailable = (ListView) findViewById(R.id.new_york_byo_available_toppings);
        toppingsAvailable.setAdapter(adapter);
        toppingsAvailable.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                selectedToppings);
        toppingsSelected = (ListView) findViewById(R.id.ny_selected_topping);
        toppingsSelected.setAdapter(adapter);
        toppingsSelected.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toppingsAvailable.getCheckedItemCount() == 0){
                    Log.d("myapp", "alert bruh");
                }
                else{
                    int position = toppingsAvailable.getCheckedItemPosition();
                    String toppingName = toppingsAvailable.getItemAtPosition(position).toString();
                    Topping topping = Topping.getTopping(toppingName);
                    currentPizza.add(topping);
                    addTopping(toppingName);
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toppingsSelected.getCheckedItemCount() == 0){
                    Log.d("myapp", "alert bruh");
                }
                else{
                    int position = toppingsSelected.getCheckedItemPosition();
                    String toppingName = toppingsSelected.getItemAtPosition(position).toString();
                    Topping topping = Topping.getTopping(toppingName);
                    currentPizza.remove(topping);
                    removeTopping(toppingName);
                }
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
                        currentPizzaOrder.add(currentPizza);
                        Log.d("myapp", ""+currentPizzaOrder.getOrderSize());
                        reset();
                        Toast.makeText(view.getContext(),
                                "Pizza added! " + currentPizzaOrder.getOrderSize() + "", Toast.LENGTH_SHORT).show();
                        finish();
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
    }

    private void addTopping(String toppingName){
        if(availableToppings.contains(toppingName)){
            availableToppings.remove(toppingName);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                    availableToppings);
            toppingsAvailable = (ListView) findViewById(R.id.new_york_byo_available_toppings);
            toppingsAvailable.setAdapter(adapter);
        }
        selectedToppings.add(toppingName);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                selectedToppings);
        toppingsSelected = (ListView) findViewById(R.id.ny_selected_topping);
        toppingsSelected.setAdapter(adapter);
        setPrice();
        checkNumberOfToppings();
    }

    private void removeTopping(String toppingName){
        if(selectedToppings.contains(toppingName)){
            selectedToppings.remove(toppingName);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                    selectedToppings);
            toppingsSelected = (ListView) findViewById(R.id.ny_selected_topping);
            toppingsSelected.setAdapter(adapter);
        }
        availableToppings.add(toppingName);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                availableToppings);
        toppingsAvailable = (ListView) findViewById(R.id.new_york_byo_available_toppings);
        toppingsAvailable.setAdapter(adapter);
        setPrice();
        checkNumberOfToppings();
    }

    private void setCrust(){
        crust.setText("PAN");
    }

    private void setFlavor(){
        flavor.setText("BBQ Chicken");
        pizzaPic.setImageResource(R.drawable.ny_byo_pizza);
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

    public void checkNumberOfToppings(){
        if(selectedToppings.size() == 7){
            Log.d("myapp", "alert bruh");
            addButton.setEnabled(false);
        }
        else{
            addButton.setEnabled(true);
        }
    }

    private void reset(){
        small.setChecked(true);
        currentPizza = pizzaFactory.createBuildYourOwn();
        currentPizza.setSize("SMALL");

        availableToppings = Topping.STRINGS;
        selectedToppings = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                availableToppings);
        toppingsAvailable = (ListView) findViewById(R.id.new_york_byo_available_toppings);
        toppingsAvailable.setAdapter(adapter);
        toppingsAvailable.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                selectedToppings);
        toppingsSelected = (ListView) findViewById(R.id.ny_selected_topping);
        toppingsSelected.setAdapter(adapter);
        toppingsSelected.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

}