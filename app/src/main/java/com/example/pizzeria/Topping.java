package com.example.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Topping defines the thirteen possible toppings for a pizza at PizzeRUa.
 * This includes sausage, pepperoni, green pepper, onion, mushroom, BBQ
 * chicken, provolone, cheddar, beef, ham, pineapple, jalapeno, and olive.
 * This enum can also find the corresponding Topping value.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public enum Topping {
    SAUSAGE(),
    PEPPERONI(),
    GREEN_PEPPER(),
    ONION(),
    MUSHROOM(),
    BBQ_CHICKEN(),
    PROVOLONE(),
    CHEDDAR(),
    BEEF(),
    HAM(),
    PINEAPPLE(),
    JALAPENO(),
    OLIVE();

    public static final ArrayList<String> STRINGS = new ArrayList<>(Arrays.asList("SAUSAGE",
            "PEPPERONI", "GREEN_PEPPER", "ONION","MUSHROOM", "BBQ_CHICKEN", "PROVOLONE",
            "CHEDDAR" ,"BEEF", "HAM", "PINEAPPLE", "JALAPENO", "OLIVE"));

    /**
     * Creates each Topping.
     */
    Topping() {}

    /**
     * Gets the Topping corresponding to the topping the customer would like.
     * @param item the name of the topping.
     * @return the Topping if found, null otherwise.
     */
    public static Topping getTopping(String item) {
        if(item.equalsIgnoreCase("sausage"))
            return SAUSAGE;
        if(item.equalsIgnoreCase("pepperoni"))
            return PEPPERONI;
        if(item.equalsIgnoreCase("green_pepper"))
            return GREEN_PEPPER;
        if(item.equalsIgnoreCase("onion"))
            return ONION;
        if(item.equalsIgnoreCase("mushroom"))
            return MUSHROOM;
        if(item.equalsIgnoreCase("bbq_chicken"))
            return BBQ_CHICKEN;
        if(item.equalsIgnoreCase("provolone"))
            return PROVOLONE;
        if(item.equalsIgnoreCase("cheddar"))
            return CHEDDAR;
        if(item.equalsIgnoreCase("beef"))
            return BEEF;
        if(item.equalsIgnoreCase("ham"))
            return HAM;
        if(item.equalsIgnoreCase("pineapple"))
            return PINEAPPLE;
        if(item.equalsIgnoreCase("jalapeno"))
            return JALAPENO;
        if(item.equalsIgnoreCase("olive"))
            return OLIVE;
        return null;
    }
}
