package com.kbrleson.coffeemaker.components.heaters;

public class PlateHeater implements GenericHeater {
    private boolean isHeating = false;

    @Override
    public void setHeating(boolean heating) {
        this.isHeating = heating;
        System.out.println("Plate Heater: " + (heating ? "On" : "Off"));
    }

    @Override
    public boolean isHeating() {
        return this.isHeating;
    }
}
