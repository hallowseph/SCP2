package scp2;
/**
 * IDs 19081476, 21135410
 * @author hallowseph(xxg8089), Nicolas-Kotze (RTH8619)
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextArea;

class DisplayStockActionTest {

	@Test
	void testActionPerformed() throws SQLException {
	    
		// Create a mock connection and textArea
	    Connection mockConnection = MockDatabaseManager.getMockConnection();
	    JTextArea mockTextArea = new JTextArea();

	    DisplayStockAction displayStockAction = new DisplayStockAction(mockTextArea);

	    // Insert a sample product
	    String insertQuery = "INSERT INTO Products (Product_ID, Product_Name, Product_Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement preparedStatement = mockConnection.prepareStatement(insertQuery);
	    preparedStatement.setInt(1, 1);
	    preparedStatement.setString(2, "TestProduct");
	    preparedStatement.setString(3, "TestType");
	    preparedStatement.setDouble(4, 10.0);
	    preparedStatement.setInt(5, 100);
	    preparedStatement.executeUpdate();

	    // Perform action
	    displayStockAction.triggerActionPerformed(); // Trigger the action

	    // Verify textArea contains expected data
	    String expectedText = "Product ID: 1\n" +
	                          "Product Name: TestProduct\n" +
	                          "Product Type: TestType\n" +
	                          "Price: $10.0\n" +
	                          "Quantity: 100\n\n";
	    
	    // Could not figure out how to stop the reading of the incorrect dataBase in time for submission, therefore it is not comparing to the appropriate mock values 
	    assertEquals(expectedText, mockTextArea.getText());
	}
}
