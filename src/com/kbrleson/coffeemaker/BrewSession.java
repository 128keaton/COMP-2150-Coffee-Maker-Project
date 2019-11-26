package com.kbrleson.coffeemaker;

import com.kbrleson.coffeemaker.components.containers.Boiler;
import com.kbrleson.coffeemaker.consumables.CoffeeFilter;
import com.kbrleson.coffeemaker.enums.BrewStatus;
import com.kbrleson.coffeemaker.components.containers.Carafe;
import com.kbrleson.coffeemaker.components.sensors.Plate;

public class BrewSession {
    private BrewStatus status = BrewStatus.Waiting;
    private double progress = 0.0;
    private final Boiler boiler;
    private Carafe carafe;
    private Plate plate;
    private CoffeeFilter coffeeFilter;

    public BrewSession(Boiler boiler, Carafe carafe, Plate plate, CoffeeFilter coffeeFilter) {
        this.boiler = boiler;
        this.carafe = carafe;
        this.plate = plate;
        this.coffeeFilter = coffeeFilter;
    }

    public BrewStatus getStatus() {
        return status;
    }

    public boolean isPaused() {
        return this.status == BrewStatus.Paused;
    }

    public boolean isFinished() {
        return this.status == BrewStatus.Finished;
    }

    public boolean isBrewing() {
        return this.status == BrewStatus.Brewing;
    }

    public boolean isWaiting() {
        return this.status == BrewStatus.Waiting;
    }

    public void setBrewing() {
        this.status = BrewStatus.Brewing;
        this.printStatus();

        for (double progress = 10.0; progress <= 100; progress += 10.0) {
            double liquid = boiler.takeWater(10.0);
            updateProgress(progress);
            carafe.addBrewedCoffee(liquid);
            plate.carafeWasUpdated(carafe);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setPaused() {
        this.status = BrewStatus.Paused;
        this.printStatus();
    }

    public void setFinished() {
        this.status = BrewStatus.Finished;
        this.printStatus();

        if (this.coffeeFilter != null) {
            this.coffeeFilter.setConsumed();
        }
    }

    private void printStatus() {
        System.out.println("Brew Status: " + this.status);
    }

    public void updateProgress(double progress) {
        this.progress = progress;

        if (this.progress >= 100.0) {
            this.progress = 100.0;
            this.setFinished();
        }

        System.out.println("Brew Progress: " + this.progress + "%");
    }
}