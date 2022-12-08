package com.example.pizzeria;

/**
 * Deluxe defines a pizza with toppings, a crust, a size, and a price.
 * A Deluxe pizza has the toppings sausage, pepperoni, green pepper, onion,
 * and mushroom. This class also defines the price of a Deluxe pizza depending
 * on its size.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class Deluxe extends Pizza {
    /**
     * Creates a Deluxe pizza with its toppings.
     * All Deluxe pizzas are created with sausage, pepperoni, green pepper,
     * onion, and mushroom.
     */
    public Deluxe() {
        add(Topping.SAUSAGE);
        add(Topping.PEPPERONI);
        add(Topping.GREEN_PEPPER);
        add(Topping.ONION);
        add(Topping.MUSHROOM);
    }

    /**
     * Gets the price of the Deluxe pizza based on its size.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        return getSize().getDeluxe();
    }

    /**
     * Creates a String with the style, crust, toppings, size, and price.
     * Iterates through the toppings array to get the list of toppings. Then,
     * it finds the style, crust, and size of the pizza through its reference
     * type.
     * @return the Deluxe pizza information.
     */
    @Override
    public String toString() {
        String toppingsList = "";
        for(String topping : getToppings())
            toppingsList += topping + ", ";
        return ("Deluxe (" + getCrust().getStyle() + " Style - "
                + getCrust().name() + ") " + toppingsList + getSize().name()
                + ", " + price());
    }
}
