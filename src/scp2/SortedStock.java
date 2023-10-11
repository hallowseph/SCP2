package scp2;

/**
 *
 * @author xxg8089,rth8619
 */

import java.util.*;

public class SortedStock {
    private final List<Product> sortedStock;
    
    public SortedStock() {
        sortedStock = new ArrayList<>();
    }

    // Add a product to the SortedStock collection.
    public void add(Product product) {
        sortedStock.add(product);
    }
    
    // Sort by name for Grocery and by SKU code for Electronics
    public void sortByProductType() {
    sortedStock.sort((product1, product2) -> {
        if (product1 instanceof GroceryProduct && product2 instanceof GroceryProduct) {
            String name1 = ((GroceryProduct) product1).getName().toLowerCase();
            String name2 = ((GroceryProduct) product2).getName().toLowerCase();
            return name1.compareTo(name2); // compare the strings we have set as the names of the grocery products being compared 
        } else if (product1 instanceof ElectronicsProduct && product2 instanceof ElectronicsProduct) {
            String skuCode1 = ((ElectronicsProduct) product1).getSkuCode();
            String skuCode2 = ((ElectronicsProduct) product2).getSkuCode();
            return skuCode1.compareTo(skuCode2); // compare strings we have set as the sku codes of products being compared
        } else if (product1 instanceof GroceryProduct) {
            return -1; // Grocery products come before Electronics products
        } else {
            return 1; // Electronics products come after Grocery products
        }
    });
}
    
    // function to return private instance variable 
    public List<Product> getSortedStock() {
        return sortedStock;
    }
}
