package scp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

/**
 *
 * @author josep
 */
public class RestartAction implements ActionListener {
    private JTextArea textArea;
    
    public RestartAction(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Clear the text area to reset the state
        textArea.setText("");
    }
}
