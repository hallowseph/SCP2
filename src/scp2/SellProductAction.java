package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author joseph
 * @author xxg8089
 */
public class SellProductAction implements ActionListener {

    private InventoryManager inventoryManager;
    private JTextArea textArea;

    public SellProductAction(InventoryManager inventoryManager, JTextArea textArea) {
        this.inventoryManager = inventoryManager;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName;
        while (true) {
            productName = JOptionPane.showInputDialog("Enter product name to sell:");

            if (productName == null) {
                //if user cancels input, exit the loop
                return;
            }

            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (isInteger(productName)) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;//if valid product name, exit loop
            }
        }

        Product existingProduct = inventoryManager.findProductByName(productName);
        if (existingProduct != null) {
            while (true) {
                String quantityString = JOptionPane.showInputDialog("Enter quantity to sell:");

                if (quantityString == null) {
                    //if user cancels the input, exit the loop
                    return;
                }

                if (quantityString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Quantity cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityString);

                        if (quantity > 0) {
                            inventoryManager.sellProduct(existingProduct, quantity);

                            //save the stock data to the text files
                            inventoryManager.saveStockToFile();
                            break; //if quantity is valid, exit loop
                        } else {
                            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid integer.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found in stock.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
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
