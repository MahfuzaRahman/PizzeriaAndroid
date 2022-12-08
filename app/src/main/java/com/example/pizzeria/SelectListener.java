package com.example.pizzeria;

import android.os.Bundle;

public interface SelectListener {

    void onStop(Bundle savedInstanceState);

    void onPause(Bundle savedInstanceState);

    void onResume(Bundle savedInstanceState);

    void onItemClicked(Item i);

}