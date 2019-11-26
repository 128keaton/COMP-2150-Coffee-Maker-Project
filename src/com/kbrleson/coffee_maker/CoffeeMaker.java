package com.kbrleson.coffee_maker;

import com.kbrleson.coffee_maker.boiler.Boiler;
import com.kbrleson.coffee_maker.containers.CoffeeCup;
import com.kbrleson.coffee_maker.misc.BrewIndicatorLight;
import com.kbrleson.coffee_maker.containers.Carafe;
import com.kbrleson.coffee_maker.misc.ReliefValve;
import com.kbrleson.coffee_maker.misc.SprayControl;
import com.kbrleson.coffee_maker.plate.Plate;

public class CoffeeMaker {
    private Plate plate;
    private Carafe carafe;
    private Boiler boiler;
    private SprayControl sprayControl;
    private ReliefValve reliefValve;
    private BrewIndicatorLight brewIndicatorLight;
    private BrewSession brewSession;

    public CoffeeMaker() {
        this.plate = new Plate();
        this.carafe = new Carafe();
        this.boiler = new Boiler();
        this.sprayControl = new SprayControl();
        this.reliefValve = new ReliefValve();
        this.brewIndicatorLight = new BrewIndicatorLight();

        this.plate.replaceCarafe(this.carafe);
    }

    public void resumeBrew() {
        if (canResumeBrew()) {
            System.out.println("\n============================================");
            System.out.println("               Resuming Brew");
            System.out.println("============================================");
            this.brewSession.setBrewing();
            this.brewIndicatorLight.setOn();
        }
    }

    public void pauseBrew() {
        if (this.brewSession != null && this.brewSession.isBrewing()) {
            System.out.println("\n============================================");
            System.out.println("               Pausing Brew");
            System.out.println("============================================");

            this.brewSession.setPaused();
            this.brewIndicatorLight.setOff();
            this.reliefValve.openValve();
            this.sprayControl.closeValve();
        } else {
            System.out.println("The coffee maker currently isn't brewing coffee");
        }
    }

    public void brew() {
        if (canBrew()) {
            System.out.println("\n============================================");
            System.out.println("               Starting Brew");
            System.out.println("============================================");
            this.brewSession = new BrewSession(boiler, carafe, plate);
            this.brewIndicatorLight.setOn();
            this.boiler.startBoiling();
            this.sprayControl.openValve();
            this.brewSession.setBrewing();
        }
    }

    public void fillBoiler() {
        if (this.boiler.isEmpty()) {
            this.boiler.fillWater();
        } else {
            System.out.println("The boiler is already full");
        }
    }

    public void fill(CoffeeCup cup) {
        if (!this.carafe.isEmpty()) {
            this.removeCarafe();

            System.out.println("\n============================================");
            System.out.println("               Filling Cup");
            System.out.println("============================================");
            cup.fillFrom(this.carafe);
            this.replaceCarafe();
        } else {
            System.out.println("The coffee maker's carafe contains no coffee");
        }
    }

    public void removeCarafe() {
        System.out.println("\n============================================");
        System.out.println("               Removing Carafe");
        System.out.println("============================================");
        System.out.println("Carafe Level: " + this.carafe.percentageFull() + "%");

        this.plate.removeCarafe();
        if (this.brewSession != null && this.brewSession.isBrewing()) {
            this.pauseBrew();
        }
    }

    public void replaceCarafe() {
        System.out.println("\n============================================");
        System.out.println("               Replacing Carafe");
        System.out.println("============================================");

        this.plate.replaceCarafe(this.carafe);
        if (this.brewSession != null && this.brewSession.isPaused()) {
            this.resumeBrew();
        }

        System.out.println("Carafe Level: " + this.carafe.percentageFull() + "%");
    }

    private boolean canResumeBrew() {
        if (this.brewSession != null && this.brewSession.isPaused() && this.plate.isTripped() && !this.carafe.isFull() && !this.boiler.isEmpty()) {
            return true;
        }

        if (this.brewSession == null) {
            System.out.println("The coffee maker has not been brewing");
        } else if (this.brewSession.isBrewing()) {
            System.out.println("The coffee maker is already brewing coffee");
        } else if (!this.plate.isTripped()) {
            System.out.println("There is no carafe available to put coffee in");
        } else if (this.carafe.isFull()) {
            System.out.println("The carafe is already full of coffee");
        } else if (this.boiler.isEmpty()) {
            System.out.println("There is no water in the boiler");
        }

        return false;
    }

    private boolean canBrew() {
        if (this.plate.isTripped() && this.carafe.isEmpty() && this.boiler.isFull()) {
            return true;
        }

        if (!this.plate.isTripped()) {
            System.out.println("There is no carafe available to put coffee in");
        } else if (this.carafe.isFull()) {
            System.out.println("The carafe is not empty");
        } else if (this.boiler.isEmpty()) {
            System.out.println("There is no water in the boiler");
        }

        return false;
    }
}
