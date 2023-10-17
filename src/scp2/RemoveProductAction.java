package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 * @author xxg8089
 */
public class RemoveProductAction implements ActionListener {

    private InventoryManager inventoryManager;
    private JTextArea textArea;

    public RemoveProductAction(InventoryManager inventoryManager, JTextArea textArea) {
        this.inventoryManager = inventoryManager;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = null;
        while (true) {
            productName = JOptionPane.showInputDialog("Enter product name to remove:");

            if (productName == null) {
                //if user cancels the input, exit the loop
                return;
            }

            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (inventoryManager.findProductByName(productName) == null) {
                JOptionPane.showMessageDialog(null, "Product not found in stock.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;//if valid product name is provided, exit loop
            }
        }
        inventoryManager.removeProduct(productName);

        //Save the stock data to text files
        inventoryManager.saveStockToFile();

        //Display a message dialog to notify the user
        JOptionPane.showMessageDialog(null,
                productName + " removed from stock.\nGrocery stock data saved to GroceryStock.txt\nElectronics stock data saved to ElectronicsStock.txt",
                "Product Removed",
                JOptionPane.INFORMATION_MESSAGE);
    }
}


