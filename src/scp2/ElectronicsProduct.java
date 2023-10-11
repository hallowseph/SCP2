package scp2;

/**
 *
 * @author xxg8089,rth8619
 */


public class ElectronicsProduct extends Product {
    private String skuCode;

    // constructor inherits parent instance vars and initializes relevant child instance var
    public ElectronicsProduct(String name, String skuCode, int quantity) {
        super(name, quantity);
        this.skuCode = skuCode;
    }

    // getters and setters
    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics Product - Name: " + getName() + ", SKU Code: " + skuCode + ", Quantity: " + getQuantity());
    }
}
