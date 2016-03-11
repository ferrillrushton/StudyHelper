/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ferrill
 */
public class AddFlashCards implements ActionListener{
    
    private final static String DONE = "Done";
    private String file;
    
    private AddFlashCardsGUI gui;
    private StudyHelperGUI frame;
    private ViewClass viewClass;
    private JPanel previousContainer;
    
    public AddFlashCards(StudyHelperGUI theFrame, JPanel prevContainer,
            String className, ViewClass viewClass, String fileName){
        frame = theFrame;
        file = fileName;
        previousContainer = prevContainer;
        this.viewClass= viewClass;
        
        gui = new AddFlashCardsGUI(frame);
        gui.getAddCardButton().addActionListener(this);
        gui.getDoneButton().addActionListener(this);
        
        previousContainer = prevContainer;
        frame.remove(previousContainer);
        frame.swap(gui.getContainer());
        frame.setTitle(className);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        gui.getMessageLabel().setText("");
        
        //If user clicks "Done"
        if (source.getText().equals(DONE))
            done();
        //If user clicks "Add Flash Card"
        else{
            if (userFilledOut()){
                addCardToSet();
                addGroupIfNew();
                gui.getFront().setText("");
                gui.getBack().setText("");
            }
        }
    }
    
    private void done(){
        //Call updateFlashCards() so that any new card groups added will show
        viewClass.updateFlashCards();
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private boolean userFilledOut(){
        // If any field is empty, then the user did not fill it out
        if (gui.getGroupName().getText().equals("") || gui.getFront().getText().equals("") ||
                gui.getBack().getText().equals("")){
            gui.getMessageLabel().setText("ERROR: All fields must be filled out");
            return false;
        }
        return true;
    }
    
    private void addCardToSet(){
        String groupName = gui.getGroupName().getText();
        String groupFile = file.replace(".txt", ("_" + groupName + ".txt"));
        
        FileInfoRetriever groupFileInfo = new FileInfoRetriever(groupFile);
        if (groupFileInfo.getFileInfo().equals("")){
            String flashCard = gui.getFront().getText() + "%%%" + gui.getBack().getText();
            groupFileInfo.addToFile(flashCard);
        }else{
            String flashCard = "###" + gui.getFront().getText() + "%%%" 
                    + gui.getBack().getText();
            groupFileInfo.addToFile(flashCard);
        }
    }
    
    private void addGroupIfNew(){
        String groupName = gui.getGroupName().getText();
        FileInfoRetriever flashcardFile = new FileInfoRetriever(file);
        Scanner cardGroups = new Scanner(flashcardFile.getFileInfo());
        cardGroups.useDelimiter("###");
        boolean newGroup = true;
        
        while (cardGroups.hasNext()){
            if (cardGroups.next().equals(groupName)){
                newGroup = false;
                break;
            }
        }
        
        if (newGroup){
            flashcardFile.addToFile("###" + groupName);
        }
    }
    
}//End of class AddFlashCards
