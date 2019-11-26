package com.kbrleson.coffee_maker.misc;

import com.kbrleson.coffee_maker.enums.SprayStrength;
import com.kbrleson.coffee_maker.interfaces.LogicalValveInterface;

public class SprayControl implements LogicalValveInterface {
    private boolean isSpraying = false;
    private SprayStrength strength = SprayStrength.Light;

    public SprayControl(SprayStrength strength) {
        this.strength = strength;
    }

    public SprayControl() {
    }

    public void setStrength(SprayStrength strength) {
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
