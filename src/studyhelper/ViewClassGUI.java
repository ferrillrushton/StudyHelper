/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class ViewClassGUI {
    
    private JScrollPane scroll;
    private JButton addDate, viewFlashCards, addFlashCards, done;
    private JList flashCardList;
    private DefaultListModel flashCards;
    private JPanel container, buttons, flashCardOptions, space1, space2;
    private JFrame frame;
    
    public JPanel getContainer()
    {return container;}
    
    public JButton getAddDateButton()
    {return addDate;}
    
    public JButton getFlashCardButton()
    {return viewFlashCards;}
    
    public DefaultListModel getFlashCards()
    {return flashCards;}
    
    public JButton getAddCardsButton()
    {return addFlashCards;}
    
    public JButton getDoneButton()
    {return done;}
    
    public JList getFlashCardList()
    {return flashCardList;}
    
    public ViewClassGUI(Scanner theFlashCards,JFrame theFrame){
        frame = theFrame;
        flashCards = new DefaultListModel();
        updateFlashCards(theFlashCards);
        init();
    }
    
    private void init(){
        addDate = new JButton("Add Important Date");
        addDate.setPreferredSize(new Dimension(200, 70));
        
        
        flashCardList = new JList(flashCards);
        viewFlashCards = new JButton("View FlashCards");
        scroll = new JScrollPane(flashCardList);
        scroll.setPreferredSize(new Dimension(frame.getWidth()/2, frame.getHeight()/3));
        
        addFlashCards = new JButton("Add FlashCards");
        addFlashCards.setPreferredSize(new Dimension(200, 70));
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(200, 70));
        
        container = new JPanel();
        buttons = new JPanel(new GridLayout(2, 1));
        flashCardOptions = new JPanel();
        space1 = new JPanel();
        space1.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/6));
        space2 = new JPanel();
        space2.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/6));
        
        buttons.add(addDate);
        buttons.add(viewFlashCards);
        flashCardOptions.add(scroll);
        container.add(space1);
        container.add(buttons);
        container.add(flashCardOptions);
        container.add(space2);
        container.add(addFlashCards);
        container.add(done);
    }
    
    public void updateFlashCards(Scanner theFlashCards){
        flashCards.clear();
        theFlashCards.useDelimiter("###");
        while (theFlashCards.hasNext())
            flashCards.addElement(theFlashCards.next());
    }
    
}//End of class ViewClassGUI
