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
        if (productName != null && !productName.isEmpty()) {
            Product existingProduct = inventoryManager.findProductByName(productName);
            if (existingProduct != null) {
                String quantityString = JOptionPane.showInputDialog("Enter quantity to buy:");
                if (quantityString != null && !quantityString.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityString);

                        if (quantity > 0) {
                            inventoryManager.buyProduct(existingProduct, quantity);

                            //Save the stock data to text files
                            inventoryManager.saveStockToFile();
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid integer.", "Buy Product Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in stock.", "Buy Product Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
