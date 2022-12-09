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
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            removeAnOrder(p);
                            checkNumberOfOrders();
                            Toast.makeText(view.getContext(),
                                    "Pizza removed from order.", Toast.LENGTH_SHORT).show();
                        }
                        //handle the "NO" click
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

        currentPizzaOrder = MainActivity.currentPizzaOrder;
        currentStoreOrders = MainActivity.currentStoreOrders;

        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
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
//
//    private void reset(){
//        orderID = mainController.getCurrentStoreOrders().getNextOrderID();
//        mainController.getCurrentPizzaOrder().setOrderID(orderID);
//        orderNumber.setText("" + orderID);
//    }

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

    /**
     * Clears the order and alerts the customer that their order was cleared.
     */
    public void clearOrder() {
        if(currentPizzaOrder.getOrderSize() == 0){
            //set alert
        }
        currentPizzaOrder.clearOrder();
        //pizzaOrders.getItems().clear();
        orderID.setText("");
        subTotal.setText("");
        salesTax.setText("");
        orderTotal.setText("");
//        Toast.makeText(view.getContext(),
//                holder.tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
        //
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Pizza Order");
//        alert.setContentText("Your order was cleared.");
//        alert.showAndWait();
    }

    /**
     * Places the order to the store orders.
     * If the order is empty, the customer is alerted that they cannot place
     * an empty order. Otherwise, the order is added to the store's orders.
     * @param event the event that indicates that the 'Place Order' button was
     *              clicked.
     */
//    @FXML
//    public void placeOrder(ActionEvent event) {
//        if(mainController.getCurrentPizzaOrder().getOrderSize() == 0){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Error");
//            alert.setContentText("You cannot place an empty order!");
//            alert.showAndWait();
//            return;
//        }
//        Order newOrder = cloneOrder(mainController.getCurrentPizzaOrder());
//        mainController.addOrderToStoreOrder(newOrder);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Store Order");
//        alert.setContentText("Your order has been placed!");
//        alert.showAndWait();
//        clearOrder();
//        setOrderID();
//    }


    /**
     * Clears the order.
     * If the order is clear, the customer is alerted that they cannot clear
     * an empty order. Otherwise, it removes all pizzas from the list of
     * pizzas in the order, the order number, the subtotal, the sales tax,
     * and the order total.
     */
//    private void clearOrder(){
//        mainController.getCurrentPizzaOrder().clearOrder();
//        pizzaOrders.getItems().clear();
//        orderNumber.clear();
//        subTotal.clear();
//        salesTax.clear();
//        orderTotal.clear();
//    }

    /**
     * Removes a pizza from the order.
     * If there are no pizzas in the order, the customer is alerted that there
     * are no pizzas to remove from the order. If no pizza is selected to
     * remove, the customer is alerted that they must select a pizza to
     * remove. Otherwise, the pizza is removed from the order, and the
     * subtotal, sales tax, and order total are adjusted.
     * @param event the event that indicates that the 'Remove Pizza' button
     *              was clicked.
     */
//    @FXML
//    public void removePizza(ActionEvent event) {
//        if(pizzaOrders.getItems().size() == 0){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText("Warning");
//            alert.setContentText("There are no pizzas to remove.");
//            alert.showAndWait();
//            return;
//        }
//        if(pizzaOrders.getSelectionModel().getSelectedItems().size() == 0 ||
//                pizza == null || pizza.equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Error");
//            alert.setContentText("Select a pizza to remove.");
//            alert.showAndWait();
//            return;
//        }
//
//        String currentPizza = pizza.substring(1, pizza.length() - 1);
//        Pizza pizzaOrder = mainController.getCurrentPizzaOrder().
//                findPizza(currentPizza);
//        mainController.getCurrentPizzaOrder().remove(pizzaOrder);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Pizza Order");
//        alert.setContentText("The pizza was removed from your order.");
//        alert.showAndWait();
//
//        pizzaOrders.getItems().remove(currentPizza);
//        setSubTotal();
//        setSalesTax();
//        setOrderTotal();
//    }

}