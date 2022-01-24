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

    /**
     * Saves the Login information that the user entered
     * @param panel Login Panel being sent
     * @param inputUsername Username the user entered
     * @param inputPassword Password the user entered
     * @param wrongInput Invalid input message
     */
    public void sendLoginPanel(LoginPanel panel, JTextField inputUsername, JPasswordField inputPassword, JLabel wrongInput){

        loginPanel = panel; 
        username = inputUsername;
        password = inputPassword;
        errorMessage = wrongInput;

    }

    /**
     * Called by the view to update the frame
     * Adds the sidebar and dashboard
     */
    private void dashboardPage(){

        // window.getContentPane().add(new Sidebar)
        // window.getContentPane().add(new dashboard)

    }

    /**
     * Constructor for the interface controller
     * @param frame frame being saved
     */
    public InterfaceController(JFrame frame){

        window = frame; 

    }

    @Override
    /**
     * Takes in the actions performed 
     * Performs an action when a button is clicked
     * @param e action that occurred
     */
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
