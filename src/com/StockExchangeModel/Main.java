package com.StockExchangeModel;

import com.StockExchangeModel.StockExchange.StockExchange;
import com.StockExchangeModel.StockExchange.Company.PubliclyListedCompany;
import com.StockExchangeModel.StockExchange.Order.Order;
import com.StockExchangeModel.StockExchange.Trader.Trader;

public class Main {

    public static void main(String[] args) {
        StockExchange se = new StockExchange("BSE");
        Trader t;

        se.addPubliclyListedCompany(new PubliclyListedCompany("INFY", "INFY", "IT", 890, 890, 890, 890));
        se.addPubliclyListedCompany(new PubliclyListedCompany("TCS", "TCS", "IT", 2250, 2250, 2250, 2250));
        se.addPubliclyListedCompany(new PubliclyListedCompany("SBI", "SBI", "Banking", 195, 195, 195, 195));
        se.addPubliclyListedCompany(new PubliclyListedCompany("M&M", "M&M", "Automobiles", 610, 610, 610, 610));
        se.addPubliclyListedCompany(new PubliclyListedCompany("Cipla", "CIPL", "Pharma", 790, 790, 790, 790));
        se.addPubliclyListedCompany(new PubliclyListedCompany("Sunpharma", "SPHA", "Pharma", 490, 490, 490, 490));

        t = new Trader("Jaydeep", 25000);
        se.addTrader(t);

        t = new Trader("Mimi", 1000);
        t.putHolding(se.getPubliclyListedCompany("INFY").getStock(), 10);
        t.putHolding(se.getPubliclyListedCompany("TCS").getStock(), 5);
        t.putHolding(se.getPubliclyListedCompany("SBI").getStock(), 20);
        se.addTrader(t);

        t = new Trader("Kapil", 25000);
        t.putHolding(se.getPubliclyListedCompany("SBI").getStock(), 100);
        t.putHolding(se.getPubliclyListedCompany("M&M").getStock(), 20);
        se.addTrader(t);

        t = new Trader("Nusrat", 2500);
        t.putHolding(se.getPubliclyListedCompany("INFY").getStock(), 20);
        t.putHolding(se.getPubliclyListedCompany("M&M").getStock(), 25);
        t.putHolding(se.getPubliclyListedCompany("SBI").getStock(), 25);
        se.addTrader(t);

        se.stageOrder(new Order(se.getTrader(1), se.getPubliclyListedCompany("INFY").getStock(), "BUY", 10, 790));
        se.stageOrder(new Order(se.getTrader(1), se.getPubliclyListedCompany("SBI").getStock(), "BUY", 100, 210));
        se.stageOrder(new Order(se.getTrader(2), se.getPubliclyListedCompany("INFY").getStock(), "SELL", 10, 900));
        se.stageOrder(new Order(se.getTrader(3), se.getPubliclyListedCompany("M&M").getStock(), "BUY", 10, 580));
        se.stageOrder(new Order(se.getTrader(4), se.getPubliclyListedCompany("SBI").getStock(), "SELL", 25, 195));
        se.stageOrder(new Order(se.getTrader(2), se.getPubliclyListedCompany("TCS").getStock(), "BUY", 5, 2190));

        se.executeOrders();

        for(PubliclyListedCompany c: se.getPubliclyListedCompaniesByCategory("Pharma")){
            System.out.println(c.toString());
        }

        se.deletePubliclyListedCompany("TCS");
        se.deleteTrader(1);
        se.deletePubliclyListedCompany("M&M");
        se.deleteTrader(3);

        for(PubliclyListedCompany company : se.getPubliclyListedCompanies()) {
            System.out.println(company.toString());
        }

        for(Trader trader : se.getTraders()) {
            System.out.println(trader.toString());
        }
    }
}
