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
    private JLabel errorMessage; 


    public void sendLoginPanel(LoginPanel panel, JTextField inputUsername, JPasswordField inputPassword, JLabel wrongInput){

        loginPanel = panel; 
        username = inputUsername;
        password = inputPassword;
        errorMessage = wrongInput;

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

            else {

                System.out.println("Password not right");

                errorMessage.setText("Username or password is incorrect");

                refreshPanel(loginPanel);

            }

            refreshFrame(window);


            // Add new panels to the window 
            //window.getContentPane().add(new Sidebar)

        }
        
    }
    
}
