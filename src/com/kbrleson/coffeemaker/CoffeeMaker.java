package com.kbrleson.coffeemaker;

import com.kbrleson.coffeemaker.components.containers.Boiler;
import com.kbrleson.coffeemaker.components.containers.CoffeeCup;
import com.kbrleson.coffeemaker.consumables.CoffeeFilter;
import com.kbrleson.coffeemaker.enums.BrewStrength;
import com.kbrleson.coffeemaker.components.indicators.BrewIndicatorLight;
import com.kbrleson.coffeemaker.components.containers.Carafe;
import com.kbrleson.coffeemaker.components.valves.ReliefValve;
import com.kbrleson.coffeemaker.components.valves.SprayControlValve;
import com.kbrleson.coffeemaker.components.sensors.Plate;

public class CoffeeMaker {
    private Plate plate;
    private Carafe carafe;
    private Boiler boiler;
    private SprayControlValve sprayControlValve;
    private ReliefValve reliefValve;
    private BrewIndicatorLight brewIndicatorLight;
    private BrewSession brewSession;
    private CoffeeFilter coffeeFilter;

    public CoffeeMaker() {
        this.plate = new Plate();
        this.boiler = new Boiler();
        this.sprayControlValve = new SprayControlValve();
        this.reliefValve = new ReliefValve();
        this.brewIndicatorLight = new BrewIndicatorLight();
    }

    public void addCoffeeFilter() {
        this.coffeeFilter = new CoffeeFilter();
    }

    public void addCoffeeGrinds() {
        if (this.coffeeFilter != null) {
            this.coffeeFilter.fill();
        } else {
            System.out.println("There is no filter in place");
        }
    }

    public void resumeBrew() {
        if (canResumeBrew()) {
            this.printAction("Resuming Brew");
            this.brewIndicatorLight.setOn();
            this.reliefValve.closeValve();
            this.sprayControlValve.openValve();
            this.brewSession.setBrewing();
        }
    }

    public void pauseBrew() {
        if (this.brewSession != null && this.brewSession.isBrewing()) {
            this.printAction("Pausing Brew");

            this.brewSession.setPaused();
            this.brewIndicatorLight.setOff();
            this.reliefValve.openValve();
            this.sprayControlValve.closeValve();
        } else if (this.brewSession != null && this.brewSession.isPaused()) {
            // This handles a programmatic brew interrupt.
            if (this.brewIndicatorLight.isIndicatorLit()) {
                this.brewIndicatorLight.setOff();
            }

            if (!this.reliefValve.isValveOpen()) {
                this.reliefValve.openValve();
            }

            if (this.sprayControlValve.isValveOpen()) {
                this.sprayControlValve.closeValve();
            }
        } else {
            System.out.println("The coffee maker currently isn't brewing coffee");
        }
    }

    public void brew() {
        if (canBrew()) {
            if (this.brewSession == null) {
                this.brewSession = new BrewSession(boiler, carafe, plate, coffeeFilter);
            }

            this.printAction("Starting Brew");
            this.carafe.setBrewStrength(this.sprayControlValve.getStrength());
            this.brewIndicatorLight.setOn();
            this.boiler.startBoiling();
            this.sprayControlValve.openValve();
            this.brewSession.setBrewing();
        }
    }

    public void brewUntil(double percentage) {
        this.brewSession = new BrewSession(boiler, carafe, plate, coffeeFilter);
        this.brewSession.setInterruptAt(percentage);
        this.brew();
    }

    public void fillBoiler() {
        if (this.boiler.isEmpty()) {
            this.boiler.fillWater();
        } else {
            System.out.println("The boiler is already full");
        }
    }

    public void fillCoffeeCup(CoffeeCup cup) {
        if (this.carafe != null && !this.carafe.isEmpty()) {
            this.removeCarafe();
            this.printAction("Filling Cup");
            cup.fillFrom(this.carafe);
        } else if (this.carafe == null) {
            System.out.println("There is no carafe in the coffee maker");
        } else {
            System.out.println("The coffee maker's carafe contains no coffee");
        }
    }

    public void removeCarafe() {
       if (this.plate.isTripped()) {
           this.printAction("Removing Carafe");
           System.out.println("Carafe Level: " + this.carafe.percentageFull() + "%");

           this.plate.removeCarafe();
           if (this.brewSession != null && (this.brewSession.isBrewing() || this.brewSession.isPaused())) {
               this.pauseBrew();
           }
       }
    }

    public void addOrReplaceCarafe() {
        if (this.carafe == null) {
            this.printAction("Adding Carafe");
            this.carafe = new Carafe();
        } else {
            this.printAction("Replacing Carafe");
        }

        this.plate.replaceCarafe(this.carafe);
        System.out.println("Carafe Level: " + this.carafe.percentageFull() + "%");

        if (this.brewSession != null && this.brewSession.isPaused()) {
            this.resumeBrew();
        }
    }

    public void emptyCarafe() {
        this.removeCarafe();
        this.printAction("Emptying Carafe");
        this.carafe.setEmpty();
        this.addOrReplaceCarafe();
    }

    public void clean() {
        this.printAction("Cleaning Machine");
        if (this.coffeeFilter.isConsumed()) {
            this.coffeeFilter = null;
        }

        this.emptyCarafe();
    }

    public void setBrewStrength(BrewStrength strength) {
        this.printAction("Update Brew Strength");
        this.carafe.setBrewStrength(strength);
        this.sprayControlValve.setStrength(strength);
    }

    private boolean canResumeBrew() {
        if (this.brewSession != null && this.brewSession.isPaused() && this.plate.isTripped() && !this.carafe.isFull() && !this.boiler.isEmpty()) {
            return true;
        }

        if (this.brewSession == null) {
            this.printError("The coffee maker has not been brewing");
        } else if (this.brewSession.isBrewing()) {
            this.printError("The coffee maker is already brewing coffee");
        } else if (!this.plate.isTripped()) {
            this.printError("There is no carafe available to put coffee in");
        } else if (this.carafe.isFull()) {
            this.printError("The carafe is already full of coffee");
        } else if (this.boiler.isEmpty()) {
            this.printError("There is no water in the boiler");
        }

        return false;
    }

    private boolean canBrew() {
        if (this.plate.isTripped() && this.coffeeFilter != null && this.coffeeFilter.isFilled() && !this.isCoffeeFilterConsumed() && this.carafe.isEmpty() && this.boiler.isFull()) {
            return true;
        }

        if (this.coffeeFilter == null) {
            this.printError("Please add a coffee filter and fill with grinds before brewing");
        } else if (!this.coffeeFilter.isFilled()) {
            this.printError("Please fill the coffee filter with grinds before brewing");
        } else if (this.isCoffeeFilterConsumed()) {
            this.printError("The coffee filter needs to be replaced and filled first");
        } else if (!this.plate.isTripped()) {
            this.printError("There is no carafe available to put coffee in");
        } else if (!this.carafe.isEmpty()) {
            this.printError("The carafe is not empty");
        } else if (this.boiler.isEmpty()) {
            this.printError("There is no water in the boiler");
        }

        return false;
    }

    private boolean isCoffeeFilterConsumed() {
        if (this.coffeeFilter != null) {
            return this.coffeeFilter.isConsumed();
        }

        return false;
    }

    private void printAction(String action) {
        System.out.println("\n============================================");
        System.out.println("            " + action);
        System.out.println("============================================");
    }

    private void printError(String error) {
        System.out.println("\nERROR: " + error + "\n");
    }
}
