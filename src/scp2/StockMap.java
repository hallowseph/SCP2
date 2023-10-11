package scp2;

/**
 *
 * @author xxg8089,rth8619
 */


import java.util.Map;
import java.util.Date;
import java.util.HashMap;

public class StockMap  {
    private final Map<Product, Date> stockMap;

    // create empty stockMap
    public StockMap() {
        stockMap = new HashMap<>();
    }
    
    // create stockMap with initial values
    public StockMap(Map<Product, Date> initialStock) {
        stockMap = new HashMap<>(initialStock);
    }
    
    // getter
    public Map<Product, Date> getStockMap() {
        return stockMap;
    }
    
    // determine the number of items in stock
    public int StockCount()
    {
        int totalStock = 0; 
        for (Product product: this.stockMap.keySet()){
            totalStock += product.getQuantity();   
        }
        return totalStock; 
    }
   
}
