/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Ferrill
 */
public class AddClassGUI {
    
    private JLabel classNameLabel, messageLabel;
    private JTextArea classNameEnter;
    private JButton addClass, done;
    private JPanel container, newClass, topHalf;
    private JFrame frame;
    
    public JPanel getContainer()
    {return container;}
    
    public JTextArea getClassEntered()
    {return classNameEnter;}
    
    public JButton getAddClassButton()
    {return addClass;}
    
    public JButton getDoneButton()
    {return done;}
    
    public JLabel getMessage()
    {return messageLabel;}
    
    public AddClassGUI(JFrame theFrame){
        frame = theFrame;
        init();
    }
    
    private void init(){
        // Label and TextArea for the new class name
        classNameLabel = new JLabel("Enter Class Name");
        classNameEnter = new JTextArea();
        classNameEnter.setPreferredSize(new Dimension(200, 20));
        
        // addClass and done buttons
        addClass = new JButton("Add Class");
        addClass.setPreferredSize(new Dimension(500, 120));
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(200, 40));
        
        // The label for messages
        messageLabel = new JLabel();
        
        // Instantiate the JPanels
        newClass = new JPanel();
        newClass.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/4));
        topHalf = new JPanel(new BorderLayout());
        topHalf.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/2));
        container = new JPanel();
        
        // Add the className Label and TextArea to their appropriate JPanel
        newClass.add(classNameLabel);
        newClass.add(classNameEnter);
        topHalf.add(newClass, BorderLayout.SOUTH);
        
        // Add all the components to the main JPanel
        container.add(topHalf);
        container.add(messageLabel);
        container.add(addClass);
        container.add(done);
    }
    
}// End of class AddClassGUI
