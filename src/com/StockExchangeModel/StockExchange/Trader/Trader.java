package com.StockExchangeModel.StockExchange.Trader;

import com.StockExchangeModel.StockExchange.Company.Stock;

import java.util.HashMap;

public class Trader {
    static long count = 0;  // helps in generation of unique id

    long id;
    String name;
    double funds;
    HashMap<Stock, Integer> holdings; // stock, quantity

    public Trader(String name, double funds, HashMap<Stock, Integer> holdings) {
        this.name = name;
        this.id = ++count;
        this.funds = funds;
        this.holdings = holdings;
        System.out.println("Added " + this.toString());
    }

    public Trader(String name, double funds) {
        this.name = name;
        this.id = ++count;
        this.funds = funds;
        this.holdings = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", funds=" + funds +
                ", holdings=" + toStringHoldings() +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public double putFunds(double funds) {
        if(this.funds + funds < 0) {
            return 0.0;
        }
        setFunds(this.funds + funds);
        return funds;
    }

    public HashMap<Stock, Integer> getHoldings() {
        return holdings;
    }

    public boolean putHolding(Stock s, int q) {
        if(holdings.getOrDefault(s, 0) + q < 0) {
            return false;
        }
        holdings.put(s, holdings.getOrDefault(s, 0) + q);
        return true;
    }

    public int getHolding(Stock s) {
        return holdings.getOrDefault(s, 0);
    }

    public String toStringHoldings() {
        if(holdings.size() == 0) {
            return "{}";
        }
        StringBuilder ret = new StringBuilder("{");
        for(Stock s: holdings.keySet()) {
           ret.append("{stock='" + s.getTicker() + "', quantity=" + holdings.get(s) + "}, ");
        }
        ret.deleteCharAt(ret.length()-1);
        ret.deleteCharAt(ret.length()-1);
        ret.append("}");
        return ret.toString();
    }
}
