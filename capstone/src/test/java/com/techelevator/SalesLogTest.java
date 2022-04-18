package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SalesLogTest {

    @Test
    void log() throws IOException {
        SalesLog salesLog = new SalesLog();
        String action = "Test action";
        BigDecimal first = new BigDecimal(1.00);
        BigDecimal second = new BigDecimal(2.00);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Calendar date = Calendar.getInstance();
        String compare = df.format(date.getTime()) + " " + action + " $" + first + " $" + second;
        salesLog.log(action, first, second);
        String result = "";

        File salesReport = new File("Sales Log.txt");
        try (Scanner inputFile = new Scanner(salesReport)) {
            while (inputFile.hasNextLine()) {
                String lineOfInput = inputFile.nextLine();
                result = lineOfInput;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exists");
        }
        Assert.assertEquals(compare, result);
    }
}