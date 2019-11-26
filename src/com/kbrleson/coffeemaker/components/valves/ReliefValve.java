package com.kbrleson.coffeemaker.components.valves;

public class ReliefValve implements GenericValve {
    private boolean isOpen = false;

    @Override
    public void openValve() {
        this.isOpen = true;
        System.out.println("Relief Valve: Open");
    }

    @Override
    public void closeValve() {
        this.isOpen = false;
        System.out.println("Relief Valve: Closed");
    }

    @Override
    public boolean isValveOpen() {
        return this.isOpen;
    }
}
