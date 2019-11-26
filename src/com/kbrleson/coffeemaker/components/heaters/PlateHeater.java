package com.kbrleson.coffeemaker.components.heaters;

public class PlateHeater implements GenericHeater {
    private boolean isHeating = false;

    @Override
    public void setHeating(boolean heating) {
        if (this.isHeating != heating) {
            this.isHeating = heating;
            this.printStatus();
        }
    }

    @Override
    public boolean isHeating() {
        return this.isHeating;
    }

    private void printStatus() {
        System.out.println("Plate Heater: " + (this.isHeating ? "On" : "Off"));
    }
}
