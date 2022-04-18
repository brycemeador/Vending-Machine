package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Map;

class LoadInventoryTest {

    @Test
    public void testFirstItemPrice() {
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        Map<String, Item> testInventory = Inventory.getInventory();
        Double testPrice = testInventory.get("A1").getPrice().doubleValue();
        Double expPrice = 3.05;
        Assert.assertEquals(expPrice, testPrice);
    }

    @Test
    public void testLastItemName() {
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        Map<String, Item> testInventory = Inventory.getInventory();
        String testName = testInventory.get("D4").getName();
        String expName = "Triplemint";
        Assert.assertEquals(expName, testName);
    }
}
