package com.example.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Crust defines six crust types that are assigned based on flavor and style.
 * A Crust has a list of corresponding flavors and its corresponding style of
 * pizza, New York or Chicago. This enum can find the crust that corresponds
 * to a pizza given the flavor and style of it. Each Crust can also be used to
 * find the style of pizza it is made on.
 * @author Arunima Tripathy, Mahfuza Rahman
 */
public enum Crust {
    DEEP_DISH(new ArrayList<>(List.of("Deluxe")), "Chicago"),
    BROOKLYN(new ArrayList<>(List.of("Deluxe")),"New York"),
    PAN(new ArrayList<>(List.of("BBQ Chicken", "Build Your Own")),
            "Chicago"),
    THIN(new ArrayList<>(List.of("BBQ Chicken")), "New York"),
    STUFFED(new ArrayList<>(List.of("Meatzza")), "Chicago"),
    HAND_TOSSED(new ArrayList<>(List.of("Meatzza", "Build Your Own")),
            "New York");

    private final ArrayList<String> flavors;
    private final String style;

    /**
     * Creates a Crust with its flavors and style.
     * @param flavors a list of pizza flavors that can be made with the Crust.
     * @param style the style of pizza that can be made with the Crust.
     */
    Crust(ArrayList<String> flavors, String style) {
        this.flavors = flavors;
        this.style = style;
    }

    /**
     * Gets the Crust corresponding to a flavor and style of pizza.
     * Searches for a crust whose list of flavors contains the given flavor
     * and whose style matches the given style of pizza.
     * @param flavor the flavor of the pizza.
     * @param style the style of the pizza.
     * @return the corresponding Crust if found, null otherwise.
     */
    public static Crust getCrust(String flavor, String style) {
        for(Crust crust : Crust.values()) {
            if(crust.flavors.contains(flavor) &&
                    crust.style.equalsIgnoreCase(style)){
                return crust;
            }
        }
        return null;
    }

    /**
     * Gets the style of a Crust.
     * @return the style of a Crust.
     */
    public String getStyle() {
        return this.style;
    }

}
