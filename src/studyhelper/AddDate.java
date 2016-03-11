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
public class AddDate implements ActionListener{
    
    private final static String DONE = "Done";
    private String file, className;
    private AddDateGUI gui;
    private StudyHelperGUI frame;
    private JPanel previousContainer;
    
    public AddDate(StudyHelperGUI theFrame, JPanel prevContainer, String theClassName,
            String fileName){
        frame = theFrame;
        file = fileName;
        className = theClassName;
        
        gui = new AddDateGUI(frame);
        gui.getAddDateButton().addActionListener(this);
        gui.getDoneButton().addActionListener(this);
        
        previousContainer = prevContainer;
        frame.remove(previousContainer);
        frame.swap(gui.getContainer());
        frame.setTitle(className);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        gui.getErrorLabel().setText("");
        
        // If user clicks "Done"
        if (source.getText().equals(DONE)){
            done();
        // If user clicks "Add Important Date"
        }else{
            if (userInputValid()){
                addDate();
                gui.getErrorLabel().setText("Date Added Successfully!");
            }
        }
    }
    
    private void done(){
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private boolean userInputValid(){
        String monthStr = gui.getMonth().getText();
        String dayStr = gui.getDay().getText();
        
        // If either the month or the day does not have 2 characters
        if (monthStr.length() != 2 || dayStr.length() != 2){
            gui.getErrorLabel().setText("ERROR: Date was inputted incorrectly.");
            return false;
        // If any of the characters are not numbers
        }else if(monthStr.charAt(0) < 48 || monthStr.charAt(0) > 57 ||
                monthStr.charAt(1) < 48 || monthStr.charAt(1) > 57 ||
                dayStr.charAt(0) < 48 || dayStr.charAt(0) > 57 ||
                dayStr.charAt(1) < 48 || dayStr.charAt(1) > 57){
            gui.getErrorLabel().setText("ERROR: Date was inputted incorrectly.");
            return false;
        // If month or day is out of range
        }else if (Integer.parseInt(monthStr) > 12 || Integer.parseInt(dayStr) > 31){
            gui.getErrorLabel().setText("ERROR: Date was inputted incorrectly.");
            return false;
        // If no description is entered
        }else if(gui.getDescription().getText().equals("")){
            gui.getErrorLabel().setText("ERROR: A description must be entered.");
            return false;
        }
        return true;
    }
    
    private void addDate(){
        String calendarFile = file.replace(("_classes_" + className + ".txt"), 
                "_calendar.txt");
        
        FileInfoRetriever calFile = new FileInfoRetriever(calendarFile);
        
        Scanner calInfo = new Scanner(calFile.getFileInfo());
        calInfo.useDelimiter("###");
        
        String newInfo = "";
        // Store new info as well as all the old info in newYearInfo
        String newYearInfo = "";
        String month = gui.getMonth().getText();
        String day = gui.getDay().getText();
        
        while (calInfo.hasNext()){
            String tempMonthInfo = "";
            Scanner monthInfo = new Scanner(calInfo.next());
            monthInfo.useDelimiter("%%%");
            tempMonthInfo += monthInfo.next(); // This is the month number
            // If the month number is equal to the month entered by the user
            if (tempMonthInfo.equals(month)){
                tempMonthInfo += insertDayInfoAt(monthInfo, day);
            }
            // Add the rest of the info so that none of it is lost
            while (monthInfo.hasNext()){
                tempMonthInfo += "%%%" + monthInfo.next();
            }
            // If it's the first month, no delimiter in front is needed
            if (newYearInfo.equals(""))
                newYearInfo += tempMonthInfo;
            // For all other months, place the delimiter back in front
            else
                newYearInfo += "###" + tempMonthInfo;
        }
        // newYearInfo holds the new and old info, so overwrite the file
        calFile.writeFile(newYearInfo);
    }
    
    private String insertDayInfoAt(Scanner monthInfo, String day){
        // If there is no if for any day that month yet
        String newMonthInfo = "";
        
        if (!monthInfo.hasNext()){
            String dayDescription = "%%%" + day + " : " + className + " - " + 
                    gui.getDescription().getText();
            return dayDescription;
        // Days are saved in increasing order, append to the day selected, or if
        // no info exists for that day, add that day and the info.
        }else{
            while (monthInfo.hasNext()){
                String tempDay = "";
                String tempInfo = monthInfo.next();
                tempDay += tempInfo.charAt(0);
                tempDay += tempInfo.charAt(1);
                
                // If the day being added to exists, append the description and
                // add to newMonthInfo, putting back in the delimiter
                if (day.compareTo(tempDay) == 0){
                    tempInfo += "%%%" + day + " : " + className + " - " + 
                            gui.getDescription().getText();
                    newMonthInfo += "%%%" + tempInfo;
                    break; // Info is added, so no need to loop anymore
                // If the day being added to does not exist, add it with the 
                // delimiter being used placed in front, and append to newMonthInfo
                }else if (day.compareTo(tempDay) < 0){
                    newMonthInfo += "%%%" + day + " : " + className + " - " +
                            gui.getDescription().getText() + "%%%" + tempInfo;
                    break; // Info is added, so no need to loop anymore
                // If you haven't reached where the day should be yet, just add
                // the info for the day being read to newMonthInfo, putting back
                // in the delimiter, and add the description with the delimiter
                }else{
                   newMonthInfo += "%%%" + tempInfo + "%%%" + day + " : " + 
                           className + " - " + gui.getDescription().getText();
                   break; // Info is added, so no need to loop anymore
                }
            }
            // Appending the rest of the info from the month (otherwise it will
            // be lost)
            while (monthInfo.hasNext()){
                newMonthInfo += "%%%" + monthInfo.next();
            }
            
            return newMonthInfo;
        }
    }//End of insertDayInfoAt(Scanner, String) method
    
}// End of class AddDate