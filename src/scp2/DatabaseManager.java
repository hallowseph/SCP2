package scp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author joseph,xxg8089
 * 
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby://localhost:1527/InventoryDB;create=true;user=pdc;password=pdc";
    private static Connection connection;
    
    private DatabaseManager(){
        //to prevent instantiation from outside
    }
    
    public static Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(DB_URL);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
