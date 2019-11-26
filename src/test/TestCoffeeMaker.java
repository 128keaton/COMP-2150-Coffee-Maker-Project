package test;

import com.kbrleson.coffee_maker.CoffeeMaker;
import com.kbrleson.coffee_maker.containers.CoffeeCup;

public class TestCoffeeMaker {
    public static void main(String[] args) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        coffeeMaker.fillBoiler();
        coffeeMaker.brew();
        coffeeMaker.removeCarafe();
        coffeeMaker.replaceCarafe();

        CoffeeCup cupA = new CoffeeCup();
        System.out.println(cupA);
        coffeeMaker.fill(cupA);
        System.out.println(cupA);
    }
}
