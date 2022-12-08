package com.example.pizzeria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewYorkPizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New York Style Pizza");
    }
}