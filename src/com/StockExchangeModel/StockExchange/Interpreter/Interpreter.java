package com.StockExchangeModel.StockExchange.Interpreter;

import com.StockExchangeModel.StockExchange.Company.CompanyActionHandler;
import com.StockExchangeModel.StockExchange.StockExchange;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Interpreter extends Parser {
    StockExchange context;
    CompanyActionHandler companyActionHandler;

    public Interpreter(StockExchange se) {
        this.context = se;
        this.companyActionHandler = new CompanyActionHandler(se);
    }

    public String[] interpret(String[] parsed) {
        if(parsed != null && parsed.length >= 2) {
            for (int i = 0; i < parsed.length; i++) {
               parsed[i] = parsed[i].toUpperCase();
            }
            String leader = parsed[0];
            String actionTypeString = parsed[1];
            String[] arguments = Arrays.copyOfRange(parsed, 2, parsed.length);

            Action action = new Action(actionTypeString, arguments);

            switch(leader) {
                case "COMPANY":
                    return companyActionHandler.handleAction(action);
                default:
                    return null;
            }
        }
        return null;
    }

    public String[] interpretFile(String filename) {
        ArrayList<String> ret = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                    ret.addAll(Arrays.asList(interpret(parseLine(line))));
            }
        } catch (Exception e) {
            ret.add(e.getMessage());
        }
        return ret.toArray(new String[0]);
    }

    public void startSession(String prompt){
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.print(prompt + " ");
            String input = s.nextLine();
            String[] parsed = parseLine(input);
            String[] interpreted = interpret(parsed);
            if (interpreted != null) {
                for (String i : interpreted) {
                    System.out.println(i);
                }
            }
        }
    }

    public static void printInterpretedResults(String[] results) {
        for ( String s : results){
            System.out.println(s);
        }
    }
}
