/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scp2;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author josep
 */
public class GUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JButton button = new JButton("Click here");
        JFrame frame = new JFrame();
        
        frame.setTitle("JFrame1");
        frame.setSize(400,200);
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setVisible(true);
        
    }
    
}
