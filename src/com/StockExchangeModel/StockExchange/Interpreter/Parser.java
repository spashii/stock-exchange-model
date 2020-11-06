package com.StockExchangeModel.StockExchange.Interpreter;

public class Parser {
    public static String[] parseLine(String s) {

        return s.trim().toUpperCase().split("\\s+");
    }
}
