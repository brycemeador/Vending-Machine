package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class VendWallet {
    static BigDecimal balance = new BigDecimal(0).setScale(2, RoundingMode.DOWN);

    public void feedMoney() {
        System.out.println("Please feed in whole dollar amounts! example, $1, $2, $5, or $10.");
        Scanner input = new Scanner(System.in);
        double num = Double.parseDouble(input.nextLine());
        if (num % 1 == 0) {
            setBalance(balance.add(BigDecimal.valueOf(num)));
        } else {
            System.out.println("Invalid entry please put in whole dolor amount!");

        }
    }

    public void balanceToZero(BigDecimal amount) {
        double convert = amount.doubleValue() * 100;
        int quarters = (int) (convert / 25);
        int tempAmount = (int) (convert % 25);
        int dimes = tempAmount / 10;
        int tempAmount2 = tempAmount % 10;
        int nickles = tempAmount2 / 5;
        int tempAmount3 = tempAmount2 % 5;

        setBalance(BigDecimal.valueOf(tempAmount3).setScale(2, RoundingMode.DOWN));

        System.out.println("Transaction completed you receive your balance of " +
                amount + " Quarters dispensed " + quarters +
                " Dimes dispensed " + dimes + "Nickles dispensed " + nickles);

    }

    public void setBalance(BigDecimal balance) {
        VendWallet.balance = balance.setScale(2, RoundingMode.FLOOR);
    }

    public static BigDecimal getBalance() {
        return balance;
    }
}