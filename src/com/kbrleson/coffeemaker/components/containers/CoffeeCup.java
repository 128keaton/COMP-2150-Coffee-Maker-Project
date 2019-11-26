package com.kbrleson.coffeemaker.components.containers;

import com.kbrleson.coffeemaker.additives.CoffeeAdditive;
import com.kbrleson.coffeemaker.enums.BrewStrength;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CoffeeCup implements LiquidContainer {
    // This represents 15% of a carafe.
    private final static double CAPACITY = 15.0;
    private double percentageFull = 0.0;
    private ArrayList<CoffeeAdditive> additives = new ArrayList<>();
    private BrewStrength strength;

    /**
     * Fill the cup to the capacity from the carafe
     * @param carafe - Carafe containing coffee
     */
    public void fillFrom(Carafe carafe) {
        double coffeeFilled =  carafe.takeBrewedCoffee(CAPACITY);
        this.strength = carafe.getBrewStrength();
        this.percentageFull = (coffeeFilled / CAPACITY) * 100.0;

        System.out.println("Coffee Cup: " + this.percentageFull + "%");
    }

    /**
     * Add a coffee flavoring/additive
     * @param additive - Additive to add to the coffee
     */
    public void add(CoffeeAdditive additive) {
        this.additives.add(additive);
    }

    /**
     * Drink the specified amount of coffee from the cup
     * @param amount Amount specified
     */
    public void drink(double amount) {
        if (amount > this.percentageFull) {
            this.percentageFull = 0.0;
        }

        this.percentageFull -= amount;

        if (this.isEmpty()) {
            this.strength = null;
            this.additives = new ArrayList<>();
            System.out.println("The coffee cup is now empty");
        }
    }

    /**
     * Get the cost of total cup of coffee
     * @return - Double representing cost in USD
     */
    public double getCost() {
        double finalCost = 0.0;

        if (this.strength != null) {
            for (CoffeeAdditive additive : additives) {
                finalCost += additive.additiveCost();
            }

            finalCost += 1.10;

        } else {
            System.out.println("No coffee has been added to this cup.");
        }

        return finalCost;
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("\nCoffee Cup");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if (!this.isEmpty()) {
            output.append("\nStatus: ").append(this.percentageFull).append("% full");
            output.append("\nCoffee Strength: ").append(this.strength);

            for (CoffeeAdditive additive : this.additives) {
                output.append("\nAdditive: ").append(additive.additiveName());
            }

            output.append("\nTotal Cost: ").append(formatter.format(this.getCost()));
        } else {
            output.append("\nStatus: Empty");
        }

        return output.toString();
    }
}
