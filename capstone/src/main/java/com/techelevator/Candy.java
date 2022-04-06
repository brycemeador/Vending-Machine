package com.techelevator;

import java.math.BigDecimal;

public class Candy extends item {
    public Candy(String name, BigDecimal price) {
        super(name, price);
    }
    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}
