
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
    public String printItems(){
        for(Map.Entry<String, Item> entry : inventory.entrySet()){
            if(entry.getValue().getQuantity() > 0) {
                System.out.println(entry.getKey().toString() + " " +
                        entry.getValue().getName().toString() + " " +
                        entry.getValue().getPrice() + " " +
                        entry.getValue().getQuantity()
                );
            } else {
                System.out.println(entry.getKey().toString() + " " +
                        entry.getValue().getName().toString() + " " +
                        entry.getValue().getPrice() + " " +
                        "SOLD OUT"
                );
            }
        }
        return "";
    }
    public void vendItem(String inputKey){
        VendWallet wallet = new VendWallet();
        Boolean prosess = true;
        while(prosess)
            if(inventory.containsKey(inputKey)) {
                if (VendWallet.getBalance().doubleValue() > inventory.get(inputKey).getPrice().doubleValue()) {
                    if (inventory.get(inputKey).getQuantity() > 0) {
                        double tempAmount = VendWallet.balance.doubleValue() - inventory.get(inputKey).getPrice().doubleValue();
                        wallet.setBalance(BigDecimal.valueOf(tempAmount).setScale(2, RoundingMode.HALF_DOWN));
                        inventory.get(inputKey).setQuantity(inventory.get(inputKey).getQuantity() - 1);
                        System.out.println(vendSound(inputKey));
                        prosess = false;
                    }
                }
            }
    }
    public String vendSound(String code){
        String sound = "";
        if (code.startsWith("A")){
            sound = "Crunch Crunch, Yum!";
        }
        else if (code.startsWith("B")){
            sound = "Munch Munch, Yum!";
        }
        else if (code.startsWith("C")){
            sound = "Glug Glug, Yum!";
        }
        else if (code.startsWith("D")){
            sound = "Chew Chew, Yum!";
        }
        return sound;
    }
















    public Map<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Item> inventory) {
        this.inventory = inventory;
    }
}


















