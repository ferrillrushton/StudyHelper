/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class SignOnGUI extends JFrame{
    
    private final static int WIDTH = 400, HEIGHT = 300;
    
    private JTextArea userName;
    private JPasswordField password;
    private JLabel welcomeMessage, signInMessage, errorMessage, userLabel,
            passwordLabel;
    private JButton signIn, createAccount;
    private JPanel container, userCon, passCon, buttons;
    
    public JTextArea getUserName()
    {return userName;}
    
    public JPasswordField getPassword()
    {return password;}
    
    public JLabel getErrorMessage()
    {return errorMessage;}
    
    public JButton getSignInButton()
    {return signIn;}
    
    public JButton getCreateAccountButton()
    {return createAccount;}
    
    public JPanel getContainer()
    {return container;}
    
    public SignOnGUI(){
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }
    
    private void init(){
        welcomeMessage = new JLabel("Welcome to Study-Helper!");
        signInMessage = new JLabel("Please either sign in, or create a new account.");
        userLabel = new JLabel("Username");
        userName = new JTextArea();
        userName.setBorder(BorderFactory.createEtchedBorder());
        passwordLabel = new JLabel("Password");
        password = new JPasswordField();
        password.setBorder(BorderFactory.createEtchedBorder());
        signIn = new JButton("Sign in");
        createAccount = new JButton("Create new account");
        errorMessage = new JLabel();
        
        container = new JPanel();
        userCon = new JPanel();
        userCon.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        passCon = new JPanel();
        passCon.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        buttons = new JPanel();
        
        userName.setPreferredSize(new Dimension(200, 18));
        password.setPreferredSize(new Dimension(200, 20));
        signIn.setPreferredSize(new Dimension(150, 70));
        createAccount.setPreferredSize(new Dimension(150, 70));
        
        container.add(welcomeMessage);
        container.add(signInMessage);
        userCon.add(userLabel);
        userCon.add(userName);
        passCon.add(passwordLabel);
        passCon.add(password);
        buttons.add(signIn);
        buttons.add(createAccount);
        container.add(userCon);
        container.add(passCon);
        container.add(buttons);
        container.add(errorMessage);
        
        add(container);
    }
    
    public void swap(JPanel newContainer){
        userName.setText("");
        password.setText("");
        remove(container);
        add(newContainer);
        revalidate();
        repaint();
    }
    
}//End of class SignOnGUI
