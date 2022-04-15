
package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    Map<String, Item> inventory = new LinkedHashMap<String, Item>();

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
                        entry.getValue().getQuantity() + " Avalible"
                );
            } else {
                System.out.println(entry.getKey().toString() + " " +
                        entry.getValue().getName().toString() + spacing.toString() +
                        entry.getValue().getPrice() + spacing.toString() +
                        "SOLD OUT"
                );
            }
        }
        return "";
    }
    public String vendItem(String inputKey) {
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
            }catch(Exception e){
            System.out.println("Invalid item code!");
        }
        return "";
    }
        public String vendSound (String code){
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
        public Map<String, Item> getInventory () {
            return inventory;
        }

        public void setInventory (Map < String, Item > inventory){
            this.inventory = inventory;
        }
    }




















