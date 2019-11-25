package com.kbrleson.coffee_maker;

import com.kbrleson.coffee_maker.interfaces.FluidSensorInterface;

public class Carafe implements FluidSensorInterface {
    private double percentageFull = 0.0;

    @Override
    public boolean isFull() {
        return this.percentageFull >= 99.0;
    }

    @Override
    public boolean isEmpty() {
        return this.percentageFull <= 1.0;
    }

    @Override
    public double percentageFull() {
        return this.percentageFull;
    }
}
