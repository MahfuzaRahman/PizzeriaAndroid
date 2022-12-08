package com.example.pizzeria;

/**
 * BBQChicken defines a pizza with toppings, a crust, a size, and a price.
 * A BBQ Chicken pizza has the toppings BBQ chicken, green pepper, provolone,
 * and cheddar. This class also defines the price of a BBQ chicken pizza
 * depending on its size.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class BBQChicken extends Pizza {
    /**
     * Creates a BBQ Chicken pizza with its toppings.
     * All BBQ Chicken pizzas are created with these BBQ chicken, green
     * pepper, provolone, and cheddar.
     */
    public BBQChicken(){
        add(Topping.BBQ_CHICKEN);
        add(Topping.GREEN_PEPPER);
        add(Topping.PROVOLONE);
        add(Topping.CHEDDAR);
    }

    /**
     * Gets the price of the BBQ Chicken pizza based on its size.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        return getSize().getBBQChicken();
    }

    /**
     * Creates a String with the style, crust, toppings, size, and price.
     * Iterates through the toppings array to get the list of toppings. Then,
     * it finds the style, crust, and size of the pizza through its reference
     * type.
     * @return the BBQ Chicken pizza information.
     */
    @Override
    public String toString() {
        String toppingsList = "";
        for(String topping : getToppings())
            toppingsList += topping + ", ";
        return ("BBQ Chicken (" + getCrust().getStyle() + " Style - "
                + getCrust().name() + ") " + toppingsList + getSize().name()
                + ", " + price());
    }
}
