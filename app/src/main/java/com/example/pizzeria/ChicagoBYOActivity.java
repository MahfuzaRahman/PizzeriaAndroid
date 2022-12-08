package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class ChicagoBYOActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener {
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
    private StoreOrders currentStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_layout);

        availableToppings = Topping.STRINGS;
        selectedToppings = new ArrayList<String>();

        pizzaFactory = new ChicagoPizza();
        currentPizza = pizzaFactory.createBuildYourOwn();

        currentPizzaOrder = MainActivity.currentPizzaOrder;
        currentStoreOrders = MainActivity.currentStoreOrders;

        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sizeSelection);
        small = radioGroup.findViewById(R.id.smallButton);
        medium = radioGroup.findViewById(R.id.mediumButton);
        large = radioGroup.findViewById(R.id.largeButton);
        small.setChecked(true);

        pizzaPrice = findViewById(R.id.pizzaPrice);
        crust = findViewById(R.id.crustText);
        setPrice();
        setCrust();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                setPrice();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                availableToppings);
        toppingsAvailable = (ListView) findViewById(R.id.toppingsAvailable);
        toppingsAvailable.setAdapter(adapter);
        toppingsAvailable.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                selectedToppings);
        toppingsSelected = (ListView) findViewById(R.id.toppingsSelected);
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
    }

    private void addTopping(String toppingName){
        if(availableToppings.contains(toppingName)){
            availableToppings.remove(toppingName);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                    availableToppings);
            toppingsAvailable = (ListView) findViewById(R.id.toppingsAvailable);
            toppingsAvailable.setAdapter(adapter);
        }
        selectedToppings.add(toppingName);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                selectedToppings);
        toppingsSelected = (ListView) findViewById(R.id.toppingsSelected);
        toppingsSelected.setAdapter(adapter);
        setPrice();
        checkNumberOfToppings();
    }

    private void removeTopping(String toppingName){
        if(selectedToppings.contains(toppingName)){
            selectedToppings.remove(toppingName);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                    selectedToppings);
            toppingsSelected = (ListView) findViewById(R.id.toppingsSelected);
            toppingsSelected.setAdapter(adapter);
        }
        availableToppings.add(toppingName);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                availableToppings);
        toppingsAvailable = (ListView) findViewById(R.id.toppingsAvailable);
        toppingsAvailable.setAdapter(adapter);
        setPrice();
        checkNumberOfToppings();
    }

    private void setCrust(){
        crust.setText("Crust: " + currentPizza.getCrust().toString());
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

}