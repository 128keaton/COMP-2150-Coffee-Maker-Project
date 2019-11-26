package com.kbrleson.coffeemaker.components.containers;

public interface LiquidContainer {
    boolean isFull();
    boolean isEmpty();
    double percentageFull();
}
