import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException; 
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage; 
import java.awt.Font;

public class LoginPanel extends JPanel{

    private BufferedImage logo;

    // @Override 
    // public void paintComponent(Graphics g){

    //     super.paintComponent(g);
    //     g.drawImage(logo, 0, 0, this);

    // }

    private void setPosition(GridBagConstraints constraints, int row, int column, int spanColumn, Insets padding){ // figure out padding or anchor? 

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = spanColumn;
        constraints.insets = padding;
    }

    public LoginPanel(JFrame frame){

        // Panel 

        setBackground(Color.WHITE);

        GridBagLayout gbl = new GridBagLayout(); 
        setLayout(gbl);

        GridBagConstraints constraints = new GridBagConstraints();

        // Action Listener 
        ActionListener actionListener = new TestActionListener(frame, this);

        // Logo 
        try {
            logo = ImageIO.read(new File("CovidCaptorSakura.png"));
        } 

        catch (IOException e){

            e.printStackTrace();
        }

        JLabel logoContainer = new JLabel(new ImageIcon(logo));
        Dimension logoDimensions = new Dimension(351, 99);
        logoContainer.setMinimumSize(logoDimensions);
        logoContainer.setPreferredSize(logoDimensions);
        setPosition(constraints, 0, 0, 3, new Insets(0,0,0,0));
        gbl.setConstraints(logoContainer, constraints);
        add(logoContainer);

        // instructions 
        JLabel signInLabel = new JLabel("Sign In", SwingConstants.CENTER); 
        signInLabel.setFont(new Font("Verdana", Font.PLAIN, 32));
        signInLabel.setOpaque(true);
        signInLabel.setBackground(Color.PINK);
        setPosition(constraints, 1, 2, 1, new Insets(30,0,0,0));
        constraints.ipady = 10;
        gbl.setConstraints(signInLabel, constraints);
        add(signInLabel);

        Dimension textBox = new Dimension(200, 30);

        // username 
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        setPosition(constraints, 2, 2, 1, new Insets(15,0,0,0));
        gbl.setConstraints(usernameLabel, constraints);
        add(usernameLabel);

        JTextField inputUsername = new JTextField();
        setPosition(constraints, 4, 2, 1, new Insets(10,0,0,0));
        gbl.setConstraints(inputUsername, constraints);

        inputUsername.setMinimumSize(textBox);
        inputUsername.setPreferredSize(textBox);
        add(inputUsername);

        //password 
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        setPosition(constraints, 5, 2, 1, new Insets(15,0,0,0));
        gbl.setConstraints(passwordLabel, constraints);
        add(passwordLabel);


        JTextField inputPassword = new JTextField();
        setPosition(constraints, 6, 2, 1, new Insets(10,0,0,0));
        gbl.setConstraints(inputPassword, constraints);
        add(inputPassword);

        inputPassword.setMinimumSize(textBox);
        inputPassword.setPreferredSize(textBox);

        // Login Button
        Dimension buttonDimensions = new Dimension(100, 50);

        JButton loginButton = new JButton("Log In");
        loginButton.setActionCommand("loginButton");
        loginButton.addActionListener(actionListener);
        setPosition(constraints, 7, 2, 1, new Insets(30,0,0,0));
        gbl.setConstraints(loginButton, constraints);
        loginButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        loginButton.setBackground(Color.PINK);
        loginButton.setForeground(Color.BLACK);

        loginButton.setMinimumSize(buttonDimensions);
        loginButton.setPreferredSize(buttonDimensions);

        add(loginButton);

        loginButton.addActionListener(actionListener);


    }
    
}
