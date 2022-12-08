package com.example.pizzeria;

/**
 * Meatzza defines a pizza with toppings, a crust, a size, and a price.
 * A Meatzza pizza has the toppings sausage, pepperoni, beef, and ham. This
 * class also defines the price of a Meatzza pizza depending on its size.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class Meatzza extends Pizza {
    /**
     * Creates a Meatzza pizza with its toppings.
     * All Meatzza pizzas are created with sausage, pepperoni, beef, and ham.
     */
    public Meatzza(){
        add(Topping.SAUSAGE);
        add(Topping.PEPPERONI);
        add(Topping.BEEF);
        add(Topping.HAM);
    }

    /**
     * Gets the price of the Meatzza pizza based on its size.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        return getSize().getMeatzza();
    }

    /**
     * Creates a String with the style, crust, toppings, size, and price.
     * Iterates through the toppings array to get the list of toppings. Then,
     * it finds the style, crust, and size of the pizza through its reference
     * type.
     * @return the Meatzza pizza information.
     */
    @Override
    public String toString() {
        String toppingsList = "";
        for(String topping : getToppings())
            toppingsList += topping + ", ";
        return ("Meatzza (" + getCrust().getStyle() + " Style - "
                + getCrust().name() + ") " + toppingsList + getSize().name()
                + ", " + price());
    }
}
