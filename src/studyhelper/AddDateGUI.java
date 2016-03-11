/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Ferrill
 */
public class AddDateGUI {
    
    private JFrame frame;
    private JLabel monthLabel, dayLabel, descriptionLabel, error;
    private JTextArea month, day, description;
    private JButton addDate, done;
    private JPanel container, date, space1, space2, space3;
    
    public JPanel getContainer()
    {return container;}
    
    public JButton getAddDateButton()
    {return addDate;}
    
    public JButton getDoneButton()
    {return done;}
    
    public JTextArea getMonth()
    {return month;}
    
    public JTextArea getDay()
    {return day;}
    
    public JTextArea getDescription()
    {return description;}
    
    public JLabel getErrorLabel()
    {return error;}
    
    public AddDateGUI(JFrame theFrame){
        frame = theFrame;
        init();
    }
    
    private void init(){
        // JLabel and JTextArea for the month
        monthLabel = new JLabel("Month (two digits, ie 01)");
        month = new JTextArea();
        month.setPreferredSize(new Dimension(50, 18));
        
        // JLabel and JTextArea for the day
        dayLabel = new JLabel("Day (two digits, ie 01)");
        day = new JTextArea();
        day.setPreferredSize(new Dimension(50, 18));
        
        // JLabel and JTextArea for the description
        descriptionLabel = new JLabel("Brief Description (i.e. Test on Chapter 1,"
                + " Project 3 Due, etc.)");
        description = new JTextArea();
        description.setPreferredSize(new Dimension(400, 80));
        
        // addDate and done JButtons
        addDate = new JButton("Add Important Date");
        addDate.setPreferredSize(new Dimension(250, 90));
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(250, 90));
        
        // Message JLabel
        error = new JLabel();
        
        // All the JPanels being used (the space JPanels are for aesthetics)
        date = new JPanel(new GridLayout(2, 2, 30, 20));
        space1 = new JPanel();
        space2 = new JPanel();
        space3 = new JPanel();
        container = new JPanel();
        
        // Add month and day labels and text areas to appropriate JPanel
        date.add(monthLabel);
        date.add(month);
        date.add(dayLabel);
        date.add(day);
        
        // Set sizes for the spaces
        space1.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/10));
        space2.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/10));
        space3.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/10));
        
        // Add all componenets to container
        container.add(space1);
        container.add(date);
        container.add(space2);
        container.add(descriptionLabel);
        container.add(description);
        container.add(space3);
        container.add(addDate);
        container.add(done);
        container.add(error);
    }
    
}// End of class AddDateGUI
