//imports 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Manages which panel is on the window/frame
public class InterfaceController extends GraphicalInterfaces implements ActionListener{

    // Database controller 
    DatabaseController databaseController = new DatabaseController(); 

    // Declaring variable/object for the GUI window 
    private JFrame window; 

    // Declaring variables/objects for the Login Panel/Page 
    private LoginPanel loginPanel; 
    private JTextField username;
    private JPasswordField password; 


    public void sendLoginPanel(LoginPanel panel, JTextField inputUsername, JPasswordField inputPassword){

        loginPanel = panel; 
        username = inputUsername;
        password = inputPassword;

    }

    private void dashboardPage(){

        // window.getContentPane().add(new Sidebar)
        // window.getContentPane().add(new dashboard)

    }

    public InterfaceController(JFrame frame){

        window = frame; 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        // LOGIN PANEL BUTTON(S)

        if (command.equals("loginButton")){
            // Debugging messages 
            System.out.println("Login button is pressed");

            String savedUsername = username.getText();
            String savedPassword = String.valueOf(password.getPassword());

            if (databaseController.checkPassword(savedUsername, savedPassword)){

                // Remove the login panel from the window 
                deconstructPanel(window, loginPanel);

            }



            // Add new panels to the window 
            //window.getContentPane().add(new Sidebar)
            window.getContentPane().add(new JButton("Hello")); 

        }
        
    }
    
}
