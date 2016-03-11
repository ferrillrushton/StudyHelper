/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ClassesGUI {
    
    private JButton addClass, viewClass, done;
    private ArrayList<JRadioButton> classSet = new ArrayList();
    private JPanel buttons, classes, container;
    private ButtonGroup bg;
    private JFrame frame;
    
    public JPanel getContainer()
    {return container;}
    
    public JButton getAddClassButton()
    {return addClass;}
    
    public JButton getViewClassButton()
    {return viewClass;}
    
    public ArrayList<JRadioButton> getClassSet()
    {return classSet;}
    
    public JButton getDoneButton()
    {return done;}
    
    public ClassesGUI(ArrayList<JRadioButton> classSet, JFrame theFrame){
        this.classSet = classSet;
        frame = theFrame;
        init();
    }
    
    private void init(){
        classes = new JPanel();
        bg = new ButtonGroup();
        for (int i = 0; i < classSet.size(); i++){
            bg.add(classSet.get(i));
            classes.add(classSet.get(i));
        }
        classes.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/2));
        
        buttons = new JPanel();
        addClass = new JButton("Add Class");
        viewClass = new JButton("View Class");
        addClass.setPreferredSize(new Dimension(200, 90));
        viewClass.setPreferredSize(new Dimension(200, 90));
        
        done = new JButton("Done");
        done.setPreferredSize(new Dimension(200, 50));
        
        buttons.add(addClass);
        buttons.add(viewClass);
        
        container = new JPanel();
        container.add(classes);
        container.add(buttons);
        container.add(done);
    }
    
    public void updateRadioButtons(ArrayList<JRadioButton> newClassSet){
        classes.removeAll();
        ButtonGroup newBG = new ButtonGroup();
        for (int i = 0; i < classSet.size(); i++){
            newBG.add(classSet.get(i));
            classes.add(classSet.get(i));
        }
    }
    
}//End of class ClassesGUI
