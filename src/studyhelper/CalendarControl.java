/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author Ferrill
 */
public class CalendarControl implements ActionListener{
    
    private ArrayList<String> calInfo;
    private int[] calendarOffset = {5,1,2,5,0,3,5,1,4,6,2,4};
    private int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final static String[] MONTHS = {"January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October", "November",
        "December"};
    private final static String NEXT = ">";
    
    private Calendar now;
    private int month;
    
    private CalendarGUI gui;
    
    public CalendarControl(ArrayList<String> theCalInfo){
        calInfo = (ArrayList<String>) theCalInfo.clone();
        now = Calendar.getInstance();
        month = now.get(Calendar.MONTH) + 1;
        
        gui = new CalendarGUI(calendarOffset, days);
        updateCalendarInfo(month);
        gui.getNextButton().addActionListener(this);
        gui.getPrevButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        
        if (source.getText().equals(NEXT)){
            displayNextMonth();
        }else{
            displayPrevMonth();
        }
    }
    
    public String[] daysInfo(int monthNum){
        String[] daysInfoStr = new String[31];
        for (int i = 0; i < daysInfoStr.length; i++)
            daysInfoStr[i] = (i+1) + "";
        
        Scanner monthInfo = new Scanner(calInfo.get(monthNum - 1));
        monthInfo.useDelimiter("%%%");
        
        while (monthInfo.hasNext()){
            Scanner dayInfo = new Scanner(monthInfo.next());
            int day = Integer.parseInt(dayInfo.next());
            if (dayInfo.hasNextLine())
                daysInfoStr[day - 1] += "\n" + dayInfo.nextLine();
        }
        
        return daysInfoStr;
    }
    
    public void updateCalendarInfo(int monthNum){
        String[] daysInfo = daysInfo(month);
        gui.displayMonth(MONTHS[month - 1], month, daysInfo, days);
    }
    
    private void displayNextMonth(){
        if (month < 12){
            month++;
            updateCalendarInfo(month);
        }
    }
    
    private void displayPrevMonth(){
        if (month > 1){
            month--;
            updateCalendarInfo(month);
        }
    }
    
}//End of class CalendarControl
