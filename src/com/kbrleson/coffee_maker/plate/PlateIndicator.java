package com.kbrleson.coffee_maker.plate;

import com.kbrleson.coffee_maker.interfaces.LitIndicatorInterface;

public class PlateIndicator implements LitIndicatorInterface {
    private boolean plateHeaterActive = false;

    @Override
    public boolean isIndicatorLightOn() {
        return this.plateHeaterActive;
    }

    @Override
    public void setOn() {
        this.plateHeaterActive = true;
        System.out.println("Plate Heater Light: On");
    }

    @Override
    public void setOff() {
        this.plateHeaterActive = false;
        System.out.println("Plate Heater Light: Off");
    }

}
