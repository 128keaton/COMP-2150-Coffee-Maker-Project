package com.kbrleson.coffee_maker.misc;

import com.kbrleson.coffee_maker.interfaces.LogicalValveInterface;

public class ReliefValve implements LogicalValveInterface {
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
