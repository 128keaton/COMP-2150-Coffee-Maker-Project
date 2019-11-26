package test;

import com.kbrleson.coffeemaker.CoffeeMaker;
import com.kbrleson.coffeemaker.additives.CinnamonAdditive;
import com.kbrleson.coffeemaker.additives.MochaAdditive;
import com.kbrleson.coffeemaker.additives.WhipAdditive;
import com.kbrleson.coffeemaker.components.containers.CoffeeCup;
import com.kbrleson.coffeemaker.enums.BrewStrength;

public class TestCoffeeMaker {
    public static void main(String[] args) {
        // The coffee lady morning routine
        goToWork();

        // In the evening, she invited a friend over for coffee.
        makeCoffeeForFriend();
    }

    private static void goToWork() {
        WhipAdditive whipAdditive = new WhipAdditive();
        CoffeeCup coffeeCup = new CoffeeCup();
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        // Brews a pot of black medium coffee
        coffeeMaker.addOrReplaceCarafe();
        coffeeMaker.fillBoiler();
        coffeeMaker.addCoffeeFilter();
        coffeeMaker.addCoffeeGrinds();
        coffeeMaker.setBrewStrength(BrewStrength.Medium);

        // Interrupts the coffee brewing about ¾ thru
        coffeeMaker.brewUntil(75);
        wait(1);

        // Makes herself a coffee with whip only and writes down the cost
        coffeeMaker.fillCoffeeCup(coffeeCup);
        coffeeCup.add(whipAdditive);
        System.out.println(coffeeCup);

        // Rushes to work..
        wait(1);
        coffeeMaker.addOrReplaceCarafe();
    }

    private static void makeCoffeeForFriend() {
        MochaAdditive mochaAdditive = new MochaAdditive();
        CinnamonAdditive cinnamonAdditive = new CinnamonAdditive();

        CoffeeCup personalCoffeeCup = new CoffeeCup();
        CoffeeCup friendCoffeeCup = new CoffeeCup();
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        // Brews a pot of light coffee
        coffeeMaker.addOrReplaceCarafe();
        coffeeMaker.fillBoiler();
        coffeeMaker.addCoffeeFilter();
        coffeeMaker.addCoffeeGrinds();
        coffeeMaker.setBrewStrength(BrewStrength.Light);

        // Interrupts the coffee brewing about ¼ thru
        coffeeMaker.brewUntil(25);
        wait(1);

        // Makes herself a coffee with cinnamon after returning the pot back to the brewing.
        coffeeMaker.fillCoffeeCup(personalCoffeeCup);
        personalCoffeeCup.add(cinnamonAdditive);

        // Records the cost (coffee with cinnamon)
        System.out.println(personalCoffeeCup);
        wait(1);

        coffeeMaker.addOrReplaceCarafe();

        // After the brewing is done, she makes her friend a coffee with mocha.
        coffeeMaker.fillCoffeeCup(friendCoffeeCup);
        friendCoffeeCup.add(mochaAdditive);

        // Records the cost (coffee with mocha)
        System.out.println(friendCoffeeCup);
    }


    private static void wait(int seconds) {
        try {
            Thread.sleep((seconds * 1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
