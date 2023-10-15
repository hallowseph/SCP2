package scp2;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author xxg8089
 */
public class InventoryManagementSystemGUI {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        //Main frame
        JFrame frame = new JFrame();
        frame.setTitle("Inventory Management System");
        frame.setSize(700,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //Panel for welcome message and options
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        //Labels
        JLabel welcomeLabel = new JLabel("Welcome to the Inventory Management System");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //TextArea
        JTextArea textArea = new JTextArea(20,40);
        textArea.setEditable(false);
        
        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.SOUTH);
                
        //Buttons for the options
        JButton displayButton = new JButton("1.Display current stock");
        JButton addButton = new JButton("2.Add product type");
        JButton removeButton = new JButton("3.Remove product type");
        JButton buyButton = new JButton("4.Buy product (Acquire more of am existing product");
        JButton sellButton = new JButton("5.Sell product (Sell a relevant quantity of an existing product");
        JButton exitButton = new JButton("6.Exit program");
        
        
        //Action listeners to the buttons
        displayButton.addActionListener(new DisplayStockAction(textArea));
        addButton.addActionListener(new AddProductAction(inventoryManager,textArea));
        removeButton.addActionListener(new RemoveProductAction(inventoryManager, textArea));
        buyButton.addActionListener(new BuyProductAction(inventoryManager, textArea));
        sellButton.addActionListener(new SellProductAction(inventoryManager, textArea));
        exitButton.addActionListener(new ExitAction());
        
        //Add components to the panel
        panel.add(welcomeLabel);
        panel.add(displayButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(buyButton);
        panel.add(sellButton);
        panel.add(exitButton);
        
        //Add the panel to the frame
        frame.add(panel,BorderLayout.CENTER);
        
        //Make the frame visible
        frame.setVisible(true);

    }
    
}
