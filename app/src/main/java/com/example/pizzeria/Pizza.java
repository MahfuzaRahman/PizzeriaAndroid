package com.example.pizzeria;

import android.util.Log;

import java.util.ArrayList;

/**
 * Pizza class defines a pizza with toppings, a crust, and a size.
 * For each pizza, toppings can be added or removed, a crust can be set, and
 * a size can be chosen.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Creates a pizza with toppings, a crust, and a size.
     */
    public Pizza(){
        toppings = new ArrayList<>();
        crust = null;
        size = null;
    }

    /**
     * Sets the size of the pizza.
     * @param size the size of the pizza.
     */
    public void setSize(String size) {
        this.size = Size.getSize(size);
    }

    /**
     * Gets the size of the pizza.
     * @return the size of the pizza.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the crust of the pizza based on its flavor and style.
     * @param flavor the flavor of the pizza.
     * @param style the style of the pizza.
     */
    public void setCrust(String flavor, String style){
        this.crust = Crust.getCrust(flavor, style);
    }

    /**
     * Gets the crust of the pizza.
     * @return the crust of the pizza.
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     * Adds a topping to the pizza.
     * @param obj the object that must be added.
     * @return true if the topping was added, false otherwise.
     */
    @Override
    public boolean add(Object obj){
        Log.d("My app", "in pizza");
        if(obj instanceof Topping)
            return toppings.add((Topping) obj);
        return false;
    }

    /**
     * Removes a topping from the pizza.
     * @param obj the object that must be removed.
     * @return true if the topping is removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj){
        if(obj instanceof Topping)
            return toppings.remove((Topping) obj);
        return false;
    }

    /**
     * Gets the toppings on the pizza.
     * @return the list of toppings on the pizza.
     */
    public ArrayList<String> getToppings(){
        ArrayList<String> toppingsList = new ArrayList<>();
        for(Topping topping : toppings)
            toppingsList.add(topping.name());
        return toppingsList;
    }

    /**
     * Creates a String with flavor, style, crust, toppings, size, price.
     * @return the pizza information.
     */
    @Override
    public String toString() {
        String toppingsList = "";
        for(Topping t : toppings)
            toppingsList += t.name() + ", ";
        return (getClass() + " (" + crust.getStyle() + " Style - "
                + crust.name() + ") " + toppingsList + getSize().name()
                + ", " + price());
    }

    /**
     * Checks if two pizzas are equal to each other.
     * Checks if the two pizzas are the same flavor instance, if they
     * have the same toppings, crust, and size.
     * @param obj the obj that must be compared for equality.
     * @return true if the pizzas are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(this instanceof BuildYourOwn){
            if(!(obj instanceof BuildYourOwn))
                return false;
            Pizza pizza = (Pizza) obj;
            return getToppings().containsAll(pizza.getToppings()) &&
                    pizza.getToppings().containsAll(getToppings()) &&
                    getCrust().equals(pizza.getCrust()) &&
                    getSize().equals(pizza.getSize());
        }
        if(this instanceof BBQChicken){
            if(!(obj instanceof BBQChicken))
                return false;
        }
        if(this instanceof Deluxe){
            if(!(obj instanceof Deluxe))
                return false;
        }
        if(this instanceof Meatzza) {
            if(!(obj instanceof Meatzza))
                return false;
        }
        Pizza pizza = (Pizza) obj;
        return getToppings().equals(pizza.getToppings()) &&
                getCrust().equals(pizza.getCrust()) &&
                getSize().equals(pizza.getSize());
    }

    /**
     * Gets the price of a pizza.
     * @return the price of the pizza.
     */
    public abstract double price();
}
