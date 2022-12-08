package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openChicago();
        openNewYork();
        openCurrentOrder();
        openPizzeria();
    }

    private void openChicago() {
        CardView chicagoButton = (CardView) findViewById(R.id.chicago_card);
        chicagoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChicagoPizzaActivity.class));
                Toast.makeText(getApplicationContext(), "Chicago Style Pizza", Toast.LENGTH_SHORT).show();
            }
        });
        // edited to add
    }

    private void openNewYork() {
        CardView chicagoButton = (CardView) findViewById(R.id.new_york_card);
        chicagoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewYorkPizzaActivity.class));
                Toast.makeText(getApplicationContext(), "New York Style Pizza", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openPizzeria() {
        CardView chicagoButton = (CardView) findViewById(R.id.pizzeria_card);
        chicagoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StoreOrdersActivity.class));
                Toast.makeText(getApplicationContext(), "Pizzeria Orders", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openCurrentOrder() {
        CardView chicagoButton = (CardView) findViewById(R.id.current_order_card);
        chicagoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class));
                Toast.makeText(getApplicationContext(), "Current Order", Toast.LENGTH_SHORT).show();
            }
        });
    }
}