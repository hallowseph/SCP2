package scp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * IDs 19081476, 21135410
 * @author hallowseph(xxg8089), Nicolas-Kotze (RTH8619)
 */
public class MockDatabaseManager {
   
	// create a mock connection in order to allow Unit testing to be viable without something like Mockito library
    public static Connection getMockConnection() throws SQLException {
        String dbURL = "jdbc:derby:MockInventoryDB;create=true";
        Connection connection = DriverManager.getConnection(dbURL);
        Statement statement = connection.createStatement();
        
        // Drop the table if it exists. After many attempts to clear the database "PRODUCTS" as seen in addProductActionTest class, the two lines of code below proved to be the solution
        String dropTableSQL = "DROP TABLE Products";
        statement.executeUpdate(dropTableSQL);
        
        // Create a mock table 
        String createTableSQL = "CREATE TABLE Products ("
                + "Product_ID INT PRIMARY KEY, "
                + "Product_Name VARCHAR(255), "
                + "Product_Type VARCHAR(255), "
                + "Price DECIMAL(10, 2), "
                + "Quantity INT"
                + ")";
        statement.executeUpdate(createTableSQL);
        
        return connection;
    }
    

    
    public static boolean isProductInDatabase(Connection connection, String productName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products WHERE Product_Name = ?");
        preparedStatement.setString(1, productName);
        return preparedStatement.executeQuery().next();
    }
    
    // the methods below were attempts at clearing the PRODUCTS database which proved very difficult  
    
//  public static void dropTableProducts(Connection connection) throws SQLException {
//      Statement statement = connection.createStatement();
//      statement.executeUpdate("DROP TABLE IF EXISTS Products");
//  }
//  
//  public static void clearTable(Connection connection) throws SQLException {
//      Statement stmt = connection.createStatement();
//      String deleteSQL = "DELETE FROM Products";
//      stmt.executeUpdate(deleteSQL);
//  }

}
