# stock-exchange-model

This is an implementation of a model ‘Stock Exchange’. It is capable of registering ‘Companies’ and ‘Traders’. Traders can interact with each other by creating ‘Orders’. Orders can be executed to facilitate ‘Stock’ trade between the Traders.

## Interpreter Usage
* `COMPANY`
  * `SHOW` - Shows company with ticker ‘ticker’
  * `SHOW_ALL` - Shows all registered companies
  * `SHOW_CATEGORY` - Shows all companies with category
  * `ADD` - Adds and registers a company
  * `DELETE` - Deletes and unregisters a company
* TRADER
  * `SHOW` - Shows trader
  * `SHOW_ALL` - Shows all registered traders
  * `ADD` - Adds and registers a trader
  * `DELETE` - Deletes and unregisters a trader
* `ORDER`
  * `SHOW_ALL` - Shows all staged orders
  * `EXECUTE_ALL` - Executes staged orders if conditions are met
  * `STAGE` - Adds and stages a new order

![StockExchangeModelUML](https://user-images.githubusercontent.com/63326129/98650627-2be18f80-235f-11eb-9bf4-ce2b6b279eac.png)

* Stock Exchanges implemented and can be added
* New Stocks with a unique ‘Ticker’ can be added
* Various details of a Stock can be queried
* Existing Stocks can be deleted
* New Traders with a unique ‘ID’ can be added
* Various details of a Trader can be queried
* Buy/Sell Orders can be placed by Traders on Stocks 
* Orders can be executed when conditions are favourable
* Stocks are be classified into Categories
* Stock with a Category can be queried
* Upper/Lower Circuit violations are handled
* Input files are handled and interpretable
* Stock data over a period of time can be analysed to get average price, max drawdown, max percentage return potential
* Command Line Interpreter is available
