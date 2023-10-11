package scp2;

/**
 *
 * @author xxg8089,rth8619
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryManager implements Action {

    private Map<Product, Date> stockMap;
    private List<Transaction> transactions;
    private FileHandler fileHandler;
    private Scanner keyboard = new Scanner(System.in);
    private SortedStock sortedStock;
    
    public InventoryManager() {
        // Initialize data structures and load initial stock from files
        stockMap = new HashMap<>();
        transactions = new ArrayList<>();
        fileHandler = new FileHandler();
        sortedStock = new SortedStock();
        try {
            loadInitialStockFromFile(); // Load initial stock from file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading initial stock from file: " + e.getMessage());
        }
    }

    @Override
   public void addProduct(Product product) {
         if (product instanceof ElectronicsProduct) {  // Handle ElectronicsProduct-specific logic, such as checking for duplicate SKU codes
        ElectronicsProduct electronicProduct = (ElectronicsProduct) product;

        while (true) {
            // please note this boolean p -> p expression was written by ChatGPT
            boolean skuCodeTaken = stockMap.keySet().stream()
                    .anyMatch(p -> p instanceof ElectronicsProduct && ((ElectronicsProduct) p).getSkuCode().equals(electronicProduct.getSkuCode())); // Goes through stockmap and chek if SKU code is already there

            if (skuCodeTaken) {
                System.out.println("SKU Code is already taken. Please enter a different SKU Code:");
                String newSkuCode = keyboard.nextLine();
                electronicProduct.setSkuCode(newSkuCode);
            } else {
                break; // Unique SKU Code provided, exit loop
            }
        }
    } else if (product instanceof GroceryProduct) {
        GroceryProduct groceryProduct = (GroceryProduct) product;

        // Check if the name of the grocery product already exists
        boolean nameTaken = stockMap.keySet().stream() // please note this boolean p -> p expression was written by ChatGPT
                .anyMatch(p -> p instanceof GroceryProduct && p.getName().equalsIgnoreCase(groceryProduct.getName()));

        if (nameTaken) {
            System.out.println("A grocery product with the same name already exists. Please enter a different name.");
            return; // Exit the method without adding the product
        }
    }
        // add new product
        stockMap.put(product, new Date());
        sortedStock.add(product);
        System.out.println(product.getName() + " added to stock.");
    }

    @Override
    public void removeProduct(String productName) {
        Product productToRemove = null;
        for (Product product : stockMap.keySet()) { //check if there is a product matching the same name in stockMap
            if (product.getName().equalsIgnoreCase(productName)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) { // if there is a matching product removes it 
            stockMap.remove(productToRemove);
            System.out.println(productName + " removed from stock.");
        } else { 
            System.out.println(productName + " not found in stock.");
        }
    }

    @Override
    public void buyProduct(Product existingProduct, int quantity) {
        if (existingProduct != null) { 
            int currentQuantity = existingProduct.getQuantity();
            existingProduct.setQuantity(currentQuantity + quantity); //add to existing quantity instead of over-writing it
            // adds a new transaction with all arguments you would expect to desribe a transaction (barring price as this is not a feature of inventory management in our case)
            transactions.add(new Transaction(existingProduct, TransactionType.BUY, quantity)); 
            System.out.println(quantity + " " + existingProduct.getName() + "(s) bought. Current quantity: " + existingProduct.getQuantity());
        } else {
            System.out.println("Product not found in stock.");
        }
    }

    @Override
    public void sellProduct(Product existingProduct, int quantity) { // similar functionality to buy product but checks quantity as well to incur a valid transaction
        if (existingProduct != null) {
            int currentQuantity = existingProduct.getQuantity();
            if (currentQuantity >= quantity) {
                existingProduct.setQuantity(currentQuantity - quantity);
                transactions.add(new Transaction(existingProduct, TransactionType.SELL, quantity));
                System.out.println(quantity + " " + existingProduct.getName() + "(s) sold. Current quantity: " + existingProduct.getQuantity());
            } else {
                System.out.println("Insufficient stock of " + existingProduct.getName() + " to sell.");
            }
        } else {
            System.out.println("Product not found in stock.");
        }
    }
    
    public void displayStockSeparated() {   // creates a list, sorts it, outputs it in sections
        SortedStock sortedStock = new SortedStock();
        sortedStock.getSortedStock().addAll(stockMap.keySet());
        sortedStock.sortByProductType();
        
        System.out.println("Grocery Products: (Sorted by Name)");
        for (Product product : sortedStock.getSortedStock()) {
            if (product instanceof GroceryProduct) {
                product.displayDetails();
            }
        }

        System.out.println("\nElectronics Products:(Sorted by SKU code)");
        for (Product product : sortedStock.getSortedStock()) {
            if (product instanceof ElectronicsProduct) {
                product.displayDetails();
            }
        }
    }

    private void loadInitialStockFromFile() throws IOException, ClassNotFoundException { // load stock items from text files into local map, places loaded stock into instance var stockMap
        Map<Product, Date> groceryStock = fileHandler.readGroceryStock("./resources/GroceryStock.txt");
        stockMap.putAll(groceryStock);
        Map<Product, Date> electronicsStock = fileHandler.readElectronicsStock("./resources/ElectronicsStock.txt");
        stockMap.putAll(electronicsStock);

    }

    public void saveStockToFile() { // uses write functionality in FileHandler class to save stock levels to text files 
        fileHandler.writeGroceryStock("./resources/GroceryStock.txt", stockMap);
        System.out.println("Grocery stock data saved to GroceryStock.txt");
        fileHandler.writeElectronicsStock("./resources/ElectronicsStock.txt", stockMap);
        System.out.println("Electronics stock data saved to ElectronicsStock.txt");

    }

    public Product findProductByName(String productName) { // used to check map for an existing product (not case sensitive)
        for (Product product : stockMap.keySet()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;

    }

}
