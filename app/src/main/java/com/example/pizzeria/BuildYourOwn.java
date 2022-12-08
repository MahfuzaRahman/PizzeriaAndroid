package com.example.pizzeria;

import android.util.Log;

/**
 * BuildYourOwn defines a pizza with a crust, a size, and a price.
 * A Build Your Own pizza does not have any toppings unless they are added by
 * the customer. Instead, toppings can be manually added and removed.
 * This class also defines the price of a Build Your Own pizza depending on
 * its size and how many toppings it has.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class BuildYourOwn extends Pizza {
    public static final int MAX_TOPPINGS = 7;
    public static final int PERCENTAGE = 100;
    public static final double TOPPING_PRICE = 1.59;

    /**
     * Creates a Build Your Own pizza with no toppings.
     * All Build Your Own pizzas are created with no toppings.
     */
    public BuildYourOwn() { }

    /**
     * Gets price of Build Your Own pizza based on its size and toppings.
     * For each topping on the pizza, $1.59 is added to the price.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        Log.d("Myapp", "gettingprice from byo");
        double price = getSize().getBuildYourOwn();
        price += TOPPING_PRICE * getToppings().size();
        price = (double) Math.round(price * PERCENTAGE) / PERCENTAGE;
        return price;
    }

    /**
     * Creates a string with the style, crust, toppings, size, and price.
     * Iterates through the toppings array to get the list of toppings. Then,
     * it finds the style, crust, and size of the pizza through its reference
     * type.
     * @return the Build Your Own pizza information.
     */
    @Override
    public String toString() {
        String toppingsList = "";
        for(String topping : getToppings())
            toppingsList += topping + ", ";
        return ("Build Your Own (" + getCrust().getStyle() + " Style - "
                + getCrust().name() + ") " + toppingsList + getSize().name()
                + ", " + price());
    }
}
