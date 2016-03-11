/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CalendarGUI extends JFrame{
    
    private final static int WIDTH = 1000, HEIGHT = 600;
    
    private JButton[] buttons;
    private JTextArea[] dayInfo;
    private JScrollPane[] scrollPanes;
    private JLabel[] daysOfWeek;
    
    private JButton next, previous;
    private JPanel calendar;
    private JPanel daysOfWeekDisplay;
    
    private int[] numOfDays;   
    private int[] offset;
    
    public void setOffsets(int[] offsets)
    {this.offset = offsets;}
    
    public void setDayInfo(JTextArea[] newInfo)
    {dayInfo = newInfo;}
    
    public void setDayInfoAt(JTextArea newInfo, int i)
    {dayInfo[i] = newInfo;}
    
    public JButton getPrevButton()
    {return previous;}
    
    public JButton getNextButton()
    {return next;}
    
    public JTextArea[] getDayInfo()
    {return dayInfo;}
    
    public CalendarGUI(int[] monthOffsets, int[] numberOfDays){
        super("January");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        numOfDays = numberOfDays;
        offset = monthOffsets;
        init();
        setVisible(true);
       }
    
    private void init(){
        buttons = new JButton[42];
        dayInfo = new JTextArea[42];
        scrollPanes = new JScrollPane[42];
        daysOfWeek = new JLabel[7];
        
        daysOfWeek[0] = new JLabel("                 Sunday");
        daysOfWeek[1] = new JLabel("              Monday");
        daysOfWeek[2] = new JLabel("            Tuesday");
        daysOfWeek[3] = new JLabel("        Wednesday");
        daysOfWeek[4] = new JLabel("        Thursday");
        daysOfWeek[5] = new JLabel("        Friday");
        daysOfWeek[6] = new JLabel("     Saturday");
        
        calendar = new JPanel(new GridLayout(6,7,5,5));
        daysOfWeekDisplay = new JPanel(new GridLayout(1,7,5,5));
        next = new JButton(">");
        previous = new JButton("<");
        
        next.setPreferredSize(new Dimension(30, HEIGHT));
        previous.setPreferredSize(new Dimension(30, HEIGHT));
        
        for (int i = 0; i < dayInfo.length; i++){
            dayInfo[i] = new JTextArea();
            dayInfo[i].setEditable(false);
            dayInfo[i].setLineWrap(true);
            dayInfo[i].setWrapStyleWord(true);
            scrollPanes[i] = new JScrollPane(dayInfo[i]);
            calendar.add(dayInfo[i]);
        }
        
        for (JLabel day: daysOfWeek)
            daysOfWeekDisplay.add(day);
        
        add(daysOfWeekDisplay,BorderLayout.NORTH);
        add(calendar, BorderLayout.CENTER);
        add(next, BorderLayout.EAST);
        add(previous, BorderLayout.WEST);
    }
    
    public void displayMonth(String monthStr, int monthNum, String[] daysInfo,
            int[] numOfDays){
        this.setTitle(monthStr);
        for (int i = 0; i < dayInfo.length; i++){
            dayInfo[i].setText("");
        }
        for (int i = 0; i < numOfDays[monthNum-1]; i++){
            dayInfo[i + offset[monthNum-1]].setMaximumSize(new Dimension(130, 70));
            dayInfo[i + offset[monthNum-1]].setText(daysInfo[i]);
        }
    }
    
}//End of class CalendarGUI
