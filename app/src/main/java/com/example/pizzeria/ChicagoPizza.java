package com.example.pizzeria;

/**
 * ChicagoPizza defines a pizza factory that creates Chicago style pizzas.
 * A Chicago Pizza factory can create four flavors of pizzas: Deluxe, Meatzza,
 * BBQ Chicken, and Build Your Own.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * Creates a Deluxe flavored, Chicago style pizza.
     * Sets the Deluxe pizza crust that corresponds to a Chicago style, Deluxe
     * flavored pizza.
     * @return the Deluxe flavored, Chicago style pizza.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust("Deluxe","Chicago");
        return pizza;
    }

    /**
     * Creates a Meatzza flavored, Chicago style pizza.
     * Sets the Meatzza pizza crust that corresponds to a Chicago style,
     * Meatzza flavored pizza.
     * @return the Meatzza flavored, Chicago style pizza.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust("Meatzza","Chicago");
        return pizza;
    }

    /**
     * Creates a BBQ Chicken flavored, Chicago style pizza.
     * Sets the BBQ Chicken pizza crust that corresponds to a Chicago style,
     * BBQ Chicken flavored pizza.
     * @return the BBQ Chicken flavored, Chicago style pizza.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust("BBQ Chicken","Chicago");
        return pizza;
    }

    /**
     * Creates a Build Your Own flavored, Chicago style pizza.
     * Sets the Build Your Own pizza crust that corresponds to a Chicago
     * style, Build Your Own flavored pizza.
     * @return the Build Your Own flavored, Chicago style pizza.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust("Build Your Own","Chicago");
        return pizza;
    }
}
