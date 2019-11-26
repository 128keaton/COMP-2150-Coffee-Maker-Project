package com.kbrleson.coffeemaker.additives;

public class CinnamonAdditive implements CoffeeAdditive {
    @Override
    public double additiveCost() {
        return 0.50;
    }

    @Override
    public String additiveName() {
        return "Cinnamon";
    }
}
