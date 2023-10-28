package scp2;

/**
 *
 * @author josep
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSampleProducts {

    public static void main(String[] args) {
        String dbURL = "jdbc:derby:InventoryDB;create=true";

        try ( Connection connection = DriverManager.getConnection(dbURL)) {
            //Sample products to be inserted
            String[][] sampleProducts = {
                {"1", "Mic", "Electronics", "49.99", "100"},
                {"2", "Orange", "Grocery", "2.99", "500"},
                {"3", "Keyboard", "Electronics", "99.99", "50"},};

            String insertQuery = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            for (String[] product : sampleProducts) {
                preparedStatement.setInt(1, Integer.parseInt(product[0]));
                preparedStatement.setString(2, product[1]);
                preparedStatement.setString(3, product[2]);
                preparedStatement.setDouble(4, Double.parseDouble(product[3]));
                preparedStatement.setInt(5, Integer.parseInt(product[4]));
                preparedStatement.executeUpdate();
            }
            System.out.println("Sample products inserted successfuly.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
