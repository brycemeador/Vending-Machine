package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

            if(!salesReport.exists()){
                salesReport.createNewFile();
            }
            writer.println(df.format(date.getTime()) + " " + action + " $" + transactionBalance + " $" + machineBalance + "\n");
            writer.close();
        }
    }
}

