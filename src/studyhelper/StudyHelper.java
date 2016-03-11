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
public class StudyHelper implements ActionListener{
    
    private int[] calendarOffset = {5,1,2,5,0,3,5,1,4,6,2,4};
    private int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private ArrayList<String> calInfo = new ArrayList(), classes = new ArrayList();
    
    private final static String CALENDAR = "Calendar";
    
    private String userFile, firstName, lastName;
    private ArrayList<String> classInfo = new ArrayList();
    private StudyHelperGUI gui;
    
    public StudyHelper(String file){
        userFile = file;
        getFileInfo();
        gui = new StudyHelperGUI(firstName);
        resetTextArea();
        gui.getCalendarButton().addActionListener(this);
        gui.getClassesButton().addActionListener(this);
    }
    
    private void getFileInfo(){
        FileInfoRetriever fileInfo = new FileInfoRetriever(userFile);
        Scanner info = new Scanner(fileInfo.getFileInfo());
        
        firstName = info.next();
        info.next();
        
        while (info.hasNextLine()){
            classInfo.add(info.nextLine());
        }
    }
    
    private void setClassDueDates(){
        JTextArea dueDates = gui.getUpcomingDueDates();
        String info = "";
        
        for (String line: classInfo)
            info += line + "\n";
        
        dueDates.setText(info);
    }
    
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton)e.getSource();
        
        if (source.getText().equals(CALENDAR)){
            openCalendar();
        }else{
            openClasses();
        }
    }
    
    private void openCalendar(){
        String file = userFile.replace(".txt", "_calendar.txt");
        calInfo.clear();
        
        FileInfoRetriever calendarInfo = new FileInfoRetriever(file);
        Scanner info = new Scanner (calendarInfo.getFileInfo());
        info.useDelimiter("###");
        while (info.hasNext())
            calInfo.add(info.next());
        
        CalendarControl calendar = new CalendarControl(calInfo);
    }
    
    private void openClasses(){
        String file = userFile.replace(".txt", "_classes.txt");
        
        FileInfoRetriever classesFile = new FileInfoRetriever(file);
        Scanner info = new Scanner (classesFile.getFileInfo());
        info.useDelimiter("###");
        while (info.hasNext())
            classes.add(info.next());
        
        Classes classesObj = new Classes(gui, file, this);
    }
    
    public void resetTextArea(){
        String infoToAdd = "";
        FileInfoRetriever dateFile = new FileInfoRetriever
                (userFile.replace(".txt", "_calendar.txt"));
        
        // Get the current month and convert it to a string with 2 characters
        Calendar now = Calendar.getInstance();
        int monthNum = now.get(Calendar.MONTH) + 1;
        String month = "";
        
        if (monthNum < 10)
            month += "0" + monthNum;
        else
            month = Integer.toString(monthNum);
        
        // Get the current day and convert it to a string with 2 characters
        int dayNum = now.get(Calendar.DATE);
        String day = "";
        
        if (dayNum < 10)
            day += "0" + monthNum;
        else
            day = Integer.toString(monthNum);
        
        // Get the info from the calendar file for the current month and the next
        // month and display them in the text area (as long as it's not December)
        Scanner dateInfo = new Scanner(dateFile.getFileInfo());
        dateInfo.useDelimiter("###");
        
        while (dateInfo.hasNext()){
            String monthInfo = dateInfo.next();
            String tempMonth = "";
            tempMonth += monthInfo.charAt(0);
            tempMonth += monthInfo.charAt(1);
            
            if (tempMonth.equals(month)){
                infoToAdd += "Month: " + tempMonth + "\n";
                Scanner dayInfo = new Scanner(monthInfo);
                dayInfo.useDelimiter("%%%");
                // Skips the month number stored
                dayInfo.next();
                while (dayInfo.hasNext()){
                    String dayInfoStr = dayInfo.next();
                    String dayStr = "" + dayInfoStr.charAt(0) + dayInfoStr.charAt(1);
                    if (dayStr.compareTo(day) >= 0)
                        infoToAdd += "\n" + dayInfoStr;
                }
                if (monthNum < 12){
                    int nextMonth = Integer.parseInt(tempMonth) + 1;
                    
                    if (nextMonth < 10)
                        infoToAdd += "\n\nMonth: " + "0" + nextMonth + "\n";
                    else
                        infoToAdd += "\n\nMonth: " + nextMonth + "\n";
                    
                    monthInfo = dateInfo.next();
                    dayInfo = new Scanner(monthInfo);
                    dayInfo.useDelimiter("%%%");
                    // Skips the month number stored
                    dayInfo.next();
                    while (dayInfo.hasNext()){
                        infoToAdd += "/n" + dayInfo.next();
                    }
                }
            }
        }
        
        gui.getUpcomingDueDates().setText(infoToAdd);
    }// End of resetTextArea() method
    
}//End of class StudyHelper
