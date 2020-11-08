package com.StockExchangeModel;

import com.StockExchangeModel.StockExchange.Interpreter.Interpreter;
import com.StockExchangeModel.StockExchange.StockAnalyser.AnalysableStock;
import com.StockExchangeModel.StockExchange.StockAnalyser.StockAnalyser;
import com.StockExchangeModel.StockExchange.StockExchange;

import java.util.Scanner;

public class Demo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter name of Stock Exchange: ");
        System.out.print(">> ");
        System.out.println("");
        String stockExchangeName = scanner.nextLine().trim();
        StockExchange se = new StockExchange(stockExchangeName);
        Interpreter interpreter = new Interpreter(se);
        int control;
        do {
            System.out.println(se.getName() + " (STOCK EXCHANGE SERVICES)");
            System.out.println("Please choose: ");
            System.out.println("1. Start Interpreter");
            System.out.println("2. Load Input File");
            System.out.println("3. Load Sample Data and Start Interpreter");
            System.out.println("4. Load Input CSV to Analyse");
            System.out.println("5. Exit");
            System.out.print(">> ");
            control = scanner.nextInt();
            switch (control) {
                case 3:
                    se = new StockExchange(stockExchangeName);
                    interpreter = new Interpreter(se);
                    interpreter.interpretFile("sample.txt");
                case 1:
                    System.out.println("");
                    System.out.println(se.getName() + " INTERPRETER");
                    System.out.println("Type 'help' to check usages");
                    interpreter.startSession("$");
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("");
                    System.out.println("Please enter input filename(use input_1.txt for demo):");
                    System.out.print(">> ");
                    String filename = scanner.next().trim();
                    System.out.println("");
                    System.out.println(se.getName() + " FILE INTERPRETER");
                    Interpreter.printInterpretedResults(interpreter.interpretFile(filename));
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("");
                    System.out.println("Please enter input filename(use input_2.csv for demo):");
                    System.out.print(">> ");
                    String filename2 = scanner.next().trim();
                    System.out.println(se.getName() + " FILE INTERPRETER");
                    StockAnalyser stockAnalyser = new StockAnalyser();
                    stockAnalyser.addAnalysableStocksFromCSV(filename2);
                    for (AnalysableStock a : stockAnalyser.getAnalysableStocks()) {
                        System.out.println("Loaded " + a.toString());
                    }
                    System.out.println("");
                    System.out.println("ANALYSIS RESULTS");
                    StockAnalyser.printAnalysedResults(stockAnalyser.analyse());
                    System.out.println("");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("");
                    System.out.println("Please choose a valid option");
            }
        } while (control != 5);
    }
}
