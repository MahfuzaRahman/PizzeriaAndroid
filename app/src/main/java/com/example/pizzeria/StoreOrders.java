package com.example.pizzeria;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * StoreOrders defines manages all the orders placed at the PizzeRUa.
 * An order can be added to or removed from the store's orders. Additionally,
 * an order can be found using its order ID from the list of orders managed by
 * the PizzeRUa. All the store orders can also be exported to a file.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class StoreOrders implements Customizable, Serializable {
    private ArrayList<Order> storeOrders;
    private int nextOrderID;

    /**
     * Creates a store orders database with a list of orders and unique ID.
     */
    public StoreOrders() {
        storeOrders = new ArrayList<>();
        nextOrderID = 1;
    }

    /**
     * Gets the store orders.
     * @return the list of orders at the store.
     */
    public ArrayList<Order> getStoreOrders(){
        return storeOrders;
    }

    /**
     * Gets the store orders.
     * @return the list of orders at the store as an array.
     */
    public String[] getStoreOrderArray(){
        String[] listAsArray = new String[storeOrders.size()];
        int i = 0;
        for(Order k : storeOrders){
            listAsArray[i] = k.toString();
        }
        return listAsArray;
    }

    /**
     * Finds an order in the store's orders given an order ID.
     * Searches through the orders in the store's orders to find the matching
     * store order.
     * @param orderID the order ID of the order.
     * @return the order if found, null otherwise.
     */
    public Order findOrder(int orderID){
        for(Order order: storeOrders){
            if(order.getOrderID() == orderID)
                return order;
        }
        return null;
    }

    /**
     * Gets the next unique order ID.
     * @return the next unique order ID.
     */
    public int getNextOrderID(){
        return nextOrderID;
    }

    /**
     * Updates the next unique order ID.
     * Increments the next unique order ID by one.
     */
    private void updateNextOrderID(){
        nextOrderID++;
    }

    /**
     * Adds an order to the store's orders.
     * @param obj the object that must be added.
     * @return true if the order is added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order order =  (Order) obj;
            if(order.getOrderSize() == 0)
                return false;
            order.setOrderID(getNextOrderID());
            updateNextOrderID();
            storeOrders.add(order);
            return true;
        }
        return false;
    }

    /**
     * Removes an order from the store's orders.
     * @param obj the object that must be removed.
     * @return true if the order is removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order)
            return storeOrders.remove((Order) obj);
        return false;
    }

    /**
     * Exports the store orders to a text file.
     * Adds the order information of each order in the store's orders to the
     * Pizzerua_orders.txt" file.
     * @throws FileNotFoundException if the text file does not exist.
     */
    public void export() throws FileNotFoundException {
        File file = new File("Pizzerua_orders.txt");
        PrintWriter pw = new PrintWriter(file);
        for(Order o : storeOrders){
            pw.println(o.toString());
        }
        pw.close();
    }
}
