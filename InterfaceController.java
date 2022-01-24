//imports 
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

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

        window.getContentPane().add(new Sidebar(window, "dashboard"));
        window.getContentPane().add(new Dashboard());

    }

    /**
     * Called by the view to update the frame
     * Adds the sidebar and patient
     */
    private void patientPage(){

        window.getContentPane().add(new Sidebar(window, "patient"));
        window.getContentPane().add(new Patient());

    }

    /**
     * Called by the view to update the frame
     * Adds the sidebar and rooms
     */
    private void roomPage(){

        window.getContentPane().add(new Sidebar(window, "room"));
        window.getContentPane().add(new Room());

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

        // LOGIN PANEL BUTTON
        if (command.equals("loginButton")){

            String savedUsername = username.getText();
            String savedPassword = String.valueOf(password.getPassword());

            if (databaseController.checkPassword(savedUsername, savedPassword)){

                // Getting current date/time to store the user's login history
                LocalDateTime date = LocalDateTime.now(); 
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String savedDate = date.format(formatDate);

                // Calling controller to store to database 
                databaseController.storeRecentLogin(savedUsername, savedDate);

                // Remove the login panel from the window 
                deconstructPanel(window, loginPanel);

                dashboardPage();

            }
            else {

                errorMessage.setText("Username or password is incorrect");

                refreshPanel(loginPanel);

            }

            refreshFrame(window);
        }

        // SIDEBAR BUTTONS

        // pressing patient button 
        if (command.equals("patientTab")){

            // removes panels from frame 
            window.getContentPane().removeAll();

            // Adds the correct panels to frame 
            patientPage();

            refreshFrame(window);

        }

        // pressing room button
        else if (command.equals("roomsTab")){

            // removes panels from frame 
            window.getContentPane().removeAll();

            // Adds the correct panels to frame
            roomPage();

            refreshFrame(window);


        }

        // pressing dashboard button 
        else if (command.equals("dashboardTab")){

            // removes panels from frame 
            window.getContentPane().removeAll();

            // Adds the correct panels to frame
            dashboardPage();

            refreshFrame(window);

        }
        
    }
    
}
