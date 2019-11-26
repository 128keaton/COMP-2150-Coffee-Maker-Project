package com.kbrleson.coffeemaker.components.indicators;

public class PlateIndicator implements GenericIndicator {
    private boolean plateHeaterActive = false;

    @Override
    public boolean isIndicatorLit() {
        return this.plateHeaterActive;
    }

    @Override
    public void setOn() {
       if (!this.plateHeaterActive) {
           this.plateHeaterActive = true;
           System.out.println("Plate Heater Light: On");
       }
    }

    @Override
    public void setOff() {
      if (this.plateHeaterActive) {
          this.plateHeaterActive = false;
          System.out.println("Plate Heater Light: Off");
      }
    }

}
