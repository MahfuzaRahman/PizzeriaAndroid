package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener{

    private RecyclerView recyclerViewPizza;
    private ArrayList<Item> items = new ArrayList<>();

//    public static PizzaFactory pizzaFactory;
//    public static Pizza currentPizza;

    public static Order currentPizzaOrder;
    public static StoreOrders currentStoreOrders;

    private int [] itemImages = {R.drawable.chicago_bbq_pizza, R.drawable.chicago_deluxe_pizza,
            R.drawable.chicago_meatzza_pizza, R.drawable.chicago_byo_pizza, R.drawable.ny_bbq_pizza,
            R.drawable.ny_deluxe_pizza, R.drawable.ny_meatzza_pizza, R.drawable.ny_byo_pizza};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentPizzaOrder = new Order();
        currentStoreOrders = new StoreOrders();

        recyclerViewPizza = findViewById(R.id.recyclerViewPizza);
        setupAvailableItems();
        ItemsAdapter adapter = new ItemsAdapter(this, items, this);
        recyclerViewPizza.setAdapter(adapter);
        recyclerViewPizza.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupAvailableItems(){

        //THIS iS HARDCODED
        String [] itemNames = {"Chicago BBQ Chicken Pizza", "Chicago Deluxe Pizza", "Chicago Meatzza Pizza",
                "Chicago BuildYourOwn Pizza", "NY BBQ Chicken Pizza", "NY Deluxe Pizza", "NY Meatzza Pizza",
                "NY BuildYourOwn Pizza"};

        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i]));
        }
    }


    private void openChicagoBBQChicken() {

        startActivity(new Intent(MainActivity.this, ChicagoBBQChickenActivity.class));

        // edited to add
    }

    private void openChicagoDeluxe() {

        startActivity(new Intent(MainActivity.this, ChicagoDeluxeActivity.class));

        // edited to add
    }

    private void openChicagoMeatzza() {

        startActivity(new Intent(MainActivity.this, ChicagoMeatzzaActivity.class));

        // edited to add
    }

    private void openChicagoBYO() {

        startActivity(new Intent(MainActivity.this, ChicagoBYOActivity.class));

        // edited to add
    }
//
//    private void openChicagoDeluxe() {
//        CardView chicagoButton = (CardView) findViewById(R.id.chicago_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ChicagoPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "Chicago Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // edited to add
//    }
//
//    private void openChicagoMeatzza() {
//        CardView chicagoButton = (CardView) findViewById(R.id.chicago_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ChicagoPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "Chicago Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // edited to add
//    }
//
//    private void openChicagoBYO() {
//        CardView chicagoButton = (CardView) findViewById(R.id.chicago_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ChicagoPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "Chicago Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // edited to add
//    }
//
//
//    private void openNewYorkBBQChicken() {
//        CardView chicagoButton = (CardView) findViewById(R.id.new_york_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewYorkPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "New York Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void openNewYorkDeluxe() {
//        CardView chicagoButton = (CardView) findViewById(R.id.new_york_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewYorkPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "New York Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void openNewYorkMeatzza() {
//        CardView chicagoButton = (CardView) findViewById(R.id.new_york_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewYorkPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "New York Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void openNewYorkBYO() {
//        CardView chicagoButton = (CardView) findViewById(R.id.new_york_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NewYorkPizzaActivity.class));
//                Toast.makeText(getApplicationContext(), "New York Style Pizza", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    private void openPizzeria() {
//        CardView chicagoButton = (CardView) findViewById(R.id.pizzeria_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, StoreOrdersActivity.class));
//                Toast.makeText(getApplicationContext(), "Pizzeria Orders", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void openCurrentOrder() {
//        CardView chicagoButton = (CardView) findViewById(R.id.current_order_card);
//        chicagoButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class));
//                Toast.makeText(getApplicationContext(), "Current Order", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public void onItemClicked(Item i) {
        Log.d("myapp", "hi");
        String name = i.getItemName().toString();

        if(name.equals("Chicago BBQ Chicken Pizza")){
            openChicagoBBQChicken();
        }
        else if(name.equals("Chicago Deluxe Pizza")){
            openChicagoDeluxe();
        }
        else if(name.equals("Chicago Meatzza Pizza")){
            openChicagoMeatzza();
        }
        else if(name.equals("Chicago BuildYourOwn Pizza")){
            openChicagoBYO();
        }
//        else if(name.equals("NY BBQ Chicken Pizza")){
//            openNewYorkBBQChicken();
//        }
//        else if(name.equals("NY Deluxe Pizza")){
//            openNewYorkDeluxe();
//        }
//        else if(name.equals("NY Meatzza Pizza")){
//            openNewYorkMeatzza();
//        }
//        else if(name.equals("NY BuildYourOwn Pizza")){
//            openNewYorkBYO();
//        }
    }




}
