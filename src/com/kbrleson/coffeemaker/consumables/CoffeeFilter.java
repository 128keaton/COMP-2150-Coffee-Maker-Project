package com.kbrleson.coffeemaker.consumables;

public class CoffeeFilter implements Consumable {
    private boolean hasBeenUsed = false;
    private CoffeeGrinds coffeeGrinds;

    @Override
    public boolean isConsumed() {
        boolean totallyConsumed = this.hasBeenUsed;

        if (this.coffeeGrinds != null) {
            totallyConsumed = this.coffeeGrinds.isConsumed() && this.hasBeenUsed;
        }

        return totallyConsumed;
    }

    @Override
    public void setConsumed() {
        this.hasBeenUsed = true;

        if (this.coffeeGrinds != null) {
            this.coffeeGrinds.setConsumed();
        }
    }

    public void fill() {
        this.coffeeGrinds = new CoffeeGrinds();
    }

    public boolean isFilled() {
        return this.coffeeGrinds != null;
    }
}
