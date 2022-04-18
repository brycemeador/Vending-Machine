package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Map;

class InventoryTest {

    //Merged all the inventory test into a single test class

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {System.setOut(new PrintStream(outputStreamCaptor));}


    @Test
    void printItemsTest() {
    Inventory inventory = new Inventory();
        inventory.loadInventory();
        inventory.printItems();
        String items = outputStreamCaptor.toString().trim();
        String itemsEquals ="A1 Potato Crisps 3.05      5 Available\r\n" +
                "A2 Stackers 1.45           5 Available\r\n" +
                "A3 Grain Waves 2.75        5 Available\r\n" +
                "A4 Cloud Popcorn 3.65      5 Available\r\n" +
                "B1 Moonpie 1.80            5 Available\r\n" +
                "B2 Cowtales 1.50           5 Available\r\n" +
                "B3 Wonka Bar 1.50          5 Available\r\n" +
                "B4 Crunchie 1.75           5 Available\r\n" +
                "C1 Cola 1.25               5 Available\r\n" +
                "C2 Dr. Salt 1.50           5 Available\r\n" +
                "C3 Mountain Melter 1.50    5 Available\r\n" +
                "C4 Heavy 1.50              5 Available\r\n" +
                "D1 U-Chews 0.85            5 Available\r\n" +
                "D2 Little League Chew 0.95 5 Available\r\n" +
                "D3 Chiclets 0.75           5 Available\r\n" +
                "D4 Triplemint 0.75         5 Available";
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

    @Test
    void vendItemExists() {
        VendWallet vendWallet = new VendWallet();
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        BigDecimal balance = new BigDecimal("5.00");
        vendWallet.setBalance(balance);
        inventory.vendItem("A1");
        BigDecimal expBal = new BigDecimal("1.95");

        Assert.assertEquals(4,Inventory.getInventory().get("A1").getQuantity());
        Assert.assertEquals("Crunch Crunch, Yum!",expBal.doubleValue(), VendWallet.getBalance().doubleValue(),0.00);
    }

    @Test
    void vendItemDoesNotExist() {
        VendWallet vendWallet = new VendWallet();
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        vendWallet.setBalance(BigDecimal.valueOf(5.00));
        inventory.vendItem("Z1");
        BigDecimal balance = new BigDecimal("5.00");
        String invalid = "Invalid item code!";
        Assert.assertEquals("Invalid item code!",balance ,VendWallet.getBalance() );
    }

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