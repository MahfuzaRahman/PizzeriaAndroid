package com.example.pizzeria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderDropDown;
    private TextView orderTotal;
    private ListView ordersList;
    private Button clearOrder;
    private ArrayAdapter<String> adapter;
    private Order[] listasArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Store Orders");

        orderDropDown = findViewById(R.id.order_number_spinner);
        orderTotal = findViewById(R.id.current_order_total);
        ordersList = findViewById(R.id.pizza_orders_list);
        clearOrder = findViewById(R.id.clear_order_btn);

        String[] array = (MainActivity.currentStoreOrders.getStoreOrders()).toArray(
                new String[MainActivity.currentStoreOrders.getStoreOrders().size()]);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, array);
        ordersList.setAdapter(adapter);
        ordersList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


}