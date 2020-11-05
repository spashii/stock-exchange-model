package com.StockExchangeModel;

import com.StockExchangeModel.StockExchange.Company.Company;
import com.StockExchangeModel.StockExchange.Interpreter.Interpreter;
import com.StockExchangeModel.StockExchange.StockExchange;
import com.StockExchangeModel.StockExchange.Order.Order;
import com.StockExchangeModel.StockExchange.Trader.Trader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StockExchange se = new StockExchange("BSE");
        Trader t;

        Interpreter i = new Interpreter(se);
        i.startSession(">>");

        se.addCompany(new Company("INFY", "INFY", "IT", 890, 890, 890, 890));
        se.addCompany(new Company("TCS", "TCS", "IT", 2250, 2250, 2250, 2250));
        se.addCompany(new Company("SBI", "SBI", "Banking", 195, 195, 195, 195));
        se.addCompany(new Company("M&M", "M&M", "Automobiles", 610, 610, 610, 610));
        se.addCompany(new Company("Cipla", "CIPL", "Pharma", 790, 790, 790, 790));
        se.addCompany(new Company("Sunpharma", "SPHA", "Pharma", 490, 490, 490, 490));

        t = new Trader("Jaydeep", 25000);
        se.addTrader(t);

        t = new Trader("Mimi", 1000);
        t.putHolding(se.getCompany("INFY").getStock(), 10);
        t.putHolding(se.getCompany("TCS").getStock(), 5);
        t.putHolding(se.getCompany("SBI").getStock(), 20);
        se.addTrader(t);

        t = new Trader("Kapil", 25000);
        t.putHolding(se.getCompany("SBI").getStock(), 100);
        t.putHolding(se.getCompany("M&M").getStock(), 20);
        se.addTrader(t);

        t = new Trader("Nusrat", 2500);
        t.putHolding(se.getCompany("INFY").getStock(), 20);
        t.putHolding(se.getCompany("M&M").getStock(), 25);
        t.putHolding(se.getCompany("SBI").getStock(), 25);
        se.addTrader(t);

        se.stageOrder(new Order(se.getTrader(1), se.getCompany("INFY").getStock(), "BUY", 10, 790));
        se.stageOrder(new Order(se.getTrader(1), se.getCompany("SBI").getStock(), "BUY", 100, 210));
        se.stageOrder(new Order(se.getTrader(2), se.getCompany("INFY").getStock(), "SELL", 10, 900));
        se.stageOrder(new Order(se.getTrader(3), se.getCompany("M&M").getStock(), "BUY", 10, 580));
        se.stageOrder(new Order(se.getTrader(4), se.getCompany("SBI").getStock(), "SELL", 25, 195));
        se.stageOrder(new Order(se.getTrader(2), se.getCompany("TCS").getStock(), "BUY", 5, 2190));

        se.executeOrders();

        for(Company c: se.getCompaniesByCategory("Pharma")){
            System.out.println(c.toString());
        }

        se.deleteCompany("TCS");
        se.deleteTrader(1);
        se.deleteCompany("M&M");
        se.deleteTrader(3);

        for(Company company : se.getCompanies()) {
            System.out.println(company.toString());
        }

        for(Trader trader : se.getTraders()) {
            System.out.println(trader.toString());
        }



        System.out.println("\n");

        Interpreter.printInterpretedResults(i.interpretFile("input1.txt"));

//        for ( String s : i.interpret(new String[]{"company", "show_all"})){
//            System.out.println(s);
//        }
//
//        for ( String s : i.interpret(new String[]{"company", "add", "apple", "AAPL", "IT", "9990", "9990", "9990", "9990"})){
//            System.out.println(s);
//        }
//
//        for ( String s : i.interpret(new String[]{"company", "show_category", "it"})){
//            System.out.println(s);
//        }
    }
}
