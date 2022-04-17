package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalesLog {

    File salesReport = new File("Sales Log.txt");
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    String action = null;
    BigDecimal transactionBalance = null;
    BigDecimal machineBalance = null;
    File salesLog = new File("Sales Report.txt");

    public void log(String action, BigDecimal transactionBalance, BigDecimal machineBalance) throws IOException {
        salesReport.createNewFile();
        Calendar date = Calendar.getInstance();

        try (PrintWriter writer = new PrintWriter(new FileWriter(salesReport, true))) {
            this.action = action;
            this.transactionBalance = transactionBalance;
            this.machineBalance = machineBalance;

            if (!salesReport.exists()) {
                salesReport.createNewFile();
            }
            writer.println(df.format(date.getTime()) + " " + action + " $" + transactionBalance + " $" + machineBalance + "\n");
            writer.close();
        }
    }
    public void salesReportOut() throws IOException {
        salesLog.createNewFile();
        Calendar date = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

        int amountSold = 0;
        double totalValSold = 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(salesLog, true))) {
            if (!salesLog.exists()) {
                salesLog.createNewFile();
            }
            writer.println("SALES REPORT AS OF " + df.format(date.getTime()) + "\n" + ">'''");
            for (Map.Entry<String, Item> entry : Inventory.getInventory().entrySet()) {
                amountSold = 5 - entry.getValue().getQuantity();
                totalValSold = totalValSold + entry.getValue().getPrice().doubleValue() * amountSold;
                writer.println(">" + entry.getValue().getName().toString() + "|" + amountSold);
            }
            writer.println(">'''" + "\n" + "**TOTAL SALES**" + "\n" + "$" + totalValSold);
            writer.close();
        }
    }
}


