package com.kbrleson.coffee_maker.additives;

import com.kbrleson.coffee_maker.interfaces.AdditiveInterface;

public class CinnamonAdditive implements AdditiveInterface {
    @Override
    public double additiveCost() {
        return 0.50;
    }

    @Override
    public String additiveName() {
        return "Cinnamon";
    }
}
