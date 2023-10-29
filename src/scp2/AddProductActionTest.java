package scp2;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * IDs 19081476, 21135410
 * @author hallowseph(xxg8089), Nicolas-Kotze (RTH8619)
 */
class AddProductActionTest {

    private Connection mockConnection;

   

    @Test
    void testActionPerformed() throws SQLException {
        
    	// create a mock connection in order to perform the unit test 
    	this.mockConnection = MockDatabaseManager.getMockConnection();
    	JTextArea mockTextArea = new JTextArea();

        AddProductAction addProductAction = new AddProductAction(mockConnection, mockTextArea);
        
        // Create statement in order to update table in the mock connection 
        String insertQuery = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = mockConnection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "TestProduct");
        preparedStatement.setString(3, "TestType");
        preparedStatement.setDouble(4, 10.0);
        preparedStatement.setInt(5, 100);
        preparedStatement.executeUpdate();
        
        // send userinput to the AddProductAction class for unit testing 
        String[] userInput = {"1", "TestProduct", "TestType", "10.0", "100"};
        addProductAction.setUserInput(userInput); 
        
        
        addProductAction.actionPerformed();
        
        // check whether created product in our mock connection with name as above is in database 
        assertTrue(MockDatabaseManager.isProductInDatabase(mockConnection, "TestProduct"));
    }
    
    // setUp and tearDown method created in order to clear table when I had problems clearing the table. Not required after finding a fix. 
    // multiple attempt at unit testing with different libraries attempted such a Mockito, to no avail. Below is the solution that ended up working. 
    
//    @BeforeEach
//    void setUp() {
//        try {
//            mockConnection = MockDatabaseManager.getMockConnection();
//            MockDatabaseManager.clearTable(mockConnection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//        try {
//            MockDatabaseManager.dropTableProducts(mockConnection);
//            mockConnection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
