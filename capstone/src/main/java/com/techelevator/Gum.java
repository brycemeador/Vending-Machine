package com.techelevator;

import java.math.BigDecimal;

public class Gum extends item{
    public Gum(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}
