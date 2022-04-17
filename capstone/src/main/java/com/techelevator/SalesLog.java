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
    File salesLog = new File("Sales Log.txt");
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    String action;
    BigDecimal balanceBeforeAction;
    BigDecimal balanceAfterAction;

    public void log(String action, BigDecimal balanceBeforeAction, BigDecimal balanceAfterAction) throws IOException {
        salesLog.createNewFile();
        Calendar date = Calendar.getInstance();

        try (PrintWriter writer = new PrintWriter(new FileWriter(salesLog, true))) {
            this.action = action;
            this.balanceBeforeAction = balanceBeforeAction;
            this.balanceAfterAction = balanceAfterAction;

            if(!salesLog.exists()){
                salesLog.createNewFile();
            }
            writer.println(df.format(date.getTime()) + " " + action + " $" + balanceBeforeAction + " $" + balanceAfterAction);
        }
    }
}

