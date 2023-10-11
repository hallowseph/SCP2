package scp2;

/**
 *
 * @author xxg8089,rth8619
 */

import java.io.*;
import java.text.ParseException; // found on StackOverflow
import java.text.SimpleDateFormat; // Suggested library by chat GPT
import java.util.Date; // Library suggested by Chat GPT
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    
    // Define a date format for parsing and formatting dates, if the text withing the new SimpleDateFormat() was different, the method from SimpleDateFormat library would not be able to parse the date as the Date library constructs it
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); // This line of code was taken from Chat GPT, struggled with getting dates to read properly and turned to Chat GPT after I could not find anything on Stack Overflow/similar websites
    
    // accepts the name of the text document as an argument and uses a file reader to to get the text within
    public Map<Product, Date> readGroceryStock(String filename) {
    Map<Product, Date> stockMap = new HashMap<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");


    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Name,ExpirationDate,Quantity,DateAcquired")) { // as the first  line is just a decriptor for a user that might read the text document, we skip it
                continue; // Skip the header line
            }
            
            // logic here is to create a String array for each of the different components necessary for a product, then to parse each into its desired variable type
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String name = parts[0];
                String expirationDateStr = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                String dateAcquiredStr = parts[3];

                Date expirationDate = dateFormat.parse(expirationDateStr);
                Date dateAcquired = dateFormat.parse(dateAcquiredStr);
                
                // create a product using the constructor that automatically sets expiration date as it is read instead of calculating it
                GroceryProduct product = new GroceryProduct(name,  quantity, expirationDate);
                stockMap.put(product, dateAcquired);
            }
        }
    } catch (IOException | ParseException e) { 
        System.out.println("File could not be read successfully.");
    }

    return stockMap;
}

    public void writeGroceryStock(String filename, Map<Product, Date> stockMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name,ExpirationDate,Quantity,DateAcquired");
            writer.newLine();

            // for each item in the stockMap under Grocery section write into text file
            for (Map.Entry<Product, Date> entry : stockMap.entrySet()) {
                Product product = entry.getKey();
                Date dateAcquired = entry.getValue();
                if (product instanceof GroceryProduct) {
                    writer.write(product.getName() + "," +
                            dateFormat.format(((GroceryProduct) product).getExpirationDate()) + "," +
                            product.getQuantity() + "," +
                            dateFormat.format(dateAcquired));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("File was not written succesfully.");
        }
    }

    public Map<Product, Date> readElectronicsStock(String filename) { // functionality similar to readGroceryStock, but tailored to SKU code and doesnt include expiration date
    Map<Product, Date> stockMap = new HashMap<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Name,SKUCode,Quantity,DateAcquired")) {
                continue; // Skip the header line
            }
            
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String name = parts[0];
                String skuCode = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                String dateAcquiredStr = parts[3];

                Date dateAcquired = dateFormat.parse(dateAcquiredStr);
                
                ElectronicsProduct product = new ElectronicsProduct(name, skuCode, quantity);
                stockMap.put(product, dateAcquired);
            }
        }
    } catch (IOException | ParseException e) {
        System.out.println("Could not read file successfully.");
    }

    return stockMap;
}
    
    // similar logic to writeGroceryStock, but adapted for SKU codes
    public void writeElectronicsStock(String filename, Map<Product, Date> stockMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name,SKUCode,Quantity,DateAcquired");
            writer.newLine();

            for (Map.Entry<Product, Date> entry : stockMap.entrySet()) {
                Product product = entry.getKey();
                Date dateAcquired = entry.getValue();
                if (product instanceof ElectronicsProduct) {
                    writer.write(product.getName() + "," +
                            ((ElectronicsProduct) product).getSkuCode() + "," +
                            product.getQuantity() + "," +
                            dateFormat.format(dateAcquired));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("File was not written successfully. ");
        }
    }
}

 