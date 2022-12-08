package com.example.pizzeria;

/**
 * Customizable defines an interface with adding and removing capabilities.
 * All classes that implement Customizable must define its methods.
 * @author Mahfuza Rahman, Arunima Tripathy
 */
public interface Customizable{
    /**
     * Abstract method that adds an object to an entity.
     * @param obj the object that must be added.
     * @return true if added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Abstract method that removes an object from an entity.
     * @param obj the object that must be removed.
     * @return true if removed, false otherwise.
     */
    boolean remove(Object obj);
}
