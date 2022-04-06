package com.techelevator;

import java.math.BigDecimal;

public abstract class item {
    private String name;
    private BigDecimal price;

    public item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public abstract String makeSound();
}
