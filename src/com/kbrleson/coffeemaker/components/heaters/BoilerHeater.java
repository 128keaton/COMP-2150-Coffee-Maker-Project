package com.kbrleson.coffeemaker.components.heaters;

public class BoilerHeater implements GenericHeater {
    private boolean isHeating = false;

    @Override
    public void setHeating(boolean heating) {
        this.isHeating = heating;
        this.printStatus();
    }

    @Override
    public boolean isHeating() {
        return this.isHeating;
    }

    private void printStatus() {
        System.out.println("Boiler Heating Element: " + (this.isHeating ? "On" : "Off"));
    }
}
