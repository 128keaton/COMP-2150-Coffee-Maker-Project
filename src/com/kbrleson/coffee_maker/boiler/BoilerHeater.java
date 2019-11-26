package com.kbrleson.coffee_maker.boiler;

import com.kbrleson.coffee_maker.interfaces.HeaterInterface;

public class BoilerHeater implements HeaterInterface {
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
