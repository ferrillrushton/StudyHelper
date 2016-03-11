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
public class CreateAccount implements ActionListener{

    private final static String GO_BACK = "Go back", FILE_NAME = "signIns.txt";
    private CreateAccountGUI gui;
    private SignOnGUI frame;
    private JPanel previousContainer;
    
    public CreateAccount(SignOnGUI theFrame){
        frame = theFrame;
        gui = new CreateAccountGUI(frame);
        
        gui.getCreateAccountButton().addActionListener(this);
        gui.getGoBackButton().addActionListener(this);
        
        previousContainer = frame.getContainer();
        frame.swap(gui.getContainer());
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        gui.getErrorMessage().setText("");
        
        if (source.getText().equals(GO_BACK)){
            goBack();
        }else if (gui.getFirstName().getText().equals("") || 
                gui.getLastName().getText().equals("") || 
                gui.getUserName().getText().equals("") ||
                gui.getPassword().getPassword().length == 0 || 
                gui.getPasswordAgain().getPassword().length == 0){
            gui.getErrorMessage().setText("You must fill out all fields.");
        }else if (!passwordsMatch()){
            gui.getErrorMessage().setText("Passwords do not match");
        }else{
            createAccount();
        }
    }
    
    private boolean passwordsMatch(){
        char[] password1 = gui.getPassword().getPassword();
        char[] password2 = gui.getPasswordAgain().getPassword();
        
        if (password1.length != password2.length)
            return false;
        
        else{
            for (int i = 0; i < password1.length; i++){
                if (password1[i] != password2[i])
                    return false;
            }
        }
        return true;
    }
    
    private void goBack(){
        frame.remove(gui.getContainer());
        frame.swap(previousContainer);
    }
    
    private void createAccount(){
        FileInfoRetriever accounts = new FileInfoRetriever(FILE_NAME);
        String account = "";
        
        // Add the userName and password to the account with a space in between
        account += gui.getUserName().getText() + " ";
        char[] passwordChars = gui.getPassword().getPassword();
        for (int i = 0; i < passwordChars.length; i++)
            account += passwordChars[i];
        account += " ";
        // Add account to the accounts file
        accounts.addToFile(account);
        gui.getErrorMessage().setText("Account Created!");
        
        // Creating the main file for the user
        String userFile = gui.getUserName().getText() + ".txt";
        FileInfoRetriever userAccount = new FileInfoRetriever(userFile);
        userAccount.addToFile(gui.getFirstName().getText() + " " + gui.getLastName().getText());
        
        // Creating the file for the calendar for later use. Adding in the
        // layout for how the months' info will be stored.
        String calendarFile = gui.getUserName().getText() + "_calendar.txt";
        FileInfoRetriever calendar = new FileInfoRetriever(calendarFile);
        calendar.addToFile("01###02###03###04###05###06###07###08###09###10###11###12");
        
        // Creating the file for the user that holds all class names
        String classesFile = gui.getUserName().getText() + "_classes.txt";
        FileInfoRetriever classes = new FileInfoRetriever(classesFile);
        classes.addToFile("");
    }
    
}//End of class CreateAccount
