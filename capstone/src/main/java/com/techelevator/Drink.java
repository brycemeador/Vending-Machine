package com.techelevator;

import java.math.BigDecimal;

public class Drink extends item {
    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String makeSound() {
        return "Glug Glug, Yum!";
    }
}
