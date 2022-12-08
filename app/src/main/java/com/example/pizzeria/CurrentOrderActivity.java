package com.example.pizzeria;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrderActivity extends AppCompatActivity {
    private TextView orderID;
    private TextView subTotal;
    private TextView salesTax;
    private TextView orderTotal;
    private ListView pizzaList;
    private ArrayAdapter<String> adapter;
    private Order currentPizzaOrder;
    private StoreOrders currentStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Current Order");

        orderID = findViewById(R.id.current_order_number);
        subTotal = findViewById(R.id.current_subtotal);
        salesTax = findViewById(R.id.current_sales_tax);
        orderTotal = findViewById(R.id.current_order_total);
        pizzaList = findViewById(R.id.pizza_orders_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                MainActivity.currentPizzaOrder.getPizzaOrders());
        pizzaList.setAdapter(adapter);
        pizzaList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        currentPizzaOrder = MainActivity.currentPizzaOrder;
        currentStoreOrders = MainActivity.currentStoreOrders;

        setSalesTax();
        setOrderID();
        setOrderTotal();
        setSubTotal();
    }

    /**
     * Displays the subtotal of the order.
     */
    private void setSubTotal() {
        currentPizzaOrder.setSubTotal();
        subTotal.setText("" + String.format("%.2f", currentPizzaOrder.getSubTotal()));
    }

    /**
     * Displays the sales tax on the order.
     */
    private void setSalesTax(){
        currentPizzaOrder.setSalesTax();
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
        currentPizzaOrder.setOrderID(orderNumber);
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