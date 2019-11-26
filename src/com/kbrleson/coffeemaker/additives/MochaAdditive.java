package com.kbrleson.coffeemaker.additives;

public class MochaAdditive implements CoffeeAdditive {
    @Override
    public double additiveCost() {
        return 0.90;
    }

    @Override
    public String additiveName() {
        return "Mocha";
    }
}
