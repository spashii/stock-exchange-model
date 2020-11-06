package com.StockExchangeModel.StockExchange.Order;

import com.StockExchangeModel.StockExchange.Company.Company;
import com.StockExchangeModel.StockExchange.Interpreter.Action;
import com.StockExchangeModel.StockExchange.Interpreter.ActionHandler;
import com.StockExchangeModel.StockExchange.StockExchange;
import com.StockExchangeModel.StockExchange.Trader.Trader;

import java.util.ArrayList;

public class OrderActionHandler extends ActionHandler {
    public OrderActionHandler(StockExchange context) {
        super(context, "ORDER");
    }

    public String[] handleAction(Action action) {
        ArrayList<String> ret = new ArrayList<>();
        String[] arguments = action.arguments;
        switch (action.actionType) {
            case SHOW_ALL:
                ret.add("* SHOWING ALL ORDERS *");
                ArrayList<Order> orders = context.getOrders();
                if (orders.size() > 0) {
                    for (Order o : orders) {
                        ret.add(o.toString());
                    }
                } else {
                    ret.add("No orders staged in '" + context.getName() + "'");
                }
                break;

            case EXECUTE_ALL:
                ret.add("* EXECUTING ORDERS *");
                ArrayList<Transaction> transactions = context.executeOrders();
                if (transactions != null && transactions.size() > 0) {
                    for(Transaction t : transactions) {
                        ret.add("Executed " + t.toString());
                    }
                }
                assert transactions != null;
                ret.add(transactions.size() + " order(s) executed in total");
                break;

            case STAGE:
                if (arguments.length >= 5) {
                    long id = Long.parseLong(arguments[0]);
                    String ticker = arguments[1];
                    String type = arguments[2];
                    int quantity = Integer.parseInt(arguments[3]);
                    double rate = Double.parseDouble(arguments[4]);
//                    ret.add("* STAGING ORDER *");
                    Trader t = context.getTrader(id);
                    Company c = context.getCompany(ticker);
                    if (t != null) {
                        if (c != null) {
                            Order o = new Order(t, c.getStock(), type, quantity, rate);
                            if (ret.add(context.stageOrder(o))) {
                                break;
                            }
                        } else {
                            ret.add("Company with ticker '" + ticker + "' not found");
                        }
                    } else {
                        ret.add("Trader with id '" + id + "' not found");
                    }
                    ret.add("Failed to stage order");
                }
                ret.add("Usage: ORDER STAGE trader_id? stock_ticker? type(BUY/SELL)? quantity? rate?");
                break;

            default:
                ret.add("Usage:");
                ret.add("  ORDER SHOW_ALL");
                ret.add("  ORDER EXECUTE_ALL");
                ret.add("  ORDER STAGE trader_id? stock_ticker? type(BUY/SELL)? quantity? rate?");
        }
        return ret.toArray(new String[0]);
    }
}