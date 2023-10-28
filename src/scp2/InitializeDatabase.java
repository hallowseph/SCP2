package scp2;

/**
 *
 * @author josep
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitializeDatabase {
    public static void main(String[] args) {
        String dbURL = "jdbc:derby:InventoryDB;create=true";
        
        try(Connection connection = DriverManager.getConnection(dbURL)){
            System.out.println("Database initialized successfully.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
