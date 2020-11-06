package com.StockExchangeModel.StockExchange.Trader;

import com.StockExchangeModel.StockExchange.Company.Company;
import com.StockExchangeModel.StockExchange.Interpreter.Action;
import com.StockExchangeModel.StockExchange.Interpreter.ActionHandler;
import com.StockExchangeModel.StockExchange.StockExchange;

import java.util.ArrayList;

public class TraderActionHandler extends ActionHandler {
    public TraderActionHandler(StockExchange context) {
        super(context, "TRADER");
    }

    public String[] handleAction(Action action) {
        ArrayList<String> ret = new ArrayList<>();
        String[] arguments = action.arguments;
        switch (action.actionType) {
            case SHOW:
                if (arguments.length == 1) {
                    long id = Long.parseLong(arguments[0]);
//                    ret.add("* SHOWING TRADER *");
                    Trader t = context.getTrader(id);
                    if (t != null) {
                        ret.add(t.toString());
                    } else {
                        ret.add("Trader with id '" + id + "' isn't registered with '" + context.getName() + "'");
                    }
                } else {
                    ret.add("Usage: TRADER SHOW id?");
                }
                break;

            case SHOW_ALL:
                ret.add("* SHOWING ALL TRADERS *");
                ArrayList<Trader> traders = context.getTraders();
                if (traders.size() > 0) {
                    for (Trader t : traders) {
                        ret.add(t.toString());
                    }
                } else {
                    ret.add("No traders registered with '" + context.getName() + "'");
                }
                break;

            case ADD:
                if (arguments.length == 2) {
                    String name = arguments[0];
                    double funds = Double.parseDouble(arguments[1]);
//                    ret.add("* ADDING TRADER *");
                    Trader t = new Trader(name, funds);

                    if(context.addTrader(t)) {
                        ret.add("Added " + t.toString());
                        break;
                    } else {
                        ret.add("Failed to add trader '" + name + "'");
                    }
                }
                if (arguments.length >= 3) {
                    String name = arguments[0];
                    double funds = Double.parseDouble(arguments[1]);
//                    ret.add("* ADDING TRADER *");
                    Trader t = new Trader(name, funds);

                    StringBuilder holdingsStringBuilder = new StringBuilder();
                    for (int i = 2; i < arguments.length; i++) {
                       holdingsStringBuilder.append(arguments[i]);
                       holdingsStringBuilder.append(" ");
                    }
                    String holdingsString = holdingsStringBuilder.toString();
                    holdingsString = holdingsString.trim().toUpperCase().replaceAll("[^&\\w]", " ");
                    String[] words = holdingsString.trim().split("\\s+");

                    if (words.length >= 2 && words.length % 2 == 0) {
                        for (int i = 0; i < words.length; i+=2) {
                            Company c = context.getCompany(words[i]);
                            if (c != null) {
                                boolean temp = t.putHolding(context.getCompany(words[i]).getStock(), Integer.parseInt(words[i + 1]));
                                if (temp) {
                                    continue;
                                }
                            }
                            ret.add("Failed to add holding {" + words[i] + ": " + words[i+1] + "}");
                        }

                        if(context.addTrader(t)) {
                            ret.add("Added " + t.toString());
                            break;
                        } else {
                            ret.add("Failed to add trader '" + name + "'");
                        }
                    }
                }
                ret.add("Usage: TRADER ADD name? funds? holdings(format:\"{ticker: quantity, ticker2: quantity2, ...}\")?");
                break;

            case DELETE:
                if (arguments.length == 1) {
                    long id = Long.parseLong(arguments[0]);
//                    ret.add("* DELETING TRADER *");
                    Trader t = context.getTrader(id);
                    if (t != null) {
                        if(context.deleteTrader(t)){
                            ret.add("Deleted " + t.toString());
                        } else {
                            ret.add("Failed to delete trader '" + id + "'");
                        }
                    } else {
                        ret.add("Trader with id '" + id + "' isn't registered with '" + context.getName() + "'");
                    }
                } else {
                    ret.add("Usage: TRADER DELETE id?");
                }
                break;

            default:
                ret.add("Usage:");
                ret.add("  TRADER SHOW id?");
                ret.add("  TRADER SHOW_ALL");
                ret.add("  TRADER ADD name? funds? holdings(format:\"{ticker:quantity, ticker2:quantity2}\")?");
                ret.add("  TRADER DELETE id?");
        }
        return ret.toArray(new String[0]);
    }
}