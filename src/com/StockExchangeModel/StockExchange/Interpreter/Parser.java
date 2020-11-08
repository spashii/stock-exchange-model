package com.StockExchangeModel.StockExchange.Interpreter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {
    public static String[] parseLine(String s) {
        return s.trim().toUpperCase().split("\\s+");
    }

    public static String[] parseCSVLine(String s) {
        return s.split(",");
    }

    public static Date parseDate(String strDate) {
        final List<String> dateFormats = Arrays.asList("dd-MM-yyyy", "d-MMM-yy");

        for(String format: dateFormats){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(strDate);
            } catch (ParseException e) {
                // intentionally empty
            }
        }
        // throw new IllegalArgumentException("Invalid input for date. Given '"+strDate+"', expecting format yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd.")
        return null;
    }

}
