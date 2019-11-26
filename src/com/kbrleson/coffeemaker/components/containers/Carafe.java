package com.kbrleson.coffeemaker.components.containers;

import com.kbrleson.coffeemaker.enums.BrewStrength;

public class Carafe implements LiquidContainer {
    private double percentageFull = 0.0;
    private BrewStrength brewStrength;

    public Carafe(BrewStrength brewStrength) {
        this.brewStrength = brewStrength;
    }

    public Carafe() {
        this.brewStrength = BrewStrength.Light;
    }

    @Override
    public boolean isFull() {
        return this.percentageFull >= 90.0;
    }

    @Override
    public boolean isEmpty() {
        return this.percentageFull <= 1.0;
    }

    @Override
    public double percentageFull() {
        return this.percentageFull;
    }

    public void addBrewedCoffee(double amount) {
        this.percentageFull += amount;
    }

    /**
     * Take a specified amount of brewed coffee from the carafe
     * @param amount Amount specified
     */
    public double takeBrewedCoffee(double amount) {
        if (this.percentageFull < amount) {
            double amountTaken = this.percentageFull;
            this.percentageFull = 0.0;

            return amountTaken;
        }

        this.percentageFull -= amount;

        return amount;
    }

    /**
     * Sets the carafe to be empty
     */
    public void setEmpty() {
        this.brewStrength = null;
        this.percentageFull = 0.0;
        System.out.println("Carafe Level: " + this.percentageFull + "%");
    }

    /**
     * Update the brew strength
     * @param brewStrength - New BrewStrength value
     */
    public void setBrewStrength(BrewStrength brewStrength) {
        this.brewStrength = brewStrength;
    }

    /**
     * Get the current brew strength
     * @return The current BrewStrength
     */
    public BrewStrength getBrewStrength() {
        return brewStrength;
    }
}
