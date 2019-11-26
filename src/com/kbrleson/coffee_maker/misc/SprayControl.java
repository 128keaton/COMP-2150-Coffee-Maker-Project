package com.kbrleson.coffee_maker.misc;

import com.kbrleson.coffee_maker.enums.BrewStrength;
import com.kbrleson.coffee_maker.interfaces.LogicalValveInterface;

public class SprayControl implements LogicalValveInterface {
    private boolean isSpraying = false;
    private BrewStrength strength = BrewStrength.Light;

    public SprayControl(BrewStrength strength) {
        this.strength = strength;
    }

    public SprayControl() {
    }

    public void setStrength(BrewStrength strength) {
        this.strength = strength;
    }

    @Override
    public void openValve() {
        this.isSpraying = true;
        this.printStatus();
    }

    @Override
    public void closeValve() {
        this.isSpraying = false;
        this.printStatus();
    }

    @Override
    public boolean isValveOpen() {
        return this.isSpraying;
    }

    private void printStatus() {
        if (this.isSpraying) {
            System.out.println("Spray Valve: Open");
            System.out.println("Spray Valve Strength: " + this.strength);
        } else {
            System.out.println("Spray Valve: Closed");
        }
    }
}
