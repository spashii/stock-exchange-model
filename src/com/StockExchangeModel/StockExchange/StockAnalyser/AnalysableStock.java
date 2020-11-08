package com.StockExchangeModel.StockExchange.StockAnalyser;

import com.StockExchangeModel.StockExchange.Company.Stock;
import com.StockExchangeModel.StockExchange.Interpreter.Parser;

import java.util.Date;

public class AnalysableStock extends Stock implements Comparable {
   Date date;
   double previousClosePrice;
   double lastPrice;

   public AnalysableStock(String dateString, String ticker, double openPrice, double closePrice, double lowPrice, double highPrice, double lastPrice, double previousClosePrice) {
      super(ticker, openPrice, closePrice, lowPrice, highPrice);
      this.date = Parser.parseDate(dateString);
      this.previousClosePrice = previousClosePrice;
      this.lastPrice = lastPrice;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public double getPreviousClosePrice() {
      return previousClosePrice;
   }

   public void setPreviousClosePrice(double previousClosePrice) {
      this.previousClosePrice = previousClosePrice;
   }

   public double getLastPrice() {
      return lastPrice;
   }

   public void setLastPrice(double lastPrice) {
      this.lastPrice = lastPrice;
   }

   @Override
   public String toString() {
      return "AnalysableStock{" +
              "date=" + date +
              ", previousClosePrice=" + previousClosePrice +
              ", lastPrice=" + lastPrice +
              ", "+ super.toString() +
              '}';
   }

   @Override
   public int compareTo(Object o) {
      if (o instanceof AnalysableStock) {
         AnalysableStock a = (AnalysableStock) o;
         return this.date.compareTo(a.date);
      }
      return -1;
   }

   public static int compare(AnalysableStock a1, AnalysableStock a2) {
      return a1.compareTo(a2);
   }
}
