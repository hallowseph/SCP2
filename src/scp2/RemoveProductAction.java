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
 */
public class RemoveProductAction implements ActionListener {
    private Connection connection;
    private JTextArea textArea;

    public RemoveProductAction(Connection connection, JTextArea textArea) {
        this.connection = connection;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //prompt the user for the product ID or name to remove
        String userInput = JOptionPane.showInputDialog("Enter the product ID or name to remove:");
        if(userInput == null || userInput.isEmpty()){
            //if user cancels or enters an empty value
            return;
        }
        
        try{
            //check if the product exists in the DB based on ID or name
            String query = "SELECT * FROM Products WHERE Product_ID = ? OR Product_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int productId;
            try{
                //attempt to parse the input as an integer (product ID)
                productId = Integer.parseInt(userInput);
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2, "");//empty string for product name
            }catch(NumberFormatException ex){
                //if parsing as an integer fails, treat it as a product name
                productId = 0;
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2, userInput);
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                //if product exists, remove it from the DB
                String productName = resultSet.getString("Product_Name");
                preparedStatement.close();
                
                //execute DELETE statement
                String deleteQuery = "DELETE FROM Products WHERE Product_ID = ? OR Product_Name = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, productId);
                deleteStatement.setString(2, productName);
                deleteStatement.executeUpdate();
                deleteStatement.close();
                
                //display success message
                JOptionPane.showMessageDialog(null, "Product '" + productName + "' removed successfully!");
                
            }else{
                //product does not exist
                preparedStatement.close();
                JOptionPane.showMessageDialog(null, "Product not found in the inventory.");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to remove the product. Please check your input.");
        }
    }
}


