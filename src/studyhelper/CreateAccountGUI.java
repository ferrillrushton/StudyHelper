/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 *
 * @author Ferrill
 */
public class CreateAccountGUI {
    
    private JLabel firstNameLabel, lastNameLabel, userNameLabel, passwordLabel, 
            passwordAgainLabel;
    private JTextArea firstName, lastName, userName;
    private JPasswordField password, passwordAgain;
    private JButton createAccount, goBack;
    private JLabel errorMessage;
    private JPanel container, fields;
    private SignOnGUI frame;
    
    public JTextArea getFirstName()
    {return firstName;}
    
    public JTextArea getLastName()
    {return lastName;}
    
    public JTextArea getUserName()
    {return userName;}
    
    public JPasswordField getPassword()
    {return password;}
    
    public JPasswordField getPasswordAgain()
    {return passwordAgain;}
    
    public JButton getCreateAccountButton()
    {return createAccount;}
    
    public JButton getGoBackButton()
    {return goBack;}
    
    public JLabel getErrorMessage()
    {return errorMessage;}
    
    public JPanel getContainer()
    {return container;}
    
    public CreateAccountGUI(SignOnGUI theFrame){
        frame = theFrame;
        init();
    }
    
    private void init(){
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        userNameLabel = new JLabel("User Name");
        passwordLabel = new JLabel("Password");
        passwordAgainLabel = new JLabel("Re-Enter Password");
        
        firstName = new JTextArea();
        lastName = new JTextArea();
        userName = new JTextArea();
        password = new JPasswordField();
        passwordAgain = new JPasswordField();
        
        createAccount = new JButton("Create Account");
        goBack = new JButton("Go back");
        
        errorMessage = new JLabel();
        
        firstName.setPreferredSize(new Dimension(200, 16));
        lastName.setPreferredSize(new Dimension(200, 16));
        userName.setPreferredSize(new Dimension(200, 16));
        password.setPreferredSize(new Dimension(200, 20));
        passwordAgain.setPreferredSize(new Dimension(200, 20));
        
        createAccount.setPreferredSize(new Dimension(150, 50));
        goBack.setPreferredSize(new Dimension(150, 50));
        
        fields = new JPanel(new GridLayout(5, 2, 0, 5));
        fields.setPreferredSize(new Dimension(frame.getWidth()-10, frame.getHeight()/2));
        container = new JPanel();
        
        fields.add(firstNameLabel);
        fields.add(firstName);
        fields.add(lastNameLabel);
        fields.add(lastName);
        fields.add(userNameLabel);
        fields.add(userName);
        fields.add(passwordLabel);
        fields.add(password);
        fields.add(passwordAgainLabel);
        fields.add(passwordAgain);
        container.add(fields);
        container.add(createAccount);
        container.add(goBack);
        container.add(errorMessage);
    }
    
}//End of class CreateAccountGUI
