package com.example.pizzeria;

import java.util.ArrayList;

/**
 * Order defines an order with pizzas and a unique orderID.
 * For each Order, there exists a subtotal which is the sum of all the prices
 * of each pizza in the order. There also exists a sales tax that is added to
 * the order.
 * @author Arunima Tripathy, Mahfuza Rahman
 */
public class Order implements Customizable {
    private ArrayList<Pizza> pizzaOrders;
    private int orderID;
    private double subTotal;
    private double salesTax;
    private static final double TAX_RATE = 6.625;
    private static final int PERCENTAGE = 100;

    /**
     * Creates an order with pizzas, orderID, subtotal, and sales tax.
     */
    public Order() {
        pizzaOrders = new ArrayList<>();
        orderID = 0;
        subTotal = 0;
        salesTax = 0;
    }

    /**
     * Gets the number of pizzas in the order.
     * @return the number of pizzas in the order.
     */
    public int getOrderSize() {
        return pizzaOrders.size();
    }

    /**
     * Sets the subtotal of the order.
     * For each pizza in the order, the subtotal is incremented by the price
     * of each pizza.
     */
    public void setSubTotal() {
        double total = 0;
        for(Pizza pizza : pizzaOrders)
            total += pizza.price();
        subTotal = total;
    }

    /**
     * Gets the subtotal of the order.
     * @return the subtotal of the order.
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * Sets the sales tax of the order.
     * Determines the sales tax based on the subtotal and tax rate.
     */
    public void setSalesTax() {
        salesTax = subTotal * (TAX_RATE/PERCENTAGE);
    }

    /**
     * Gets the sales tax of the order.
     * @return the sales tax of the order.
     */
    public double getSalesTax(){
        return salesTax;
    }

    /**
     * Gets the order total of the order.
     * @return the total order of the order.
     */
    public double getOrderTotal() {
        return (double) Math.round( ( subTotal + salesTax ) * PERCENTAGE)
                / PERCENTAGE;
    }

    /**
     * Sets the order ID of the order.
     * @param ID the ID of the order.
     */
    public void setOrderID(int ID){
        orderID = ID;
    }

    /**
     * Gets the order ID of the order.
     * @return the order ID of the order.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Adds a pizza to the order.
     * @param obj the object that must be added.
     * @return true if the pizza was added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Pizza) {
            if(pizzaOrders.add((Pizza) obj)){
                setSubTotal();
                setSalesTax();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a pizza from the order.
     * @param obj the object that must be removed.
     * @return true if the pizza was removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Pizza){
            if(pizzaOrders.remove((Pizza) obj)) {
                setSubTotal();
                setSalesTax();
                return true;
            }
        }
        return false;
    }

    /**
     * Clears the order of the pizzas, subtotal, and sales tax.
     * Removes all the pizzas in the list of pizzas and resets the subtotal
     * and sales tax once the pizzas have been removed.
     */
    public void clearOrder() {
        pizzaOrders.clear();
        setSubTotal();
        setSalesTax();
    }

    /**
     * Searches for a pizza in the order's list of pizzas.
     * Checks if any pizzas in the list of pizzas correspond to the given
     * pizza.
     * @param pizza the pizza that must be found in the list of pizzas.
     * @return corresponding Pizza if found, null otherwise.
     */
    public Pizza findPizza(String pizza){
        for(Pizza pizzaOrder: pizzaOrders){
            if(pizzaOrder.toString().equals(pizza))
                return pizzaOrder;
        }
        return null;
    }

    /**
     * Gets the pizzas in the order's list of pizzas.
     * @return the list of pizzas.
     */
    public String[] getPizzaOrders(){
        String[] pizzas = new String[pizzaOrders.size()];
        for(int i = 0; i < pizzas.length; i++)
            pizzas[i] = pizzaOrders.get(i).toString();
        return pizzas;
    }

    /**
     * Checks if two orders are the same by their unique order ID.
     * @param obj the obj that must be compared for equality.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Order)
            return orderID == ((Order) obj).getOrderID();
        return false;
    }

    /**
     * Creates a String with the order ID, pizzas, and order total.
     * @return the order information.
     */
    @Override
    public String toString(){
        String pizzas = "";
        for(String pizza: getPizzaOrders())
            pizzas += pizza + "\n";
        return "orderID: " + orderID + "\n" + pizzas +
                "Order Total: $" + getOrderTotal() + "\n";
    }
}
