package com.example.pizzeria;


import java.io.Serializable;

/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author Lily Chang
 */
public class Item implements Serializable {
    private String itemName;
    private int image;

    /**
     * Parameterized constructor.
     * @param itemName
     * @param image
     */
    public Item(String itemName, int image) {
        this.itemName = itemName;
        this.image = image;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImage() {
        return image;
    }

}
