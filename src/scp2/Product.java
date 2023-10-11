package scp2;

/**
 *
 * @author xxg8089,rth8619
 */



public abstract class Product {
    private final String name;
    private int quantity;

    public Product(String name, int quantity) {
        // Initialize variables all types of products have in common
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Abstract method to be implemented by subclasses 
    public abstract void displayDetails();
}
