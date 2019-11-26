package com.kbrleson.coffee_maker.misc;

import com.kbrleson.coffee_maker.interfaces.LitIndicatorInterface;

public class BrewIndicatorLight implements LitIndicatorInterface {
    private boolean isBrewing = false;

    @Override
    public boolean isIndicatorLightOn() {
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
