package com.kbrleson.coffee_maker.boiler;

import com.kbrleson.coffee_maker.interfaces.FluidSensorInterface;

public class Boiler implements FluidSensorInterface {
    private double percentageFull = 0.0;
    private BoilerHeater heater;

    public Boiler() {
        this.heater = new BoilerHeater();
    }

    public void startBoiling() {
        if (this.heater.isHeating()) {
            return;
        }

        this.heater.setHeating(true);
    }

    public void fillWater() {
       this.percentageFull = 100.0;
       System.out.println("Boiler: FULL");
    }

    public double takeWater(double amountToTake) {
        this.percentageFull -= amountToTake;

        if (this.isEmpty()) {
            this.heater.setHeating(false);
        }

        return amountToTake;
    }

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
