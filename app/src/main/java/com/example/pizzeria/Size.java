package com.example.pizzeria;

/**
 * Size defines three size types that may be selected by a customer.
 * A Size has prices of pizzas corresponding to different flavors. This enum
 * can find the price that corresponds to a given flavor and size.
 * @author Arunima Tripathy, Mahfuza Rahman
 */
public enum Size {
    SMALL(14.99, 13.99, 15.99, 8.99),
    MEDIUM(16.99, 15.99, 17.99, 10.99),
    LARGE(18.99, 17.99, 19.99, 12.99);

    private final double deluxe;
    private final double BBQChicken;
    private final double meatzza;
    private final double buildYourOwn;

    /**
     * Creates a Size with prices corresponding to flavors.
     * @param deluxe the price of a Deluxe flavored pizza.
     * @param BBQChicken the price of a BBQ Chicken flavored pizza.
     * @param meatzza the price of a Meatzza flavored pizza.
     * @param buildYourOwn the price of a Build Your Own flavored pizza.
     */
    Size(double deluxe, double BBQChicken, double meatzza,
         double buildYourOwn){
        this.deluxe = deluxe;
        this.BBQChicken = BBQChicken;
        this.meatzza = meatzza;
        this.buildYourOwn = buildYourOwn;
    }

    /**
     * Gets the corresponding Size containing prices of flavors given a size.
     * @param size the size of the pizza.
     * @return the corresponding Size if found, null otherwise.
     */
    public static Size getSize(String size) {
        if(size.equalsIgnoreCase("small"))
            return SMALL;
        if(size.equalsIgnoreCase("medium"))
            return MEDIUM;
        if(size.equalsIgnoreCase("large"))
            return LARGE;
        return null;
    }

    /**
     * Gets the price of a Deluxe flavored pizza for the size.
     * @return the price of a Deluxe flavored pizza.
     */
    public double getDeluxe(){
        return deluxe;
    }

    /**
     * Gets the price of a BBQ Chicken flavored pizza for the size.
     * @return the price of a BBQ Chicken flavored pizza.
     */
    public double getBBQChicken(){
        return BBQChicken;
    }

    /**
     * Gets the price of a Meatzza flavored pizza for the size.
     * @return the price of a Meatzza flavored pizza.
     */
    public double getMeatzza(){
        return meatzza;
    }

    /**
     * Gets the price of a Build Your Own flavored pizza for the size.
     * @return the price of a Build Your Own flavored pizza.
     */
    public double getBuildYourOwn(){
        return buildYourOwn;
    }
}
