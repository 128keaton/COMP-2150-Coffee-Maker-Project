package com.kbrleson.coffee_maker.plate;

import com.kbrleson.coffee_maker.interfaces.HeaterInterface;

public class PlateHeater implements HeaterInterface {
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
