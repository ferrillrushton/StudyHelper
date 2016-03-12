/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 *
 * @author Ferrill
 */
public class SignOn implements ActionListener{

    private SignOnGUI gui;
    private final static String CREATE_ACCOUNT = "Create new account", FILE_NAME = "signIns.txt";
    private JTextArea userNameText;
    private JPasswordField passwordText;
    private JLabel errorMessage;
    
    private class TabListener implements KeyListener{
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_TAB){
                userNameText.transferFocus();
                // The tab is still added to the JTextArea, so it must be removed
                String text = removeTab(userNameText.getText());
                userNameText.setText(text);
            }
        }
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        
        // Takes the text from the JTextArea, splits the String using the tab key
        // as the delimiter, and then putting it back together. This gets rid of
        // the tabs.
        private String removeTab(String text){
            String[] splitText = text.split("\\t");
            String newText = "";
            for (int i = 0; i < splitText.length; i++){
                newText += splitText[i];
            }
            return newText;
        }
    }
    
    public SignOn(SignOnGUI theGUI){
        gui = theGUI;
        userNameText = gui.getUserName();
        passwordText = gui.getPassword();
        errorMessage = gui.getErrorMessage();
        gui.getSignInButton().addActionListener(this);
        gui.getCreateAccountButton().addActionListener(this);
        userNameText.addKeyListener(new TabListener());
        
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        setErrorMessage("");
        
        if (source.getText().equals(CREATE_ACCOUNT)){
            startCreateAccount();
        }else if (userNameText.getText().equals("") || 
                passwordText.getPassword().length == 0){
            setErrorMessage("You must enter a username and a password");
        }else {
            try{
                signInCheck(userNameText.getText(), passwordText.getPassword());
            }catch(NullPointerException ex){
                setErrorMessage("You must enter a username and a password");
            }
        }
    }
    
    private void signInCheck(String userName, char[] charPassword){
        boolean validSignIn = false;
        
        String password = "";
        for (int i = 0; i < charPassword.length; i++)
            password += charPassword[i];
        
        FileInfoRetriever file = new FileInfoRetriever(FILE_NAME);
        
        String fileInfo = file.getFileInfo();
        String[] accounts = fileInfo.split(" ");
        
        String[] userNames = new String[accounts.length/2];
        String[] passwords = new String[accounts.length/2];
        int userNameCount = 0;
        int passwordsCount = 0;
        
        for (int i = 0; i < accounts.length; i++){
            if (i % 2 == 0){
                userNames[userNameCount] = accounts[i].trim();
                userNameCount++;
            }
            else{
                passwords[passwordsCount] = accounts[i].trim();
                passwordsCount++;
            }
        }
        
        for (int i = 0; i < userNames.length; i++){
            if (userName.equals(userNames[i]) && 
                    password.equals(passwords[i])){
                validSignIn = true;
                break;
            }
        }
        
        if (validSignIn)
            startStudyHelper();
        else
            setErrorMessage("Username and password do not match");
    }
    
    private void startStudyHelper(){
        gui.dispose();
        String userFile = userNameText.getText() + ".txt";
        StudyHelper studyHelper = new StudyHelper(userFile);
    }
    
    private void startCreateAccount(){
        CreateAccount createAccount = new CreateAccount(gui);
    }
    
    private void setErrorMessage(String message){
        errorMessage.setText(message);
    }
    
    private boolean passwordsMatch(char[] password1, char[] password2){
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
    
}//End of class SignOn
