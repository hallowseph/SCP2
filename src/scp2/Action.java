package scp2;

/**
 *
 * @author xxg8089,rth8619
 */

// The Action interface defines methods that can be performed on inventory items. They are all later used in the inventory manager
public interface Action {
    void addProduct(Product product);
    void removeProduct(String productName);
    void buyProduct(Product product, int quantity);
    void sellProduct(Product product, int quantity);

}
