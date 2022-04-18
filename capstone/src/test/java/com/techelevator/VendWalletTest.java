package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class VendWalletTest {
    private VendWallet vendWallet;

    @Before
    public void setObjectUnderTest() {
        vendWallet = new VendWallet();
    }

    //test that setBalance/getBalance works
    @Test
    public void balance() {
        BigDecimal expected = new BigDecimal("45.00");
        BigDecimal testAmount = new BigDecimal("45");

        vendWallet.setBalance(testAmount);

        Assert.assertEquals(expected, VendWallet.getBalance());
    }

    //test that giveChange works
    @Test
    public void giveChange() {
        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal testAmount = new BigDecimal("45").setScale(2);

        vendWallet.setBalance(testAmount);
        vendWallet.balanceToZero(testAmount);

        Assert.assertEquals("Transaction completed your change is $45.00\n" +
                "Quarters Dispensed: 180\n" +
                "Dimes Dispensed: 0\n" +
                "Nickles Dispensed: 0", expected, new BigDecimal("0.00"));
    }

    //test that new updateBalance method works when a whole number is entered
    @Test
    public void updateBalance() {
        double expected = 15.00;
        double test = 15.00;

        vendWallet.updateBalance(test);

        Assert.assertEquals(expected, vendWallet.updateBalance(test), 0.0);
    }

    //test that updateBalance works when non-whole number is entered
    @Test
    public void updateBalanceNonwhole() {
        double expected = 0;
        double test = 15.50;

        vendWallet.updateBalance(test);

        Assert.assertEquals(expected, vendWallet.updateBalance(test), 0.0);
    }
}