package com.kbrleson.coffeemaker.components.indicators;

public class PlateIndicator implements GenericIndicator {
    private boolean plateHeaterActive = false;

    @Override
    public boolean isIndicatorLit() {
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
