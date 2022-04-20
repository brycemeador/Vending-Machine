
package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Inventory {
    // Linked hash map used to keep order of input
    static Map<String, Item> inventory = new LinkedHashMap<String, Item>();

    // takes in file and parses the file in to hash map
    public void loadInventory() {
        String fileName = "vendingmachine.csv";
        File file = new File(fileName);
        try (Scanner inputFile = new Scanner(file)) {
            while (inputFile.hasNextLine()) {
                String lineOfInput = inputFile.nextLine();
                String[] splitInput = lineOfInput.split("\\|");
                Item item = new Item(splitInput[1], new BigDecimal(splitInput[2]), splitInput[3], 5);
                item.setCode(splitInput[0]);
                inventory.put(item.getCode(), item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exists");
        }
    }
    // First loop goes through a finds the largest string.
    // Second loop compares the current entry length and longest to figure out
    // how many spaces to add on and then prints each item amount or if it is sold out.
    public String printItems() {
        int length = 0;
        int largestSpaceLength = 0;

        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            String totalString = entry.getKey() + entry.getValue().getName() + entry.getValue().getPrice().toString();
            if (totalString.length() > length)
                length = totalString.length();
            largestSpaceLength = length + 1;
        }
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            String totalString = entry.getKey() + entry.getValue().getName() + entry.getValue().getPrice().toString();
            int spaceLength = largestSpaceLength - totalString.length();
            StringBuilder spacing = new StringBuilder();
            for (int i = 0; i < spaceLength; i++) {
                spacing.append(" ");
            }
            if (entry.getValue().getQuantity() > 0) {
                System.out.println(entry.getKey().toString() + " " +
                                entry.getValue().getName().toString() + " " +
                                entry.getValue().getPrice() + spacing.toString() +
                                entry.getValue().getQuantity() + " Available"
                        //corrected typo
                );
            } else {
                System.out.println(entry.getKey().toString() + " " +
                        entry.getValue().getName().toString() +
                        entry.getValue().getPrice() + spacing.toString() +
                        "SOLD OUT"
                );
            }
        }
        return "";
    }
    // Takes input key and deducts from balance and inventory then calls vend sound to print out items sound
    public void vendItem(String inputKey) {
        try {
            while (true) {

                VendWallet wallet = new VendWallet();
                SalesLog sales = new SalesLog();
                if (inventory.containsKey(inputKey) && VendWallet.getBalance().doubleValue() >
                        inventory.get(inputKey).getPrice().doubleValue() && inventory.get(inputKey).getQuantity() > 0) {
                    double tempAmount = VendWallet.balance.doubleValue() - inventory.get(inputKey).getPrice().doubleValue();
                    wallet.setBalance(BigDecimal.valueOf(tempAmount).setScale(2, RoundingMode.HALF_DOWN));
                    inventory.get(inputKey).setQuantity(inventory.get(inputKey).getQuantity() - 1);
                    System.out.println(vendSound(inputKey));
                    break;
                } else if (inventory.get(inputKey).getQuantity() == 0) {
                    System.out.println("Item is sold out!");
                    break;
                } else if (VendWallet.getBalance().doubleValue() < inventory.get(inputKey).getPrice().doubleValue()) {
                    System.out.println("Insufficient balance please feed money!");
                    break;
                } else if (!(inventory.containsKey(inputKey))) {
                    System.out.println("Invalid item code!");
                    break;
                }
            }
        }catch(NullPointerException e){
            System.out.println("Invalid item code!");
        }
    }

    // This method is called by vendItem to make items sound.
    public String vendSound(String code) {
        String sound = "";
        if (code.startsWith("A")) {
            sound = "\nCrunch Crunch, Yum!";
        } else if (code.startsWith("B")) {
            sound = "\nMunch Munch, Yum!";
        } else if (code.startsWith("C")) {
            sound = "\nGlug Glug, Yum!";
        } else if (code.startsWith("D")) {
            sound = "\nChew Chew, Yum!";
        }
        return sound;
    }

    public static Map<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Item> inventory) {
        this.inventory = inventory;
    }


}






























