package com.kbrleson.coffee_maker.plate;

import com.kbrleson.coffee_maker.containers.Carafe;
import com.kbrleson.coffee_maker.interfaces.LogicalSensorInterface;

public class Plate implements LogicalSensorInterface {
    private boolean carafeAvailable = true;
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
