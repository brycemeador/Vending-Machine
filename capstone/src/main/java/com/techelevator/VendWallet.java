package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class VendWallet {
    static BigDecimal balance = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);

    //converted feedMoney into 2 methods, feedMoney and updateBalance. Couldn't find out how to test feedMoney due to
    //no parameters
    // This method takes in money with scanner and calls update balance.
    public BigDecimal feedMoney() {

        System.out.println("\nPlease feed in whole dollar amounts! Examples: $1, $2, $5, or $10.");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextDouble()) {
            System.out.println("\nInvalid entry please insert whole dollar amount");
            input = new Scanner(System.in);
        }
        double num = Double.parseDouble(input.nextLine());
        return BigDecimal.valueOf(updateBalance(num)).setScale(2);
    }
    public double updateBalance(Double num){
        if (num % 1 == 0 && num > 0) {
            setBalance(balance.add(BigDecimal.valueOf(num)));
            return num;
        } else {
            System.out.println("\nInvalid entry please insert whole dollar amount");
            return 0;
        }
    }
    // Cashes out balance by taking the balance and returning the fewest coins possible.
    public void balanceToZero(BigDecimal amount) {
        double convert = amount.doubleValue() * 100;
        int quarters = (int) (convert / 25);
        int tempAmount = (int) (convert % 25);
        int dimes = tempAmount / 10;
        int tempAmount2 = tempAmount % 10;
        int nickles = tempAmount2 / 5;
        int tempAmount3 = tempAmount2 % 5;
        setBalance(BigDecimal.valueOf((double)tempAmount3).setScale(2, RoundingMode.HALF_DOWN));

        System.out.println("\nTransaction completed your change is $" +
                amount + "\nQuarters Dispensed: " + quarters +
                "\nDimes Dispensed: " + dimes + "\nNickles Dispensed: " + nickles);
    }

    public void setBalance(BigDecimal balance) {
        VendWallet.balance = balance.setScale(2,RoundingMode.HALF_DOWN);
    }

    public static BigDecimal getBalance() {
        return balance;
    }
}
