//imports 
import javax.swing.*;
import java.awt.*;
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
     * 
     * @param panel
     * @param inputUsername
     * @param inputPassword
     * @param wrongInput
     */
    public void sendLoginPanel(LoginPanel panel, JTextField inputUsername, JPasswordField inputPassword, JLabel wrongInput){

        loginPanel = panel; 
        username = inputUsername;
        password = inputPassword;
        errorMessage = wrongInput;

    }

    /**
     * 
     */
    private void dashboardPage(){

        window.getContentPane().add(new Sidebar(window, "dashboard"));
        window.getContentPane().add(new Dashboard());

    }

    /**
     * 
     */
    private void patientPage(){

        window.getContentPane().add(new Sidebar(window, "patient"));
        window.getContentPane().add(new Patient());

    }

    private void roomPage(){

        window.getContentPane().add(new Sidebar(window, "room"));
        //window.getContentPane().add(new Room());

    }


    /**
     * 
     * @param frame
     */
    public InterfaceController(JFrame frame){

        window = frame; 

    }

    /**
     * 
     */
    @Override
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

            window.getContentPane().removeAll();

            patientPage();

            refreshFrame(window);

        }


        else if (command.equals("roomsTab")){

            window.getContentPane().removeAll();

            roomPage();

            refreshFrame(window);


        }

        else if (command.equals("dashboardTab")){

            window.getContentPane().removeAll();

            dashboardPage();

            refreshFrame(window);

        }
        
    }
    
}
