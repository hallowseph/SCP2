package scp2;
/**
 * IDs 19081476, 21135410
 * @author hallowseph(xxg8089), Nicolas-Kotze (RTH8619)
 */

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.*;

class SellProductActionTest {

    @Test
    void testActionPerformed() throws SQLException {
        // Create a mock connection and textArea
        Connection mockConnection = MockDatabaseManager.getMockConnection();
        JTextArea mockTextArea = new JTextArea();

        SellProductAction sellProductAction = new SellProductAction(mockConnection, mockTextArea);

        // Insert a sample product
        String insertQuery = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = mockConnection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "TestProduct");
        preparedStatement.setString(3, "TestType");
        preparedStatement.setDouble(4, 10.0);
        preparedStatement.setInt(5, 100);
        preparedStatement.executeUpdate();

        // Set user input to sell 50 units of product with ID 1
        sellProductAction.setUserInput("50");

        // Perform action
        sellProductAction.actionPerformed(null);

        // Verify product quantity was updated
        preparedStatement = mockConnection.prepareStatement("SELECT Quantity FROM Products WHERE Product_ID = ?");
        preparedStatement.setInt(1, 1);
        java.sql.ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        assertEquals(50, resultSet.getInt("Quantity"));
    }
}
