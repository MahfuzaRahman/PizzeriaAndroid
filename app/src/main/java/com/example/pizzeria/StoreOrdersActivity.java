package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    private Spinner orderNumbers;
    private TextView orderTotal;
    private ListView ordersList;
    private Button clearOrder;
    private ArrayAdapter<String> adapter;
    private ArrayList<Integer> orderIDs;
    private Order[] listasArray;
    private StoreOrders currentStoreOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Store Orders");

        currentStoreOrders = MainActivity.currentStoreOrders;
        orderIDs = new ArrayList<>();
        for(Order order: currentStoreOrders.getStoreOrders()){
            orderIDs.add(order.getOrderID());}
        orderNumbers = findViewById(R.id.order_number_spinner);
        orderTotal = findViewById(R.id.current_order_total);
        ordersList = findViewById(R.id.pizza_orders_list);
        clearOrder = findViewById(R.id.clear_order_btn);

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, orderIDs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumbers.setAdapter(dataAdapter);
        orderNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //hi(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        listasArray = (MainActivity.currentStoreOrders.getStoreOrders()).toArray(
                new String[MainActivity.currentStoreOrders.getStoreOrders().size()]);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, listasArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ordersList.setAdapter(adapter);
        ordersList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ordersList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //hi(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}