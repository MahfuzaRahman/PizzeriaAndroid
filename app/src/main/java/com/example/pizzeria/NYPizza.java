package com.example.pizzeria;

/**
 * NYPizza defines a pizza factory that creates Chicago style pizzas.
 * A New York Pizza factory can create four flavors of pizzas: Deluxe,
 * Meatzza, BBQ Chicken, and Build Your Own.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class NYPizza implements PizzaFactory {
    /**
     * Creates a Deluxe flavored, New York style pizza.
     * Sets the Deluxe pizza crust that corresponds to a New York style,
     * Deluxe flavored pizza.
     * @return the Deluxe flavored, New York style pizza.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust("Deluxe","New York");
        return pizza;
    }

    /**
     * Creates a Meatzza flavored, New York style pizza.
     * Sets the Meatzza pizza crust that corresponds to a New York style,
     * Meatzza flavored pizza.
     * @return the Meatzza flavored, New York style pizza.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust("Meatzza","New York");
        return pizza;
    }

    /**
     * Creates a BBQ Chicken flavored, New York style pizza.
     * Sets the BBQ Chicken pizza crust that corresponds to a New York style,
     * BBQ Chicken flavored pizza.
     * @return the Deluxe flavored, New York style pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust("BBQ Chicken","New York");
        return pizza;
    }

    /**
     * Creates a Build Your Own flavored, New York style pizza.
     * Sets the Build Your Own pizza crust that corresponds to a New York
     * style, Build Your Own flavored pizza.
     * @return the Build Your Own flavored, New York style pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust("Build Your Own","New York");
        return pizza;
    }
}
