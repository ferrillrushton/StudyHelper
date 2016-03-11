/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

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
public class AddFlashCardsGUI {
    
    private JFrame frame;
    private JLabel frontLabel, backLabel, groupNameLabel, message;
    private JTextArea front, back, groupName;
    private JButton addCard, done;
    private JPanel container, top;
    
    public JLabel getFrontLabel()
    {return frontLabel;}
    
    public JLabel getBackLabel()
    {return backLabel;}
    
    public JLabel getGroupNameLabel()
    {return groupNameLabel;}
    
    public JLabel getMessageLabel()
    {return message;}
    
    public JTextArea getFront()
    {return front;}
    
    public JTextArea getBack()
    {return back;}
    
    public JTextArea getGroupName()
    {return groupName;}
    
    public JButton getAddCardButton()
    {return addCard;}
    
    public JButton getDoneButton()
    {return done;}
    
    public JPanel getContainer()
    {return container;}
    
    public AddFlashCardsGUI(JFrame theFrame){
        frame = theFrame;
        init();
    }
    
    private void init(){
        groupNameLabel = new JLabel("Card Group Name");
        groupName = new JTextArea();
        groupName.setPreferredSize(new Dimension(200, 20));

        frontLabel = new JLabel("Front of Card");
        front = new JTextArea();
        front.setPreferredSize(new Dimension(frame.getWidth() - 80, frame.getHeight()/3));
        
        backLabel = new JLabel("Back of Card");
        back = new JTextArea();
        back.setPreferredSize(new Dimension(frame.getWidth() - 80, frame.getHeight()/3));
        
        addCard = new JButton("Add Card");
        addCard.setPreferredSize(new Dimension(270, 50));
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(270, 50));
        
        message = new JLabel();
        
        container = new JPanel();
        top = new JPanel();
        top.setPreferredSize(new Dimension(frame.getWidth(), 20));
        
        top.add(groupNameLabel);
        top.add(groupName);
        container.add(top);
        container.add(frontLabel);
        container.add(front);
        container.add(backLabel);
        container.add(back);
        container.add(addCard);
        container.add(done);
        container.add(message);
    }
}//End of class AddFlashCardsGUI
