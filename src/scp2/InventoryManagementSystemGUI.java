package scp2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author hallowseph(xxg8089)
 */
public class InventoryManagementSystemGUI {
    public static void main(String[] args) {
        //Main frame
        JFrame frame = new JFrame();
        frame.setTitle("Inventory Management System");
        frame.setSize(700,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panel for welcome message and options
        JPanel panel = new JPanel(new BorderLayout());
        
        //Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to the Inventory Management System");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Larger font for the welcome message
        Font welcomeFont = new Font("Arial", Font.BOLD, 24);
        welcomeLabel.setFont(welcomeFont);
        
        panel.add(welcomeLabel, BorderLayout.NORTH);
        
        //Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        
        //Create a "File" menu
        JMenu fileMenu = new JMenu("File");
        
        //Create menu items for "Main Menu" and Restart"
        JMenuItem mainMenuMenuItem = new JMenuItem("Main Menu");
        JMenuItem restartMenuItem = new JMenuItem("Restart");
        
        //Add menu items to the "File" menu
        fileMenu.add(mainMenuMenuItem);
        fileMenu.add(restartMenuItem);
        
        //Add the "File" menu to the menu bar
        menuBar.add(fileMenu);
        
        //Set the manu bar for the frame
        frame.setJMenuBar(menuBar);
        
        //Text area for stock display
        JTextArea textArea = new JTextArea(20,40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        //Font for the text in the JTextArea
        Font textAreaFont = new Font("Arial", Font.PLAIN, 14);
        textArea.setFont(textAreaFont);
        
        //Panel for buttons in a grid layout
        JPanel buttonPanel = new JPanel (new GridLayout(3,3,10,10));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        //Add action listeners to the menu items
        mainMenuMenuItem.addActionListener(new MainMenuAction(textArea));
        restartMenuItem.addActionListener(new RestartAction(textArea));
        
        //Buttons for the options
        JButton displayButton = new JButton("Display current stock");
        JButton addButton = new JButton("Add product");
        JButton removeButton = new JButton("Remove product");
        JButton buyButton = new JButton("Buy product");
        JButton sellButton = new JButton("Sell product");
        JButton exitButton = new JButton("Exit program");
        
        //font for the buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        displayButton.setFont(buttonFont);
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        buyButton.setFont(buttonFont);
        sellButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        
        //Action listeners to the buttons
        displayButton.addActionListener(new DisplayStockAction(textArea));
        addButton.addActionListener(new AddProductAction(DatabaseManager.getInstance().getConnection(),textArea));
        removeButton.addActionListener(new RemoveProductAction(DatabaseManager.getInstance().getConnection(), textArea));
        buyButton.addActionListener(new BuyProductAction(DatabaseManager.getInstance().getConnection(), textArea));
        sellButton.addActionListener(new SellProductAction(DatabaseManager.getInstance().getConnection(), textArea));
        exitButton.addActionListener(new ExitAction());
        
        //Add buttons to the button panel
        buttonPanel.add(displayButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(exitButton);
                
        //Add the main panel to the frame
        frame.add(panel);
        
        //Make the frame visible
        frame.setVisible(true);

    }
    
}
