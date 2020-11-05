package com.StockExchangeModel.StockExchange.Order;

import com.StockExchangeModel.StockExchange.Trader.Trader;

public class Transaction extends Order {
    Trader seller;
    Trader buyer;

    public Transaction(Order o, Trader seller, Trader buyer) {
        super();
        this.stock = o.stock;
        this.type = new Type("TRANSACTION");
        this.quantity = o.quantity;
        this.rate = o.rate;
        this.seller = seller;
        this.buyer = buyer;
    }

    public boolean execute() {
        // seller's ask price is the final price at which the transaction takes place
        if(seller != null && buyer != null) {
            double releasedFunds = -1 * buyer.putFunds(-1 * quantity * rate);
            if(releasedFunds != 0.0) {
                if(seller.putHolding(stock, -1*quantity)) {
                    buyer.putHolding(stock, quantity);
                    seller.putFunds(quantity * rate);
                    if(stock.getLowPrice() > rate) {
                        stock.setLowPrice(rate);
                    }
                    if(stock.getHighPrice() < rate) {
                        stock.setHighPrice(rate);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public Trader getTrader() {
        return null;
    }

    public void setTrader(Trader trader) {
    }

    public Trader getSeller() {
        return seller;
    }

    public void setSeller(Trader seller) {
        this.seller = seller;
    }

    public Trader getBuyer() {
        return buyer;
    }

    public void setBuyer(Trader buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "seller_id=" + seller.getId() +
                ", seller_name='" + seller.getName() + '\'' +
                ", buyer_id=" + buyer.getId() +
                ", buyer_name='" + buyer.getName() + '\'' +
                ", stock='" + stock.getTicker() + '\'' +
                ", quantity=" + quantity +
                ", rate=" + rate +
                '}';
    }
}
