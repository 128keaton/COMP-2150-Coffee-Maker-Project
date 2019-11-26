package com.kbrleson.coffee_maker.additives;

import com.kbrleson.coffee_maker.interfaces.AdditiveInterface;

public class MochaAdditive implements AdditiveInterface {
    @Override
    public double additiveCost() {
        return 1.0;
    }

    @Override
    public String additiveName() {
        return "Mocha";
    }
}
