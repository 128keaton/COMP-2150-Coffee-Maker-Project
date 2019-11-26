package com.kbrleson.coffeemaker;

import com.kbrleson.coffeemaker.components.containers.Boiler;
import com.kbrleson.coffeemaker.consumables.CoffeeFilter;
import com.kbrleson.coffeemaker.enums.BrewStatus;
import com.kbrleson.coffeemaker.components.containers.Carafe;
import com.kbrleson.coffeemaker.components.sensors.Plate;

class BrewSession {
    private BrewStatus status = BrewStatus.Waiting;
    private Double progress = 0.0;
    private Double progressHalt = null;
    private final Boiler boiler;
    private Carafe carafe;
    private Plate plate;
    private CoffeeFilter coffeeFilter;

    BrewSession(Boiler boiler, Carafe carafe, Plate plate, CoffeeFilter coffeeFilter) {
        this.boiler = boiler;
        this.carafe = carafe;
        this.plate = plate;
        this.coffeeFilter = coffeeFilter;
    }

    BrewStatus getStatus() {
        return status;
    }

    boolean isPaused() {
        return this.status == BrewStatus.Paused;
    }

    boolean isFinished() {
        return this.status == BrewStatus.Finished;
    }

    boolean isBrewing() {
        return this.status == BrewStatus.Brewing;
    }

    boolean isWaiting() {
        return this.status == BrewStatus.Waiting;
    }

    void setBrewing() {
        this.status = BrewStatus.Brewing;
        this.printStatus();
        double startProgress = this.progress != 0 ? this.progress : 0.0;
        double maxProgress = this.progressHalt != null ? this.progressHalt : 100.0;

        for (double currentProgress = startProgress; currentProgress <= maxProgress; currentProgress += 1.0) {
            double coffee = boiler.takeWater(1.0);

            this.carafe.addBrewedCoffee(coffee);
            this.plate.carafeWasUpdated(carafe);

            this.updateProgress(currentProgress);

            if (maxProgress < 100.0 && currentProgress == maxProgress) {
                System.out.println("Brew Paused at: " + currentProgress + "%");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void setInterruptAt(Double progressHalt) {
        if (progressHalt != null && progressHalt > 0.0) {
            this.progressHalt = progressHalt;
        } else {
            this.progressHalt = null;
        }
    }

    void setPaused() {
        this.progressHalt = null;
        this.status = BrewStatus.Paused;
        this.printStatus();
    }

    private void setFinished() {
        this.status = BrewStatus.Finished;
        this.printStatus();

        if (this.coffeeFilter != null) {
            this.coffeeFilter.setConsumed();
        }
    }

    private void printStatus() {
        System.out.println("Brew Status: " + this.status);
    }

    private void updateProgress(double progress) {
        this.progress = progress;

        if (this.progress >= 100.0) {
            this.progress = 100.0;
            this.setFinished();
        }

        if (progress > 0 && progress < 100.0 && progress % 10 == 0) {
            System.out.println("Brew Progress: " + this.progress + "%");
        }
    }
}
