package com.StockExchangeModel.StockExchange;

import com.StockExchangeModel.StockExchange.Company.Company;
import com.StockExchangeModel.StockExchange.Order.Order;
import com.StockExchangeModel.StockExchange.Order.Transaction;
import com.StockExchangeModel.StockExchange.Order.Type;
import com.StockExchangeModel.StockExchange.Trader.Trader;

import java.util.ArrayList;

public class StockExchange {
    String name;
    ArrayList<Company> companies;
    ArrayList<Trader> traders;
    ArrayList<Order> orders;

    StockExchange() {
        this("");
    }

    public StockExchange(String name) {
        this.companies = new ArrayList<>();
        this.traders = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.name = name;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(String ticker) {
        for(Company c: companies) {
            if(c.getStock().getTicker().compareTo(ticker.toUpperCase()) == 0) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Company> getCompaniesByCategory(String s) {
        ArrayList<Company> ret = new ArrayList<>();
        for(Company c : companies) {
            if(c.getCategory().compareTo(s) == 0) {
               ret.add(c);
            }
        }
        return ret;
    }

    public boolean addCompany(Company c) {
        if(getCompany(c.getStock().getTicker()) == null) {
            return companies.add(c);
        }
        return false;
    }

    public boolean deleteCompany(Company c) {
        return companies.remove(c);
    }

    public boolean deleteCompany(String ticker) {
        Company c = getCompany(ticker);
        if(c != null) {
            return deleteCompany(c);
        }
        return false;
    }

    public ArrayList<Trader> getTraders() {
        return traders;
    }

    public Trader getTrader(long id) {
        for(Trader t: traders) {
            if(t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean addTrader(Trader t) {
        if(getTrader(t.getId()) == null) {
            traders.add(t);
            System.out.println("Added " + t.toString());
        }
        return false;
    }

    public boolean deleteTrader(Trader t) {
        System.out.println("Deleted " + t.toString());
        return traders.remove(t);
    }

    public boolean deleteTrader(long id) {
        Trader t = getTrader(id);
        if(t != null) {
            return deleteTrader(t);
        }
        return false;
    }

    @Override
    public String toString() {
        return "StockExchange{" +
                "name='" + name + '\'' +
                ", publiclyListedCompanies=" + companies.toString() +
                ", orders=" + orders.toString() +
                '}';
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public boolean stageOrder(Order o) {
        // generic check for both types of orders
        if (o.getRate() < o.getStock().getLowerCircuit()) {
            System.out.println("Rejected " + o.toStringStatus("REJECTED:LOWER_CIRCUIT_VIOLATION"));
            return false;
        }
        if(o.getRate() > o.getStock().getUpperCircuit()) {
            System.out.println("Rejected " + o.toStringStatus("REJECTED:UPPER_CIRCUIT_VIOLATION"));
            return false;
        }

        // specific checks for different types of orders
        if (o.getType().getTypeEnum() == Type.TypeEnum.BUY) {
            if (o.getTrader().getFunds() - (o.getQuantity() * o.getRate()) < 0) {
                System.out.println("Rejected " + o.toStringStatus("REJECTED:INSUFFICIENT_FUNDS"));
                return false;
            } else {
                orders.add(o);
                System.out.println("Staged " + o.toStringStatus("STAGED:BUY"));
                return true;
            }
        } else {
            if(o.getTrader().getHolding(o.getStock()) < o.getQuantity()) {
                return false;
            }
            else {
                orders.add(o);
                System.out.println("Staged " + o.toStringStatus("STAGED:SELL"));
                return true;
            }
        }
    }

    public ArrayList<Transaction> executeOrders() {
        ArrayList<Transaction> successfulTransactions = new ArrayList<>();

        ArrayList<Order> buyOrders = new ArrayList<>();
        ArrayList<Order> sellOrders = new ArrayList<>();

        for(Order o: orders) {
            if(o.getType().getTypeEnum() == Type.TypeEnum.BUY) {
                buyOrders.add(o);
            } else {
                sellOrders.add(o);
            }
        }

        for(Order sellOrder: sellOrders) {
            Order highestBid = new Order();
            highestBid.setRate(sellOrder.getRate());
            highestBid.setQuantity(sellOrder.getQuantity());

            for(Order buyOrder: buyOrders) {
                if(sellOrder.getStock().getTicker().compareTo(buyOrder.getStock().getTicker()) == 0) {
                    if (buyOrder.getRate() >= highestBid.getRate() && buyOrder.getQuantity() >= sellOrder.getQuantity()) {
                        highestBid = buyOrder;
                    }
                }
            }

            if (highestBid.getStock() != null) {
                Transaction transaction = new Transaction(sellOrder, sellOrder.getTrader(), highestBid.getTrader());
                if(transaction.execute()) {
                    System.out.println("Executed " + transaction.toString());
                    orders.remove(sellOrder);
                    orders.remove(highestBid);
                    successfulTransactions.add(transaction);
                }
            }
        }

        return successfulTransactions;
    }

    public String getName() {
        return name;
    }
}
