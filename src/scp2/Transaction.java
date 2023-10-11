package scp2;

import java.util.Date;

/**
 *
 * @author xxg8089,rth8619
 */

public class Transaction {
    // define necessary variables for creating and recording transactions
    private final Product product;
    private final TransactionType transactionType;
    private final int quantity;
    private final Date transactionDate;

    public Transaction(Product product, TransactionType transactionType, int quantity) {
        this.product = product;
        this.transactionType = transactionType;
        this.quantity = quantity;
        //set date to date at point transaction is created 
        this.transactionDate = new Date();
    }

    public Product getProduct() {
        return product;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        String typeString = transactionType == TransactionType.BUY ? "Bought" : "Sold";
        return typeString + " " + quantity + " " + product.getName() + "(s) on " + transactionDate;
    }
}
