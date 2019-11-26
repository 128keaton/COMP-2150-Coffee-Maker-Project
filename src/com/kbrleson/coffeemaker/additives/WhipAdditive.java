package com.kbrleson.coffeemaker.additives;

public class WhipAdditive implements CoffeeAdditive {
    @Override
    public double additiveCost() {
        return 1.25;
    }

    @Override
    public String additiveName() {
        return "Whip";
    }
}
