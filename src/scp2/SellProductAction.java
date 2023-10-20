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
 * @author joseph
 * @author xxg8089
 */
public class SellProductAction implements ActionListener {

    private Connection connection;
    private JTextArea textArea;

    public SellProductAction(Connection connection, JTextArea textArea) {
        this.connection = connection;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //get user input for the product name or ID to sell
            String productIdStr = JOptionPane.showInputDialog("Enter Product ID or Name to sell:");
            if (productIdStr == null || productIdStr.isEmpty()) {
                //if user cancels or enters an empty value
                return;
            }

            //check if the entered value is a numeric product ID or a name
            int productId = -1;
            boolean isProductId = false;

            try {
                productId = Integer.parseInt(productIdStr);
                isProductId = true;
            } catch (NumberFormatException ex) {
                //
            }

            //find the existing product in the DB
            String query;
            if (isProductId) {
                query = "SELECT * FROM Products WHERE Product_ID = ?";
            } else {
                query = "SELECT * FROM Products WHERE Product_Name = ?";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productIdStr);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //product exists, get the current quantity and proceed to sell
                int existingQuantity = resultSet.getInt("Quantity");
                preparedStatement.close();

                //get the quantity to sell
                String quantityStr = JOptionPane.showInputDialog("Enter the quantity to sell");
                if (quantityStr == null || quantityStr.isEmpty()) {
                    //if user cancels or enters an empty value
                    return;
                }

                int quantityToSell;
                try {
                    quantityToSell = Integer.parseInt(quantityStr);
                    if (quantityToSell <= 0 || quantityToSell > existingQuantity) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity to sell.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for quantity.");
                    return;
                }

                int newQuantity = existingQuantity - quantityToSell;

                //update the quantity in the DB
                String updateQuery = "UPDATE Products SET Quantity = ? WHERE ";
                if (isProductId) {
                    updateQuery += "Product_ID = ?";
                } else {
                    updateQuery += "Product_Name = ?";
                }

                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newQuantity);
                updateStatement.setString(2, productIdStr);
                updateStatement.executeUpdate();
                updateStatement.close();

                //display success message
                JOptionPane.showMessageDialog(null, "Product quantity updated successfully!");
                
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in the iunventory.");
            }
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to update product quantity. Please check your input.");
        }
    }
}

