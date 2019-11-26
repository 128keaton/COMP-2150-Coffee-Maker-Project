package com.kbrleson.coffeemaker.additives;

public class MochaAdditive implements CoffeeAdditive {
    @Override
    public double additiveCost() {
        return 1.0;
    }

    @Override
    public String additiveName() {
        return "Mocha";
    }
}
