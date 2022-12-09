package com.example.pizzeria;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 *  
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private TextView orderID;
    private TextView subTotal;
    private TextView salesTax;
    private TextView orderTotal;
    private ListView pizzaList;
    private Button clearOrder;
    private Button placeOrder;
    private ArrayAdapter<String> adapter;
    private Order currentPizzaOrder;
    private StoreOrders currentStoreOrders;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Current Order");
        clearOrder = findViewById(R.id.clear_order_btn);
        placeOrder = findViewById(R.id.place_order_btn);
        orderID = findViewById(R.id.current_order_number);
        subTotal = findViewById(R.id.current_subtotal);
        salesTax = findViewById(R.id.current_sales_tax);
        orderTotal = findViewById(R.id.current_order_total);
        currentPizzaOrder = MainActivity.currentPizzaOrder;
        currentStoreOrders = MainActivity.currentStoreOrders;
        generatePizzaOrders();
        clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllOrders();
            }
        });
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order newOrder = cloneOrder((Order) MainActivity.currentPizzaOrder);
                MainActivity.currentStoreOrders.add(newOrder);
                clearOrderPlaced();
            }
        });
        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
    }

    /**
     * Generates the list view of the pizza orders in the current order at the pizzeria.
     * Populates the current order view with every additional pizza order placed via the
     * MainActivity.
     */
    private void generatePizzaOrders(){
        pizzaList = findViewById(R.id.pizza_orders_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentPizzaOrder.getPizzaOrders());
        pizzaList.setAdapter(adapter);
        pizzaList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        checkNumberOfOrders();
        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p = (String) pizzaList.getItemAtPosition(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Remove Action");
                alert.setMessage("Remove pizza from this order?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeAnOrder(p);
                        checkNumberOfOrders();
                        Toast.makeText(view.getContext(),
                                "Pizza removed from order.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                "Pizza not removed from order.", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Creates a copy of an order.
     * When an order is added to the store's orders, each order must be
     * distinct from one other.
     * @param order the order that must be copied to be placed.
     * @return the copied order.
     */
    private Order cloneOrder(Order order) {
        Order clonedOrder = new Order();
        for(String pizza: order.getPizzaOrders()){
            Pizza pizzaOrder = order.findPizza(pizza);
            clonedOrder.add(pizzaOrder);
        }
        clonedOrder.setSubTotal();
        clonedOrder.setSalesTax();
        clonedOrder.setOrderID(order.getOrderID());
        return clonedOrder;
    }

    /**
     * Clears the Current Order window of a order that was just placed.
     * The sales tax, order ID, order total, and subtotal are all reset.
     */
    private void clearOrderPlaced(){
        int value = 0;
        value = ((StoreOrders)MainActivity.currentStoreOrders).getNextOrderID();
        orderID.setText(""+ value);
        MainActivity.currentPizzaOrder.clearOrder();
        MainActivity.currentPizzaOrder.setOrderID(value);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentPizzaOrder.getPizzaOrders());
        pizzaList.setAdapter(adapter);
        clearOrder.setEnabled(false);
        placeOrder.setEnabled(false);
        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
    }

    /**
     * Clears the current order of all the pizzas placed in the order.
     * Resets the sales tax, order ID, order total, and subtotal of the order.
     */
    private void clearAllOrders(){
        MainActivity.currentPizzaOrder.clearOrder();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentPizzaOrder.getPizzaOrders());
        pizzaList.setAdapter(adapter);
        clearOrder.setEnabled(false);
        placeOrder.setEnabled(false);
        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
    }

    /**
     * Checks the number of pizza orders in the current pizza order.
     * If there are no pizzas in the order, the app disables the ability to clear or place an
     * order.
     */
    private void checkNumberOfOrders(){
        //if number of orders is 0, clear order button is disabled.
        if(MainActivity.currentPizzaOrder.getOrderSize() == 0){
            clearOrder.setEnabled(false);
            placeOrder.setEnabled(false);
        }else{
            clearOrder.setEnabled(true);
            placeOrder.setEnabled(true);
        }
    }

    /**
     * Removes a pizza from the current order at the pizzeria.
     * @param toString the String format of the pizza to be removed.
     */
    private void removeAnOrder(String toString){
        Pizza pizza = MainActivity.currentPizzaOrder.findPizza(toString);
        MainActivity.currentPizzaOrder.remove(pizza);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentPizzaOrder.getPizzaOrders());
        pizzaList.setAdapter(adapter);
        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
    }

    /**
     * Displays the subtotal of the order.
     */
    private void setSubTotal() {
        MainActivity.currentPizzaOrder.setSubTotal();
        subTotal.setText("" + String.format("%.2f", currentPizzaOrder.getSubTotal()));
    }

    /**
     * Displays the sales tax on the order.
     */
    private void setSalesTax(){
        MainActivity.currentPizzaOrder.setSalesTax();
        salesTax.setText("" + String.format("%.2f", currentPizzaOrder.getSalesTax()));
    }

    /**
     * Displays the total price of the order.
     */
    private void setOrderTotal(){
        orderTotal.setText("" + String.format("%.2f", currentPizzaOrder.getOrderTotal()));
    }

    /**
     * Displays the orderID of the order.
     * Determines the orderID of the order based on all the store orders.
     */
    private void setOrderID(){
        int orderNumber = currentStoreOrders.getNextOrderID();
        MainActivity.currentPizzaOrder.setOrderID(orderNumber);
        orderID.setText("" + orderNumber);
    }

}