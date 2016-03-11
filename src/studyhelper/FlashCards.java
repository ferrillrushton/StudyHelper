/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Ferrill
 */
public class FlashCards implements ActionListener{
    
    private final static String PREVIOUS = "<", NEXT = ">";
    private String className;
    
    private ArrayList<String> front = new ArrayList();
    private ArrayList<String> back = new ArrayList();
    
    private FlashCardsGUI gui;
    private int size, pos, state;
    
    public FlashCards(ArrayList<String> frontSides, ArrayList<String> backSides,
            String theClassName){
        front = frontSides;
        back = backSides;
        className = theClassName;
        gui = new FlashCardsGUI(className);
        size = front.size();
        pos = 0;   // keeps track of which card the user is looking at (index)
        state = 0; // 0 means the user is looking at the front, and 1 means back
        
        gui.getPrevButton().addActionListener(this);
        gui.getNextButton().addActionListener(this);
        gui.getFlipButton().addActionListener(this);
        gui.getFlashCard().setText(front.get(pos));
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        if (source.getText().equals(PREVIOUS)){
            getPrevious();
        }else if (source.getText().equals(NEXT)){
            getNext();
        }else{
            flip();
        }
    }
    
    private void getPrevious(){
        // If at the first card, switch to last, otherwise decrement pos
        if (pos == 0)
            pos = size - 1;
        else
            pos--;
        
        // If state is 0, show front of card, otherwise show back
        if (state == 0)
            gui.getFlashCard().setText(front.get(pos));
        else
            gui.getFlashCard().setText(back.get(pos));
    }
    
    private void getNext(){
        // If at the last card, switch to first, otherwise increment pos
        if (pos == size - 1)
            pos = 0;
        else
            pos++;
        
        // If state is 0, show front of card, otherwise show back
        if (state == 0)
            gui.getFlashCard().setText(front.get(pos));
        else
            gui.getFlashCard().setText(back.get(pos));
    }
    
    private void flip(){
        // Switch the state and switch if the back or front at pos is showing
        if (state == 0){
            state = 1;
            gui.getFlashCard().setText(back.get(pos));
        }else{
            state = 0;
            gui.getFlashCard().setText(front.get(pos)); 
        }
    }
    
}//End of class FlashCards
