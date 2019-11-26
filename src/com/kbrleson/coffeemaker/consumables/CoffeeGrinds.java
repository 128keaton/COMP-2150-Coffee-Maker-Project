package com.kbrleson.coffeemaker.consumables;

public class CoffeeGrinds implements Consumable {
    private boolean hasBeenUsed = false;

    @Override
    public boolean isConsumed() {
        return this.hasBeenUsed;
    }

    @Override
    public void setConsumed() {
        this.hasBeenUsed = true;
    }
}
