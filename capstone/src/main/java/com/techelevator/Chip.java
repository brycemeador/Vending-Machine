package com.techelevator;

import java.math.BigDecimal;

public class Chip extends item {
    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}
