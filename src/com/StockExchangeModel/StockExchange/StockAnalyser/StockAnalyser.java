package com.StockExchangeModel.StockExchange.StockAnalyser;

import com.StockExchangeModel.StockExchange.Interpreter.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class StockAnalyser extends Parser {
    ArrayList<AnalysableStock> analysableStocks;

    public StockAnalyser() {
        this.analysableStocks = new ArrayList<>();
    }

    public ArrayList<AnalysableStock> getAnalysableStocks() {
        return analysableStocks;
    }

    public int addAnalysableStock(AnalysableStock a) {
        // adding in sorted position
        int index = Collections.binarySearch(this.analysableStocks, a, AnalysableStock::compareTo);
        if (index < 0) {
            index = ~index;
        }
        analysableStocks.add(index, a);
        return index;
    }

    public int addAnalysableStocksFromCSV(String filename) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // ignoring first line
            while ((line = br.readLine()) != null) {
                String[] CSVLine = parseCSVLine(line);
                if (CSVLine.length == 8) {
                    AnalysableStock a = new AnalysableStock(CSVLine[1], CSVLine[0], Double.parseDouble(CSVLine[3]), Double.parseDouble(CSVLine[7]), Double.parseDouble(CSVLine[5]), Double.parseDouble(CSVLine[4]), Double.parseDouble(CSVLine[6]), Double.parseDouble(CSVLine[2]));
                    addAnalysableStock(a);
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return count;
    }

    public String[] analyse() {
        // I could have done all these tasks in O(n^2) but
        // I didn't to make the calculations distinct and clear
        ArrayList<String> ret = new ArrayList<>();
        if (analysableStocks.size() > 2) {
            // calculating average price
            double average = 0.0;
            double sum = 0.0;
            for(AnalysableStock a: analysableStocks) {
                sum += a.getClosePrice();
            }
            average = sum/analysableStocks.size();
            ret.add("Average Stock Price = " + average);
            // calculating max drawdown
            double maxDrawdown = 0.0;
            double drawdown;
            for(int i = 0; i<analysableStocks.size(); i++)  {
                drawdown = 0.0;
                for(int j = i+1; j<analysableStocks.size(); j++) {
                    if(analysableStocks.get(j).getClosePrice() <= analysableStocks.get(i).getClosePrice()) {
                        drawdown += analysableStocks.get(i).getClosePrice() - analysableStocks.get(j).getClosePrice();
                        continue;
                    }
                    maxDrawdown = (drawdown>maxDrawdown) ? drawdown: maxDrawdown;
                    break;
                }
            }
            ret.add("Max Drawdown = " + maxDrawdown);
            // calculating max return potential percentage
            double returnPotential = 0.0;
            for(AnalysableStock a : analysableStocks) {
                returnPotential += Math.abs(a.getClosePrice() - a.getOpenPrice());
            }
            double maxReturnPotentialPercentage = 100 * (returnPotential/analysableStocks.get(0).getClosePrice());
            ret.add("Max Percentage Return Potential = " + maxReturnPotentialPercentage + " %");
        }
        else {
            ret.add("Not enough data to analyse");
        }
        return ret.toArray(new String[0]);
    }

    public static void printAnalysedResults(String[] results) {
        for (String s : results) {
            System.out.println(s);
        }
    }
}