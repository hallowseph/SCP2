package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author josep
 * @author xxg8089
 *
 */
public class BuyProductAction implements ActionListener {
    private Connection connection;
    private JTextArea textArea;

    public BuyProductAction(Connection connection, JTextArea textArea) {
        this.connection = connection;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //get user input for the product name or ID to buy more of
            String productIdStr = JOptionPane.showInputDialog("Enter Product ID or Name to buy more of:");
            if(productIdStr == null || productIdStr.isEmpty()){
                //if user cancels input or enters an empty value
                return;
            }
            
            //check if the entered value is a product ID or a name
            int productId = -1;
            boolean isProductId = false;
            
            try{
                productId = Integer.parseInt(productIdStr);
                isProductId = true;
            }catch(NumberFormatException ex){
                //
            }
            
            //find the product in the DB
            String query;
            if(isProductId){
                query = "SELECT * FROM Products WHERE Product_ID = ?";
            }else{
                query = "SELECT * FROM Products WHERE Product_Name = ?";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productIdStr);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                //product exists, get the current quantity and proceed to update it
                int existingQuantity = resultSet.getInt("Quantity");
                preparedStatement.close();
                
                //get the quantity to buy
                String quantityStr = JOptionPane.showInputDialog("Enter the quantity to buy:");
                if(quantityStr == null || quantityStr.isEmpty()){
                    //if user cancels or enters an empty value
                    return;
                }
                
                int quantityToAdd;
                try{
                    quantityToAdd = Integer.parseInt(quantityStr);
                    if(quantityToAdd <= 0){
                        JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.");
                        return;
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for quantity.");
                    return;
                }
                
                int newQuantity = existingQuantity + quantityToAdd;
                
                //update the quantity in the DB
                String updateQuery = "UPDATE Products SET Quantity = ? WHERE ";
                if(isProductId){
                    updateQuery += "Product_ID = ?";
                }else{
                    updateQuery += "Product_Name = ?";
                }
                
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newQuantity);
                updateStatement.setString(2, productIdStr);
                updateStatement.executeUpdate();
                updateStatement.close();
                
                //display success message
                JOptionPane.showMessageDialog(null, "Product quantity updated successfully!");
            }else{
                //if product does not exist
                JOptionPane.showMessageDialog(null, "Product not found in the inventory.");
            }
        }catch(NumberFormatException | SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to update product quantity. Please check your input.");
        }
    }
}
