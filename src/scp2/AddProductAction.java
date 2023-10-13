package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 * @author xxg8089
 */
public class AddProductAction implements ActionListener {
    private InventoryManager inventoryManager;
    private JTextArea textArea;
    
    public AddProductAction(InventoryManager inventoryManager, JTextArea textArea){
        this.inventoryManager = inventoryManager;
        this.textArea = textArea;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] productTypes = {"Grocery", "Electronics"};
        String selectedProductType = (String) JOptionPane.showInputDialog(
                null,
                "Select a product type:",
                "Add Product",
                JOptionPane.QUESTION_MESSAGE,
                null,
                productTypes,
                productTypes[0]
        );
        
        if(selectedProductType !=null){
            String productName = JOptionPane.showInputDialog("Enter product name:");
            if (productName != null && !productName.isEmpty()) {
                Product productToAdd = null;
                
                if(selectedProductType.equals("Grocery")){
                    productToAdd = new GroceryProduct(productName, new Date(), 0);
                } else if (selectedProductType.equals("Electronics")){
                    String skuCode = JOptionPane.showInputDialog("Enter SKU code:");
                    if(skuCode != null && !skuCode.isEmpty()){
                        productToAdd = new ElectronicsProduct(productName, skuCode, 0);
                    }
                }
                
                if(productToAdd != null){
                    inventoryManager.addProduct(productToAdd);
                    
                    //Display the newly added product
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    
                    //Save the stock data to text files
                    inventoryManager.saveStockToFile();
                    
                    //Message dialog
                    JOptionPane.showMessageDialog(null,
                            productName + " added to stock.\nGrocery stock data saved to GroceryStock.txt\nElectronics stock data saved to ElectronicsStock.txt",
                            "Product Added",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
}
