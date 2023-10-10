/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scp2;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author xxg8089
 */
public class GUI {
    public static void main(String[] args) {
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
        
        //Label for welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Inventory Management System");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Buttons for the options
        JButton displayButton = new JButton("1.Display current stock");
        JButton addButton = new JButton("2.Add product type");
        JButton removeButton = new JButton("3.Remove product type");
        JButton buyButton = new JButton("4.Buy product (Acquire more of am existing product");
        JButton sellButton = new JButton("5.Sell product (Sell a relevant quantity of an existing product");
        JButton exitButton = new JButton("6.Exit program");
        
        //Action listeners to the buttons
        /*
        
        
        
        
        
        
        */
        
        //Add components to the panel
        panel.add(welcomeLabel);
        panel.add(displayButton);
        panel.add(buyButton);
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
