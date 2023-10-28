package scp2;

/**
 *
 * @author josep
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        String dbURL = "jdbc:derby:InventoryDB";
        
        try(Connection connection = DriverManager.getConnection(dbURL);
            Statement statement = connection.createStatement()){
            
            //Define the SQL statement to create the "Products" table
            String createTableSQL = "CREATE TABLE Products ("
                    + "Product_ID INT PRIMARY KEY, "
                    + "Product_Name VARCHAR(255), "
                    + "Product_Type VARCHAR(255), "
                    + "Price DECIMAL(10, 2), "
                    + "Quantity INT"
                    + ")";
            statement.executeUpdate(createTableSQL);
            
            System.out.println("Table created successfully.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
