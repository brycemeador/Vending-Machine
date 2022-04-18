package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static com.techelevator.VendWallet.balance;
import static org.junit.jupiter.api.Assertions.*;

class VendtemTest {


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
}