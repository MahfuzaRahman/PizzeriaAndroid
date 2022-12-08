package com.example.pizzeria;

import android.os.Bundle;

public interface SelectListener {
    void onPause(Bundle savedInstanceState);

    void onItemClicked(Item i);
}