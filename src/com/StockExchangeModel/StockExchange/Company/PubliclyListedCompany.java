package com.StockExchangeModel.StockExchange.Company;

public class PubliclyListedCompany {
    String name;
    int categoryIndex;
    Stock stock;

    static String[] categoryList = {"Miscellaneous", "Pharma", "Consumer Goods", "Automobiles", "IT", "Infrastructure", "Finance", "Banking"};

    static int getCategoryIndex(String c) {
        for(int i = 1; i<categoryList.length; i++) {
            if (categoryList[i].compareTo(c) == 0) {
                return i;
            }
        }
        return 0;
    }

    public String getCategory() {
        return categoryList[this.categoryIndex];
    }

    public PubliclyListedCompany(String name, String ticker, String category, double openPrice, double closePrice, double lowPrice, double highPrice) {
        this.name = name;
        this.categoryIndex = getCategoryIndex(category);
        this.stock = new Stock(ticker, openPrice, closePrice, lowPrice, highPrice);
        System.out.println("Added " + this.toString());
    }

    @Override
    public String toString() {
        return "PubliclyListedCompany{" +
                "name='" + this.name + '\'' +
                ", category='" + this.getCategory() + '\'' +
                ", stock=" + this.stock.toString() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
