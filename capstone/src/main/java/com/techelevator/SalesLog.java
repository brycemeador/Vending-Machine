package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesLog {

    File salesReport = new File("Sales Log.txt");
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    String action = null;
    BigDecimal transactionBalance = null;
    BigDecimal machineBalance = null;


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
        Calendar date = Calendar.getInstance();
        DateFormat dfReport = new SimpleDateFormat("MM_dd_yyyy hh_mm_ss a");
        File salesLog = new File(dfReport.format(date.getTime()) + " Sales Report.txt");
        salesLog.createNewFile();

        int amountSold = 0;
        double totalValSold = 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(salesLog, true))) {
            writer.println(">'''");
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


