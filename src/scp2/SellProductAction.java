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
    
    public SellProductAction(InventoryManager inventoryManager, JTextArea textArea){
        this.inventoryManager = inventoryManager;
        this.textArea = textArea;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = JOptionPane.showInputDialog("Enter product name to sell:");
        if(productName != null && !productName.isEmpty()){
            Product existingProduct = inventoryManager.findProductByName(productName);
            if(existingProduct != null){
                String quantityString = JOptionPane.showInputDialog("Enter quantity to sell:");
                if(quantityString != null && !quantityString.isEmpty()){
                    try{
                        int quantity = Integer.parseInt(quantityString);
                        
                        if(quantity > 0) { 
                            inventoryManager.sellProduct(existingProduct, quantity);
                            
                            //Save the stock data to the text file
                            inventoryManager.saveStockToFile();
                        } else{
                            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid integer.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
                        }
                } 
            }
        } else{
            JOptionPane.showMessageDialog(null, "Product not found in stock.", "Sell Product Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
