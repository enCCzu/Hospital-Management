//Imports
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage; 
import java.awt.Font;


public class LoginPanel extends JPanel{
    
    // Covidcaptor Sakura logo
    private BufferedImage logo; 


    /**
     * Sets the position of the component
     * @param constraints Constraints of the container location
     * @param row int of the row number
     * @param column int of column number
     * @param spanColumn int of the number of columns it spans
     * @param padding padding of the insets
     */
    private void setPosition(GridBagConstraints constraints, int row, int column, int spanColumn, Insets padding){ // figure out padding or anchor? 

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = spanColumn;
        constraints.insets = padding;
    }

    /**
     * Login Panel
     * @param frame frame that is being updated
     */
    public LoginPanel(JFrame frame){


        // Panel and Layout

        setBackground(Color.WHITE);

        GridBagLayout gbl = new GridBagLayout(); 
        setLayout(gbl);

        GridBagConstraints constraints = new GridBagConstraints();

        // Logo image 
        try {
            logo = ImageIO.read(new File("Images/CovidCaptorSakura.png"));
        } 

        catch (IOException e){

            e.printStackTrace();
        }

        JLabel logoContainer = new JLabel(new ImageIcon(logo));
        // layout of logo image 
        setPosition(constraints, 0, 0, 3, new Insets(0,0,0,0));
        gbl.setConstraints(logoContainer, constraints);


        // instructions 
        JLabel signInLabel = new JLabel("Sign In", SwingConstants.CENTER); 
        signInLabel.setFont(new Font("Verdana", Font.PLAIN, 32));
        signInLabel.setOpaque(true);
        signInLabel.setBackground(Color.PINK);
        
        // Layout of instructions 
        setPosition(constraints, 1, 2, 1, new Insets(30,0,0,0));
        constraints.ipady = 10;
        gbl.setConstraints(signInLabel, constraints);

        
        // Size for text boxes 
        Dimension textBox = new Dimension(200, 30);

        // Username text 
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        // layout of username text 
        setPosition(constraints, 2, 2, 1, new Insets(15,0,0,0));
        gbl.setConstraints(usernameLabel, constraints);
        
        // Username text box 
        JTextField inputUsername = new JTextField();
        // size of text box 
        inputUsername.setMinimumSize(textBox);
        inputUsername.setPreferredSize(textBox);
        // layout of username text box 
        setPosition(constraints, 4, 2, 1, new Insets(10,0,0,0));
        gbl.setConstraints(inputUsername, constraints);

        // Password text 
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        // layout of the password text
        setPosition(constraints, 5, 2, 1, new Insets(15,0,0,0));
        gbl.setConstraints(passwordLabel, constraints);
        
        // Password text box 
        JPasswordField inputPassword = new JPasswordField();
        // size of text box 
        inputPassword.setMinimumSize(textBox);
        inputPassword.setPreferredSize(textBox);
        // layout of password text box 
        setPosition(constraints, 6, 2, 1, new Insets(10,0,0,0));
        gbl.setConstraints(inputPassword, constraints);

         // Login Button
         Dimension buttonDimensions = new Dimension(100, 50);

         JButton loginButton = new JButton("Log In");
         loginButton.setFont(new Font("Verdana", Font.PLAIN, 18));
         loginButton.setBackground(Color.PINK);
         loginButton.setForeground(Color.BLACK);
         // set size of button 
         loginButton.setMinimumSize(buttonDimensions);
         loginButton.setPreferredSize(buttonDimensions);
         // layout of button 
         setPosition(constraints, 7, 2, 1, new Insets(30,0,0,0));
         gbl.setConstraints(loginButton, constraints);

         // Action Listener 
        InterfaceController actionListener = new InterfaceController(frame);
        actionListener.sendLoginPanel(this, inputUsername, inputPassword);
        // Action listener of button 
        loginButton.setActionCommand("loginButton");
        loginButton.addActionListener(actionListener);

        // Add to panel 
        add(logoContainer);
        add(signInLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(inputPassword);
        add(loginButton);

    }


}
