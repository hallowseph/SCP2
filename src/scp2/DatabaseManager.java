package scp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author hallowseph(xxg8089)
 * 
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:InventoryDB;create=true;";
    private static Connection connection;
    
    private DatabaseManager(){
        try{
            connection = DriverManager.getConnection(DB_URL);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //Singleton instance
    private static DatabaseManager instance;
    
    //Method for getting the reference to the Singleton Object
    public static DatabaseManager getInstance(){
        if(instance == null){
            synchronized(DatabaseManager.class){
                if(instance == null){
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection(){
        if(connection != null){
            try{
               connection.close(); 
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton class, cannot be cloned.");
    }
}
