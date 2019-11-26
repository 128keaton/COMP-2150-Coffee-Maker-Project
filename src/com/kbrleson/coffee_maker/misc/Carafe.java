package com.kbrleson.coffee_maker.misc;

import com.kbrleson.coffee_maker.interfaces.FluidSensorInterface;

public class Carafe implements FluidSensorInterface {
    private double percentageFull = 0.0;

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
}
