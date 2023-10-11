package scp2;

/**
 *
 * @author xxg8089,rth8619
 */

import java.util.Calendar;
import java.util.Date; // Please note the suggestion for the Date library was made by ChatGpt, however the functionality as far as we needed it was self-explored

public class GroceryProduct extends Product {
    private final Date expirationDate;

    public GroceryProduct(String name, Date dateAcquired, int quantity) {
         // Call the constructor of the parent class (Product) and initialize the expiration date
        super(name, quantity);
        
        // Calculate the expiration date based on the date acquired
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAcquired);
        int daysToAdd = 14 + (int) (Math.random() * 7); // Random days between 14 and 20 as Math.Random() will produce a random double between 0.0 (inclusive) and 1.0 (exclusive)
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        expirationDate = calendar.getTime();
    }   
    
    // second constructor is ordered differently in order to manually pass in and set an exipration date
    public GroceryProduct(String name,int quantity, Date expirationDate ) {
        super(name, quantity);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Grocery Product - Name: " + getName() + ", Expiration Date: " + expirationDate + ", Quantity: " + getQuantity());
    }
}
