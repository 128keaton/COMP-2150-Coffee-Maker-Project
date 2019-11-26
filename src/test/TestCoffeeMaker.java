package test;

import com.kbrleson.coffee_maker.CoffeeMaker;

public class TestCoffeeMaker {
    public static void main(String[] args) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        coffeeMaker.fillBoiler();
        coffeeMaker.brew();
        coffeeMaker.removeCarafe();
        coffeeMaker.replaceCarafe();
    }
}
