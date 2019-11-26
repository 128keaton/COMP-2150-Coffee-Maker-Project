package com.kbrleson.coffeemaker.components.sensors;

import com.kbrleson.coffeemaker.components.containers.Carafe;
import com.kbrleson.coffeemaker.components.heaters.PlateHeater;
import com.kbrleson.coffeemaker.components.indicators.PlateIndicator;

public class Plate implements GenericLogicalSensor {
    private boolean carafeAvailable = false;
    private PlateHeater heater;
    private PlateIndicator indicator;

    public Plate() {
        this.heater = new PlateHeater();
        this.indicator = new PlateIndicator();
    }

    @Override
    public boolean isTripped() {
        return this.carafeAvailable;
    }

    public void removeCarafe() {
        this.carafeAvailable = false;
        this.heater.setHeating(false);
        this.indicator.setOff();
    }

    public void replaceCarafe(Carafe carafe) {
        this.carafeAvailable = true;

        this.heater.setHeating(!carafe.isEmpty());

        if (this.heater.isHeating()) {
            this.indicator.setOn();
        } else {
            this.indicator.setOff();
        }
    }

    public void carafeWasUpdated(Carafe carafe) {
        if (!carafe.isEmpty() && !this.heater.isHeating())  {
            this.heater.setHeating(true);
            this.indicator.setOn();
        }
    }
}
