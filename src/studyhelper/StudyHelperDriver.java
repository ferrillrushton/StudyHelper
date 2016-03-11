/*
CS 112   Tuesday 5:30 - 9:20

Final Project
Ferrill Rushton
December 14th, 2015

Program Title: StudyHelper
Program Description: This program lets the user keep track of important dates
                    for classes, as well as create flash cards for those classes.
The purpose of this program is to: Practice using all of the skills I have learned
                    in this Java class, and apply them to create something useful
                    for me.

Algorithm:
1. Create a JFrame for user to sign-on or create new account
2. If user selects create new account
    1. Change the contents of the frame to fields to create a new account
    2. User enters their first name, last name, username, and password(twice)
    3. If user leaves a field blank or passwords do not match, an error message
        is displayed
    4. If user enters everything correctly, save userName and password to a file
        that holds all usernames and password. Also create empty files for
        that user's calendar and classes list
3. If user selects sign on, check to see if their userName and password
    combination matches a combination stored in the file for the accounts.
4. If it doesn't match, display error message
5. If it does match, begin StudyHelper and dispose of signOn
    1. StudyHelper uses JFrame, program will end when this frame is exited
    2. Have a JTextArea that displays upcoming important dates
    3. Have a button to view the calendar and one to view the classes
    4. If user clicks Calendar, begin Calendar for current month
        1. Calendar uses JFrame
        2. There are seven JLabels at the top for the days of the week
        3. There are 42 JTextAreas for each day
        4. There are 2 JButtons for next and previous (month)
        5. If user clicks next, get the info for the next month (including
            what day of the week the month starts on and how many days there are)
            and display the new info
        6. IF user clicks previous, repeat step 5 but for the previous month
        7. When user exits Calendar, it is disposed
    5. If user clicks on Classes, begin Classes
        1. Classes lies within a JPanel
        2. Classes has JRadioButtons at the top, one for each class the user
            has added, and they are in a ButtonGroup
        3. Classes has 3 JButtons, addClass, viewClass, and done
        4. Swap the main JPanel in StudyHelper with Classes' JPanel
        5. If user clicks addClass, begin AddClass
            1. AddClass lies within a JPanel
            2. AddClass has a JTextArea for the new class name
            3. AddClass has 2 JButtons, addClass and done
            4. Swap AddClass JPanel with Classes JPanel
            5. If user clicks addClass and there is text in the JTextArea,
                then add the text to the classes file for that user, and
                create an empty file for the flash cards for that class
            6. If user clicks done, return to Classes
        6. If user clicks viewClass and a JRadioButton is selected, begin ViewClass
            1. ViewClass lies within a JPanel
            2. Have a JList for the list of flash cards for that user and class
            3. Have 4 JButtons, addDate, viewCards, addCards, and done
            4. Swap ViewClass JPanel with Classes JPanel
            5. If user clicks addDate, begin addDate
                1. AddDate is within a JPanel
                2. Has 3 JTextAreas, month, date, description
                3. Has 2 JButtons, addDate and done
                4. If user clicks addDate
                    1. if any field is blank, display error message
                    2. if either month or day is not 2 numbers (ie 01), display
                        error message
                    3. if all is correct, add the description to the appropriate
                        date in the user's calendar file
                5. If user clicks done, return to ViewClass
            6. If user clicks viewCards and at least one flash card group is selected
                from the JList, begin FlashCards with the flashcards selected
                1. FlashCards is its own JFrame
                2. There is a label at the top with the class name
                3. There is a large JTextArea that shows the info in the flashcard
                4. 3 JButtons, previous, next, and flip
                5. If user clicks next
                    1. Show next flashCard in the set, if the current flash
                        card is the last one, then show the first
                6. If user clicks previous
                    1. Show previous flashcard in set, if the current flash
                        card is the first one, then show the last
                7. If user clicks flip, then show the other side of the card in
                    the JTextArea
            7. If user clicks addCards, begin AddFlashCards
                1. AddFlashCards is in a JPanel
                2. 3 JTextAreas, groupName, front, and back
                3. 2 JButtons, addCard and done
                4. If user clicks addCard
                    1. If any field is blank, display error message
                    2. If card group exists, append to that group's file
                    3. If card group does not exist, create the file and
                        add the card to it
                5. If user clicks done, return to ViewClass
            8. If user clicks done, return to Classes
        7. If user clicks done, return to StudyHelper
 */

package studyhelper;

public class StudyHelperDriver {

    public static void main(String[] args) {
        
        SignOnGUI signOn = new SignOnGUI();
        SignOn so = new SignOn(signOn);
        
    }
    
}
