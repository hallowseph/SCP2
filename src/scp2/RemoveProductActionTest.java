package scp2;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.*;
/**
 * IDs 19081476, 21135410
 * @author hallowseph(xxg8089), Nicolas-Kotze (RTH8619)
 */
class RemoveProductActionTest {

    @Test
    void testActionPerformed() throws SQLException {
        // Create a mock connection and textArea
        Connection mockConnection = MockDatabaseManager.getMockConnection();
        JTextArea mockTextArea = new JTextArea();
        
        // create an instance of RemoveProductAction with mock fields for unit testing 
        RemoveProductAction removeProductAction = new RemoveProductAction(mockConnection, mockTextArea);

        // Insert a sample product
        String insertQuery = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = mockConnection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "TestProduct");
        preparedStatement.setString(3, "TestType");
        preparedStatement.setDouble(4, 10.0);
        preparedStatement.setInt(5, 100);
        preparedStatement.executeUpdate();

        // Set user input to remove product with ID 1
        removeProductAction.setUserInput("1");

        // Perform action
        removeProductAction.actionPerformed();

        // Verify product was removed
        preparedStatement = mockConnection.prepareStatement("SELECT * FROM Products WHERE Product_ID = ?");
        preparedStatement.setInt(1, 1);
        assertFalse(preparedStatement.executeQuery().next());
    }
}
