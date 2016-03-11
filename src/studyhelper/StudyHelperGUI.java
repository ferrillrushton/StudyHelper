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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StudyHelperGUI extends JFrame{
    
    private JButton viewCalendar, viewClasses;
    private JTextArea upcomingDueDates;
    private JScrollPane scroll;
    private JPanel container;
    private JLabel welcome;
    private String name;
    
    private final static int WIDTH = 600, HEIGHT = 525;
    
    public JTextArea getUpcomingDueDates()
    {return upcomingDueDates;}
    
    public JButton getCalendarButton()
    {return viewCalendar;}
    
    public JButton getClassesButton()
    {return viewClasses;}
    
    public JPanel getContainer()
    {return container;}
    
    public StudyHelperGUI(String theName){
        super("Study Helper");
        name = theName;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        init();
        setVisible(true);
    }
    
    private void init(){
        container = new JPanel();
        
        welcome = new JLabel("Welcome " + name + "!");
        upcomingDueDates = new JTextArea(15, 45);
        viewCalendar = new JButton("Calendar");
        viewClasses = new JButton("Classes");
        scroll = new JScrollPane(upcomingDueDates);
        
        upcomingDueDates.setEditable(false);
        viewCalendar.setPreferredSize(new Dimension(400, 100));
        viewClasses.setPreferredSize(new Dimension(400, 100));
        
        container.add(welcome);
        container.add(scroll);
        container.add(viewCalendar);
        container.add(viewClasses);
        
        add(container);
    }
    
    public void swap(JPanel newContainer){
        
        remove(container);
        add(newContainer);
        revalidate();
        repaint();
    }
    
}//End of class StudyHelperGUI
