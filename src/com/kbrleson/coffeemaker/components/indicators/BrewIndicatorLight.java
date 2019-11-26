package com.kbrleson.coffeemaker.components.indicators;

public class BrewIndicatorLight implements GenericIndicator {
    private boolean isBrewing = false;

    @Override
    public boolean isIndicatorLit() {
        return this.isBrewing;
    }

    @Override
    public void setOn() {
        this.isBrewing = true;
        System.out.println("Brew Indicator Light: On");
    }

    @Override
    public void setOff() {
        this.isBrewing = false;
        System.out.println("Brew Indicator Light: Off");
    }

}
