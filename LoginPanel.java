import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class LoginPanel extends JPanel{

    public LoginPanel(){

        setPreferredSize(null);

        setBackground(Color.PINK);

        ActionListener actionListener = new TestActionListener(this);

        // username 
        JLabel usernameLabel = new JLabel(); 
        JTextField inputUsername = new JTextField();

        //password 
        JLabel passwordLabel = new JLabel();
        JTextField inputPassword = new JTextField(); 

        // Login Button

        JButton loginButton = new JButton("Log In"); 
        loginButton.setActionCommand("loginButton");
        loginButton.addActionListener(actionListener);





    }
    
}
