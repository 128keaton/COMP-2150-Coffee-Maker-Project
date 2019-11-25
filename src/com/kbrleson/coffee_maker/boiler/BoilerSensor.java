package com.kbrleson.coffee_maker.boiler;

import com.kbrleson.coffee_maker.interfaces.LogicalSensorInterface;

public class BoilerSensor implements LogicalSensorInterface {
    private boolean isBoiling = false;

    @Override
    public boolean isTripped() {
        return this.isBoiling;
    }
}
