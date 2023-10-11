package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 * @author xxg8089
 */
public class DisplayStockAction implements ActionListener {

        private JTextArea textArea;
        
        public DisplayStockAction(JTextArea textArea){
            this.textArea = textArea;
        }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        //Read the data from text files
        FileHandler fileHandler = new FileHandler();
        
        //Read and display  Grocery products
        Map<Product, Date> groceryStockMap = fileHandler.readGroceryStock("C:\\Users\\josep\\Documents\\NetBeansProjects\\SCP2\\resources\\GroceryStock.txt");
        List<Product> groceryProducts = new ArrayList<>(groceryStockMap.keySet());
        groceryProducts.sort((product1,product2) ->{
            if(product1 instanceof GroceryProduct && product2 instanceof GroceryProduct){
                String name1 = ((GroceryProduct) product1).getName().toLowerCase();
                String name2 = ((GroceryProduct) product2).getName().toLowerCase();
                return name1.compareTo(name2);
            }
            return 0;
        });
        textArea.append("Grocery Products: (Sorted by Name)\n");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        
        for(Product product : groceryProducts){
            Date dateAcquired = groceryStockMap.get(product);
            textArea.append("Grocery Product - Name: " + product.getName() + ", Expiration Date: "
                    + dateFormat.format(((GroceryProduct) product).getExpirationDate()) + ", Quantity: "
                    + product.getQuantity() + "\n");
        }
        
        //Read and display Electronics products
        Map<Product, Date> electronicsStockMap = fileHandler.readElectronicsStock("C:\\Users\\josep\\Documents\\NetBeansProjects\\SCP2\\resources\\ElectronicsStock.txt");
        List<Product> electronicsProducts = new ArrayList<>(electronicsStockMap.keySet());
        electronicsProducts.sort((product1,product2) ->{
            if(product1 instanceof ElectronicsProduct && product2 instanceof ElectronicsProduct){
                String name1 = ((ElectronicsProduct) product1).getName().toLowerCase();
                String name2 = ((ElectronicsProduct) product2).getName().toLowerCase();
                return name1.compareTo(name2);
            }
            return 0;
        });
        textArea.append("\nElectronics Products: (Sorted by SKU Code)\n");
        
        for(Product product : electronicsProducts){
            Date dateAcquired = electronicsStockMap.get(product);
            if(product instanceof ElectronicsProduct){
                textArea.append("Electronics Product - Name: " + product.getName() + ", SKU Code: "
                        + ((ElectronicsProduct) product).getSkuCode() + ", Quantity: "
                        + product.getQuantity() + "\n");
            }
        }
    }
    
}
