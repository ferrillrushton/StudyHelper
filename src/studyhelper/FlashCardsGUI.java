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
import javax.swing.JTextPane;

/**
 *
 * @author Ferrill
 */
public class FlashCardsGUI extends JFrame{
    
    private JLabel classNameLabel;
    private JTextArea flashCard;
    private JButton previous, next, flip;
    private JPanel container;
    
    private String className;
    private final static int WIDTH = 700, HEIGHT = 475;
    
    public JLabel getClassName()
    {return classNameLabel;}
    
    public JTextArea getFlashCard()
    {return flashCard;}
    
    public JButton getPrevButton()
    {return previous;}
    
    public JButton getNextButton()
    {return next;}
    
    public JButton getFlipButton()
    {return flip;}
    
    public JPanel getContainer()
    {return container;}
    
    public FlashCardsGUI(String theClassName){
        super("Study Helper Flash Cards");
        className = theClassName;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        init();
        setVisible(true);
    }
    
    private void init(){
        container = new JPanel();
        classNameLabel = new JLabel(className);
        flashCard = new JTextArea();
        flip = new JButton("Flip Card");
        previous = new JButton("<");
        next = new JButton(">");
        
        flashCard.setEditable(false);
        flashCard.setMaximumSize(new Dimension(500, 300));
        flip.setPreferredSize(new Dimension(100, 50));
        
        container.add(classNameLabel);
        add(container, BorderLayout.NORTH);
        add(flashCard, BorderLayout.CENTER);
        add(flip, BorderLayout.SOUTH);
        add(previous, BorderLayout.WEST);
        add(next, BorderLayout.EAST);
    }
    
}//End of class FlashCardsGUI
