package com.kbrleson.coffee_maker.boiler;

import com.kbrleson.coffee_maker.interfaces.FluidSensorInterface;

public class Boiler implements FluidSensorInterface {
    private double percentageFull = 0.0;
    private BoilerHeater heater;
    private BoilerSensor sensor;

    public Boiler() {
        this.heater = new BoilerHeater();
        this.sensor = new BoilerSensor();
    }

    public void startBoiling() {
        if (this.heater.isHeating()) {
            System.out.println("Boiler Heating Element: ON");
            return;
        }

        this.heater.setHeating(true);
        System.out.println("Boiler Heating Element: ON");
    }

    public void stopBoiling() {
        this.heater.setHeating(false);
        System.out.println("Boiler Heating Element: OFF");
    }

    public void fillWater() {
        for (double i = 0.0; i <= 100.0; i++) {
            this.percentageFull += i;
            System.out.println("Adding water to boiler. " + this.percentageFull + "/100.0 full");
        }
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
