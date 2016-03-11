/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ferrill
 */
public class ViewClass implements ActionListener{
    
    private final static String DONE = "Done", ADD_DATE = "Add Important Date",
            ADD_CARDS = "Add FlashCards";
    private String className, file;
    private StudyHelperGUI frame;
    private ViewClassGUI gui;
    private JPanel previousContainer;
    
    public ViewClass(String theClassName, String fileName, StudyHelperGUI theFrame,
            JPanel prevContainer){
        className = theClassName;
        file = fileName;
        frame = theFrame;
        Scanner flashCards = getFlashCards();
        
        gui = new ViewClassGUI(flashCards, frame);
        gui.getFlashCardButton().addActionListener(this);
        gui.getAddDateButton().addActionListener(this);
        gui.getDoneButton().addActionListener(this);
        gui.getAddCardsButton().addActionListener(this);
         
        previousContainer = prevContainer;
        frame.remove(previousContainer);
        frame.swap(gui.getContainer());
        frame.setTitle(className);
    }
    
    private Scanner getFlashCards(){
        String flashCardFile = file.replace(".txt", "_flashcards.txt");
        FileInfoRetriever flashCards = new FileInfoRetriever(flashCardFile);
        
        String info = flashCards.getFileInfo();
        return new Scanner(info);
    }
    
    public void updateFlashCards(){
        gui.updateFlashCards(getFlashCards());
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        // If user clicks "Done"
        if (source.getText().equals(DONE)){
            done();
        // If user clicks "Add Important Date"
        }else if (source.getText().equals(ADD_DATE)){
            addDate();
        }else if (source.getText().equals(ADD_CARDS)){
            addFlashCards();
        // If user clicks "View FlashCards"
        }else if(gui.getFlashCardList().getSelectedIndices().length != 0){
            openFlashCards();
        }
    }
    
    private void done(){
        frame.setTitle("Classes");
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private void addDate(){
        AddDate addDate = new AddDate(frame, gui.getContainer(), className, file);
    }
    
    private void addFlashCards(){
        String flashCardFile = file.replace(".txt", "_flashcards.txt");
        AddFlashCards add = new AddFlashCards(frame, gui.getContainer(), className,
                    this, flashCardFile);
    }
    
    // Fix so they can open more than one file (multiple flash card piles)
    private void openFlashCards(){
        List<String> indices = gui.getFlashCardList().getSelectedValuesList();
        ArrayList<String> front = new ArrayList();
        ArrayList<String> back = new ArrayList();
        
        for (String group: indices){
            System.out.println(group);
            String groupFile = file.replace(".txt", "_flashcards_" + group + ".txt");
            FileInfoRetriever flashCardsInfo = new FileInfoRetriever(groupFile);
            Scanner info = new Scanner (flashCardsInfo.getFileInfo());
            info.useDelimiter("###");
            while (info.hasNext()){
                Scanner cardInfo = new Scanner (info.next());
                cardInfo.useDelimiter("%%%");
                front.add(cardInfo.next());
                back.add(cardInfo.next());
            }
        }
        
        FlashCards cards = new FlashCards(front, back, className);
    }
    
    
}//End of class ViewClass
