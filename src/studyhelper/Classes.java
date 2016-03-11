/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Ferrill
 */
public class Classes implements ActionListener{
    
    private final static String ADD_CLASS = "Add Class", VIEW_CLASS = "View Class";
    private String file;
    
    private ArrayList<String> classes = new ArrayList();
    private StudyHelperGUI frame;
    private StudyHelper studyHelper;
    private ArrayList<JRadioButton> radioButtons = new ArrayList();
    private ClassesGUI gui;
    private JPanel previousContainer;
    
    public Classes(StudyHelperGUI theFrame, String fileName, StudyHelper studyHelper){
        //classes = theClasses;
        frame = theFrame;
        file = fileName;
        this.studyHelper = studyHelper;
        makeRadioButtons();
        gui = new ClassesGUI(radioButtons, frame);
        
        gui.getAddClassButton().addActionListener(this);
        gui.getViewClassButton().addActionListener(this);
        gui.getDoneButton().addActionListener(this);
        previousContainer = frame.getContainer();
        frame.swap(gui.getContainer());
        frame.setTitle("Classes");
    }
    
    private void makeRadioButtons(){
        getClasses();
        
        radioButtons.clear();
        for (int i = 0; i < classes.size(); i++){
            radioButtons.add(new JRadioButton(classes.get(i)));
        }
    }
    
    public void updateRadioButtons(){
        makeRadioButtons();
        gui.updateRadioButtons(radioButtons);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        if (source.getText().equals(ADD_CLASS)){
            openAddClass();
        }else if (source.getText().equals(VIEW_CLASS)){
            openViewClass();
        }else{
            done();
        }
    }
    
    private void openAddClass(){
        AddClass addClass = new AddClass(frame, gui.getContainer(), file, this);
    }
    
    private void openViewClass(){
        ArrayList<JRadioButton> buttons = gui.getClassSet();
        String className = "";
        
        for (int i = 0; i < buttons.size(); i++){
            if (buttons.get(i).isSelected()){
                className = buttons.get(i).getText();
                break;
            }
        }
        if (!className.equals("")){
            String fileName = file.replace(".txt", ("_" + className + ".txt"));
        
            ViewClass viewClass = new ViewClass(className, fileName, frame, gui.getContainer());
        }
    }
    
    private void done(){
        studyHelper.resetTextArea();
        frame.setTitle("Study Helper");
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private void getClasses(){
        classes.clear();
        FileInfoRetriever classesFile = new FileInfoRetriever(file);
        Scanner info = new Scanner (classesFile.getFileInfo());
        info.useDelimiter("###");
        while (info.hasNext())
            classes.add(info.next());
    }
    
}//End of class Classes
