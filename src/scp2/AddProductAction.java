package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author hallowseph(xxg8089)
 * 
 */
public class AddProductAction implements ActionListener {
    private Connection connection;
    private JTextArea textArea;
    
    public AddProductAction(Connection connection, JTextArea textArea){
        this.connection = connection;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //JComboBox with product type options
            String[] productTypes = {"Grocery", "Electronics"};
            JComboBox<String> typeComboBox = new JComboBox<>(productTypes);
            
            //Show a dialog for selecting the product type
            int typeChoice = JOptionPane.showConfirmDialog(
                null, typeComboBox, "Select product type", 
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            
            if(typeChoice != JOptionPane.OK_OPTION){
                //if user cancels the selection or closes the dialog
                return;
            }
            
            //Get the selected product type
            String productType = (String) typeComboBox.getSelectedItem();
            
            //Get user input for product ID
            int productId = -1; // initialized as non-valid value
            boolean uniqueProductId = false;
            
            while(!uniqueProductId){
                String productIdStr = JOptionPane.showInputDialog("Enter a unique Product ID:");
                
                if(productIdStr == null){
                    //if user cancels
                    return;
                }
                
                try{
                    productId = Integer.parseInt(productIdStr);
                    
                    //Check if the product ID is unique
                    if(!isProductIdUnique(productId)){
                        JOptionPane.showMessageDialog(null, "Product ID must be unique. This ID is already in use.");
                    }else{
                        uniqueProductId = true;
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Product ID.");
                }
            }
            
            //Get user inputs
            String productName = JOptionPane.showInputDialog("Enter Product Name:");
            String priceStr = JOptionPane.showInputDialog("Enter Price:");
            String quantityStr = JOptionPane.showInputDialog("Enter Quantity");
            
            //Check for empty fields
            if(productName.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()){
                JOptionPane.showMessageDialog(null, "All fields must be filled.");
                return;
            }
            
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            
            //quantity and price must be greater than 0
            if(price <=0 || quantity <= 0){
                JOptionPane.showMessageDialog(null, "Price and quantity must be greater than 0.");
                return;
            }
            
            //Insert the product into the database
            String query = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, productType);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, quantity);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            //Display success message
            JOptionPane.showMessageDialog(null, "Product added successfully!");
        }catch(NumberFormatException | SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to add the product. Please check your input.");
        }
    }
    private boolean isProductIdUnique(int productId) throws SQLException{
        String query = "SELECT Product_ID FROM Products WHERE Product_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isUnique = !resultSet.next();//if the resultSet is empty, the ID is unique
        preparedStatement.close();
        return isUnique;
    }
}


