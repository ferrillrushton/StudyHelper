/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ferrill
 */
public class AddClass implements ActionListener{
    
    private final static String DONE = "Done";
    private String file;
    private StudyHelperGUI frame;
    private Classes previousDisplay;
    private AddClassGUI gui;
    private JPanel previousContainer;
    
    public AddClass(StudyHelperGUI theFrame, JPanel prevContainer, String theFile,
            Classes prevDisplay){
        frame = theFrame;
        file = theFile;
        previousDisplay = prevDisplay;
        
        gui = new AddClassGUI(frame);
        gui.getAddClassButton().addActionListener(this);
        gui.getDoneButton().addActionListener(this);
        
        previousContainer = prevContainer;
        frame.remove(previousContainer);
        frame.swap(gui.getContainer());
        frame.setTitle("Add Class");
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        // If user clicks done
        if (source.getText().equals(DONE)){
            done();
        }else if (gui.getClassEntered().getText().equals("")){
            // Do nothing, user has not entered a class name
        // If user clicks Add Class and has entered text in the class name JTextArea
        }else{
            addClass();
        }
    }
    
    // Update the radio buttons so they include the class just added, and
    // swap the JPanels so that it returns to the previous display
    private void done(){
        previousDisplay.updateRadioButtons();
        frame.setTitle("Classes");
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private void addClass(){
        String className = gui.getClassEntered().getText();
        
        FileInfoRetriever classes = new FileInfoRetriever(file);
        // Add the className with the custome delimiter
        classes.addToFile(className + "###");
        
        // Creating the empty file that will hold the new class' flash cards
        String flashCardFile = file.replace(".txt", ("_" + className + "_flashcards.txt"));
        FileInfoRetriever flashCards = new FileInfoRetriever(flashCardFile);
        flashCards.addToFile("");
        
        gui.getMessage().setText("Class added successfully!");
    }
    
}// End of class AddClass
