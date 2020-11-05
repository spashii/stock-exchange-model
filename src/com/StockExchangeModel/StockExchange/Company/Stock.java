package com.StockExchangeModel.StockExchange.Company;

import java.util.Objects;

public class Stock {
    String ticker;
    double openPrice;
    double closePrice;  // contains the previous day's close price for any given day
    double lowPrice;
    double highPrice;

    public Stock(String ticker, double openPrice, double closePrice, double lowPrice, double highPrice) {
        this.ticker = ticker.toUpperCase();
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return ticker.equals(stock.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                '}';
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker.toUpperCase();
    }

    public double getLowerCircuit(){
        return 0.9 * closePrice;
    }

    public double getUpperCircuit(){
        return 1.1 * closePrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }
}
