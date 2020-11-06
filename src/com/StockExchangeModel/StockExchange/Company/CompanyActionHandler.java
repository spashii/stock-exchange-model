package com.StockExchangeModel.StockExchange.Company;

import com.StockExchangeModel.StockExchange.Interpreter.Action;
import com.StockExchangeModel.StockExchange.Interpreter.ActionHandler;
import com.StockExchangeModel.StockExchange.StockExchange;

import java.util.ArrayList;

public class CompanyActionHandler extends ActionHandler {
    public CompanyActionHandler(StockExchange context) {
        super(context, "COMPANY");
    }

    public String[] handleAction(Action action) {
        ArrayList<String> ret = new ArrayList<>();
        String[] arguments = action.arguments;
        switch (action.actionType) {
            case SHOW:
                if (arguments.length == 1) {
                    String ticker = arguments[0];
//                    ret.add("* SHOWING COMPANY *");
                    Company c = context.getCompany(ticker);
                    if (c != null) {
                        ret.add(c.toString());
                    } else {
                        ret.add("Company with ticker '" + ticker + "' isn't registered with '" + context.getName() + "'");
                    }
                } else {
                    ret.add("Usage: COMPANY SHOW ticker?");
                }
                break;

            case SHOW_ALL:
                ret.add("* SHOWING ALL COMPANIES *");
                ArrayList<Company> companies = context.getCompanies();
                if (companies.size() > 0) {
                    for (Company c : companies) {
                        ret.add(c.toString());
                    }
                } else {
                    ret.add("No companies registered with '" + context.getName() + "'");
                }
                break;

            case SHOW_CATEGORY:
                if (arguments.length == 1) {
                    String category = arguments[0];
                    ret.add("* SHOWING '" + category + "' COMPANIES *");
                    ArrayList<Company> companiesCategory = context.getCompaniesByCategory(category);
                    if (companiesCategory.size() > 0) {
                        for (Company c : companiesCategory) {
                            ret.add(c.toString());
                        }
                    } else {
                        ret.add("No companies with category '" + category + "' registered with '" + context.getName() + "'");
                    }
                } else {
                    ret.add("Usage: COMPANY SHOW_CATEGORY category?");
                }
                break;

            case ADD:
                if (arguments.length == 7) {
                    String name = arguments[0];
                    String ticker = arguments[1];
                    String category = arguments[2];
                    double openPrice = Double.parseDouble(arguments[3]);
                    double closePrice = Double.parseDouble(arguments[4]);
                    double lowPrice = Double.parseDouble(arguments[5]);
                    double highPrice = Double.parseDouble(arguments[6]);
//                    ret.add("* ADDING COMPANY *");
                    Company c = new Company(name, ticker, category, openPrice, closePrice, lowPrice, highPrice);
                    if(context.addCompany(c)) {
                        ret.add("Added " + c.toString());
                        break;
                    }
                    ret.add("Failed to add company '" + ticker + "'");
                }
                ret.add("Usage: COMPANY ADD name? ticker? category? open_price? close_price? low_price? high_price?");
                break;

            case DELETE:
                if (arguments.length == 1) {
                    String ticker = arguments[0];
//                    ret.add("* DELETING COMPANY *");
                    Company c = context.getCompany(ticker);
                    if (c != null) {
                        if(context.deleteCompany(ticker)){
                            ret.add("Deleted " + c.toString());
                        } else {
                            ret.add("Failed to delete company '" + ticker + "'");
                        }
                    } else {
                        ret.add("Company with ticker '" + ticker + "' isn't registered with '" + context.getName() + "'");
                    }
                } else {
                    ret.add("Usage: COMPANY DELETE ticker?");
                }
                break;

            default:
                ret.add("Usage:");
                ret.add("  COMPANY SHOW ticker?");
                ret.add("  COMPANY SHOW_ALL");
                ret.add("  COMPANY SHOW_CATEGORY category?");
                ret.add("  COMPANY ADD name? ticker? category? open_price? close_price? low_price? high_price?");
                ret.add("  COMPANY DELETE ticker?");
        }
        return ret.toArray(new String[0]);
    }

}
