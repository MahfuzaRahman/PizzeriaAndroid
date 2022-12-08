package com.example.pizzeria;

/**
 * PizzaFactory defines a factory that creates pizzas of different flavors.
 * This allows Chicago style pizza and New York style pizza factories to
 * create pizzas of the same flavors.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public interface PizzaFactory {
    /**
     * Creates a Deluxe flavored pizza.
     * @return a pizza made in the Deluxe flavor.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza flavored pizza.
     * @return a pizza made in the Meatzza flavor.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken flavored pizza.
     * @return a pizza made in the BBQ Chicken flavor.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own flavored pizza.
     * @return a pizza made in the Build Your Own flavor.
     */
    Pizza createBuildYourOwn();
}
