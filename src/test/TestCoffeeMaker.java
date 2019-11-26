package test;

import com.kbrleson.coffeemaker.CoffeeMaker;
import com.kbrleson.coffeemaker.additives.CinnamonAdditive;
import com.kbrleson.coffeemaker.additives.MochaAdditive;
import com.kbrleson.coffeemaker.components.containers.CoffeeCup;
import com.kbrleson.coffeemaker.enums.BrewStrength;

public class TestCoffeeMaker {
    public static void main(String[] args) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        CinnamonAdditive cinnamonAdditive = new CinnamonAdditive();
        MochaAdditive mochaAdditive = new MochaAdditive();

        coffeeMaker.fillBoiler();
        coffeeMaker.addCoffeeFilter();
        coffeeMaker.addCoffeeGrinds();
        coffeeMaker.brew();

        CoffeeCup cupA = new CoffeeCup();
        System.out.println(cupA);
        cupA.add(mochaAdditive);
        coffeeMaker.fill(cupA);
        System.out.println(cupA);

        cupA.drink(100);

        coffeeMaker.fillBoiler();
        coffeeMaker.clean();
        coffeeMaker.addCoffeeFilter();
        coffeeMaker.addCoffeeGrinds();
        coffeeMaker.setBrewStrength(BrewStrength.Medium);
        coffeeMaker.brew();

        System.out.println(cupA);
        coffeeMaker.fill(cupA);
        cupA.add(cinnamonAdditive);
        System.out.println(cupA);
    }
}
