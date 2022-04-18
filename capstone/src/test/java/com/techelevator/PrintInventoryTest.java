package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrintInventoryTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printItemsTest() {
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        inventory.printItems();
        String items = outputStreamCaptor.toString().trim();
        String itemsEquals = "A1 Potato Crisps 3.05      5 Avalible\r\n" +
                "A2 Stackers 1.45           5 Avalible\r\n" +
                "A3 Grain Waves 2.75        5 Avalible\r\n" +
                "A4 Cloud Popcorn 3.65      5 Avalible\r\n" +
                "B1 Moonpie 1.80            5 Avalible\r\n" +
                "B2 Cowtales 1.50           5 Avalible\r\n" +
                "B3 Wonka Bar 1.50          5 Avalible\r\n" +
                "B4 Crunchie 1.75           5 Avalible\r\n" +
                "C1 Cola 1.25               5 Avalible\r\n" +
                "C2 Dr. Salt 1.50           5 Avalible\r\n" +
                "C3 Mountain Melter 1.50    5 Avalible\r\n" +
                "C4 Heavy 1.50              5 Avalible\r\n" +
                "D1 U-Chews 0.85            5 Avalible\r\n" +
                "D2 Little League Chew 0.95 5 Avalible\r\n" +
                "D3 Chiclets 0.75           5 Avalible\r\n" +
                "D4 Triplemint 0.75         5 Avalible";
        Assert.assertTrue(itemsEquals.equals(items));

            System.setOut(standardOut);
    }
    @Test
    void printItemsSoldOutTest(){
        Inventory inventory = new Inventory();
        inventory.loadInventory();

        for(Map.Entry<String, Item> entry : Inventory.getInventory().entrySet()){
            entry.getValue().setQuantity(0);
        }
        inventory.printItems();
        String items = outputStreamCaptor.toString().trim();

        String itemsEquals = "A1 Potato Crisps3.05      SOLD OUT\r\n" +
                "A2 Stackers1.45           SOLD OUT\r\n" +
                "A3 Grain Waves2.75        SOLD OUT\r\n" +
                "A4 Cloud Popcorn3.65      SOLD OUT\r\n" +
                "B1 Moonpie1.80            SOLD OUT\r\n" +
                "B2 Cowtales1.50           SOLD OUT\r\n" +
                "B3 Wonka Bar1.50          SOLD OUT\r\n" +
                "B4 Crunchie1.75           SOLD OUT\r\n" +
                "C1 Cola1.25               SOLD OUT\r\n" +
                "C2 Dr. Salt1.50           SOLD OUT\r\n" +
                "C3 Mountain Melter1.50    SOLD OUT\r\n" +
                "C4 Heavy1.50              SOLD OUT\r\n" +
                "D1 U-Chews0.85            SOLD OUT\r\n" +
                "D2 Little League Chew0.95 SOLD OUT\r\n" +
                "D3 Chiclets0.75           SOLD OUT\r\n" +
                "D4 Triplemint0.75         SOLD OUT";
        Assert.assertTrue(itemsEquals.equals(items));

            System.setOut(standardOut);



    }
}