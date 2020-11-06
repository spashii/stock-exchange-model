package com.StockExchangeModel.StockExchange.Order;

import com.StockExchangeModel.StockExchange.Company.Stock;
import com.StockExchangeModel.StockExchange.Trader.Trader;

public class Order {
    private Trader trader;
    Stock stock;
    Type type;
    int quantity;
    double rate;

    public Order(Trader trader, Stock stock, Type type, int quantity, double rate) {
        this.trader = trader;
        this.stock = stock;
        this.type = type;
        this.quantity = quantity;
        this.rate = rate;
    }

    public Order(Trader trader, Stock stock, String type, int quantity, double rate) {
        this.trader = trader;
        this.stock = stock;
        this.type = new Type(type);
        this.quantity = quantity;
        this.rate = rate;
    }

    public Order() {
        this(null, null, "UNKNOWN", 0, 0.0);
    }

    @Override
    public String toString() {
        return "Order{" +
                "trader_id=" + getTrader().getId() +
                ", trader_name='" + getTrader().getName() + '\'' +
                ", stock='" + stock.getTicker() + '\'' +
                ", type='" + type.getTypeString() + '\'' +
                ", quantity=" + quantity +
                ", rate=" + rate +
                '}';
    }

    public String toStringStatus(String status) {
        return "Order{" +
                "status='" + status + '\'' +
                ", trader_id=" + getTrader().getId() +
                ", trader_name='" + getTrader().getName() + '\'' +
                ", stock='" + stock.getTicker() + '\'' +
                ", type='" + type.getTypeString() + '\'' +
                ", quantity=" + quantity +
                ", rate=" + rate +
                '}';
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Stock getStock() {
        return stock;
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
