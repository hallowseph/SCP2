package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

/**
 *
 * @author hallowseph(xxg8089)
 * 
 */
public class DisplayStockAction implements ActionListener {
        private JTextArea textArea;
        
        public DisplayStockAction(JTextArea textArea){
            this.textArea = textArea;
        }
        
    @Override
    public void actionPerformed(ActionEvent e) {
       //Clear the text area before displaying new data
       textArea.setText("");
       
       //Establish a database connection
       try{
           Connection connection = DatabaseManager.getInstance().getConnection();
           
           //SQL query to retrieve data
           String query = "SELECT Product_ID, Product_Name, Product_Type, Price, Quantity FROM Products";
           
           //Create a prepared statement
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           
           //Execute the query
           ResultSet resultSet = preparedStatement.executeQuery();
           
           //Display the results in the text area
           while(resultSet.next()){
               int productID = resultSet.getInt("Product_ID");
               String productName = resultSet.getString("Product_Name");
               String productType = resultSet.getString("Product_Type");
               double price = resultSet.getDouble("Price");
               int quantity  = resultSet.getInt("Quantity");
               
               String result = "Product ID: " + productID + "\n" +
                               "Product Name: " + productName + "\n" +
                               "Product Type: " + productType + "\n" +
                               "Price: $" + price + "\n" +
                               "Quantity: " + quantity + "\n\n";
               textArea.append(result);
           }
           
           //Close resources
           resultSet.close();
           preparedStatement.close();
       }catch(SQLException ex){
           ex.printStackTrace();
           textArea.setText("Error: Unable to retrieve data from the database");
       }
    }
}
