package com.StockExchangeModel.StockExchange.Interpreter;

import com.StockExchangeModel.StockExchange.Company.CompanyActionHandler;
import com.StockExchangeModel.StockExchange.Order.OrderActionHandler;
import com.StockExchangeModel.StockExchange.StockExchange;
import com.StockExchangeModel.StockExchange.Trader.TraderActionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Interpreter extends Parser {
    StockExchange context;
    CompanyActionHandler companyActionHandler;
    TraderActionHandler traderActionHandler;
    OrderActionHandler orderActionHandler;

    public Interpreter(StockExchange se) {
        this.context = se;
        // registering action handlers
        this.companyActionHandler = new CompanyActionHandler(se);
        this.traderActionHandler = new TraderActionHandler(se);
        this.orderActionHandler = new OrderActionHandler(se);
    }

    public String[] interpret(String[] parsed) {
        if(parsed != null && parsed.length >= 1) {
            for (int i = 0; i < parsed.length; i++) {
                parsed[i] = parsed[i].toUpperCase();
            }
            String leader = parsed[0];
            String actionTypeString = new String();

            String[] arguments = new String[0];
            if (parsed.length > 1) {
                actionTypeString = parsed[1];
                if (parsed.length > 2) {
                    arguments = Arrays.copyOfRange(parsed, 2, parsed.length);
                }
            }

            Action action = new Action(actionTypeString, arguments);

            switch (leader) {
                case "COMPANY":
                    return companyActionHandler.handleAction(action);
                case "TRADER":
                    return traderActionHandler.handleAction(action);
                case "ORDER":
                    return orderActionHandler.handleAction(action);
                default:
                    return new String[]{
                            "Usage:",
                            "  COMPANY:",
                            "    COMPANY SHOW ticker?",
                            "    COMPANY SHOW_ALL",
                            "    COMPANY SHOW_CATEGORY category?",
                            "    COMPANY ADD name? ticker? category? open_price? close_price? low_price? high_price?",
                            "    COMPANY DELETE ticker?",
                            "  TRADER:",
                            "    TRADER SHOW id?",
                            "    TRADER SHOW_ALL",
                            "    TRADER ADD name? funds? holdings(format:\"{ticker:quantity, ticker2:quantity2}\")?",
                            "    TRADER DELETE id?",
                            "  ORDER:",
                            "    ORDER SHOW_ALL",
                            "    ORDER EXECUTE_ALL",
                            "    ORDER STAGE trader_id? stock_ticker? type(BUY/SELL)? quantity? rate?",
                            "  EXIT (only in interpreter mode)"
                    };
            }
        }
        return new String[0];
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
            if(parsed.length >= 1 && (parsed[0].compareTo("EXIT") == 0
                                   || parsed[0].compareTo("QUIT") == 0
                                   || parsed[0].compareTo("Q") == 0) ){
                return;
            }
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
