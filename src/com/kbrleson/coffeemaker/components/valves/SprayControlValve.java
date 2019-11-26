package com.kbrleson.coffeemaker.components.valves;

import com.kbrleson.coffeemaker.enums.BrewStrength;

public class SprayControlValve implements GenericValve {
    private boolean isSpraying = false;
    private BrewStrength strength = BrewStrength.Light;

    public SprayControlValve(BrewStrength strength) {
        this.strength = strength;
    }

    public SprayControlValve() {
    }

    public void setStrength(BrewStrength strength) {
        this.strength = strength;
        System.out.println("Spray Valve Strength: " + this.strength);
    }

    public BrewStrength getStrength() {
        return strength;
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
