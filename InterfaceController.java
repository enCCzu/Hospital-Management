//imports 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceController extends GraphicalInterfaces implements ActionListener{

    // Declaring variable/object for the GUI window 
    private JFrame window; 

    // Declaring variables/objects for the Login Panel/Page 
    private LoginPanel loginPanel; 


    public void sendLoginPanel(LoginPanel panel){

        loginPanel = panel; 

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

            // Remove the login panel from the window 
            deconstructPanel(window, loginPanel);

            // Add new panels to the window 
            //window.getContentPane().add(new Sidebar)
            window.getContentPane().add(new JButton("Hello")); 

        }
        
    }
    
}
