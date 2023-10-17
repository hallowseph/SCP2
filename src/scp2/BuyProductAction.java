package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 * @author xxg8089
 *
 */
public class BuyProductAction implements ActionListener {

    private InventoryManager inventoryManager;
    private JTextArea textArea;

    public BuyProductAction(InventoryManager inventoryManager, JTextArea textArea) {
        this.inventoryManager = inventoryManager;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = JOptionPane.showInputDialog("Enter product name to buy:");
        while (true) {
            productName = JOptionPane.showInputDialog("Enter product name to buy:");

            if (productName == null) {
                //if user cancels input, exit the loop
                return;
            }

            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (isInteger(productName)) {
                JOptionPane.showMessageDialog(null, "Product name cannot be an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;//if user input is valid, exit loop
            }
        }

        Product existingProduct = inventoryManager.findProductByName(productName);
        if (productName != null) {
            while (true) {
                String quantityString = JOptionPane.showInputDialog("Enter quantity to buy:");

                if (quantityString == null) {
                    //if user cancels input, exit the loop
                    return;
                }

                if (quantityString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Quantity cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityString);

                        if (quantity > 0) {
                            inventoryManager.buyProduct(existingProduct, quantity);

                            //save the stock data to text files
                            inventoryManager.saveStockToFile();
                            break; //if valid quantity, exit the loop 
                        } else {
                            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.", "Buy Product Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid integer.", "Buy Product Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found in stock.", "Buy Product Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
