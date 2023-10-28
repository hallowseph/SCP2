package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 */
public class MainMenuAction implements ActionListener{
    private JTextArea textArea;
    
    public MainMenuAction(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Clear the text area to display the main menu
        textArea.setText("");
        
        String mainMenuText = "Welcome to the Inventory Management System\n\n"
                + "Please select an option from the buttons below.\n"
                + " - Display current stock (Display the products data in the database)\n"
                + " - Add product (Add a new product type - Grocery or Electronic)\n"
                + " - Remove product (Remove an existing product)\n"
                + " - Buy product (Acquire more of an existing product)\n"
                + " - Sell product (Sell a relevant quantity of an existing product)\n"
                + " - Exit program";
        
        textArea.append(mainMenuText);
    }
}
