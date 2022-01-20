// imports 

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

// test action listener - make sure to change later 
public class TestActionListener extends ViewHelperMethods implements ActionListener{

    private JFrame window; 
    private LoginPanel loginPanel;
    private SidePanel sidebar;
    private JButton patientButton;
    private JButton roomButton;
    // private JLabel text = new JLabel("Press button");
    // private int count = 0; 

    public void sendButtons(JButton patientTab, JButton roomTab){

        patientButton = patientTab;
        roomButton = roomTab; 

    }

    public void sendLoginPanel(LoginPanel panel){

        loginPanel = panel;

    }

    public TestActionListener(JFrame frame){

        window = frame; 

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand(); 
        // type some stuff 
        // count++;
        // text.setText("Button was pressed: " + count);

        if (command.equals("loginButton")){
            deconstructPanel(window, loginPanel);
            System.out.println("Button is pressed");
            sidebar = new SidePanel(window, "patient");
            window.getContentPane().add(sidebar);
            window.getContentPane().add(new PatientTab());
            refreshFrame(window);
        }
        if (command.equals("patientTab")){
            window.getContentPane().removeAll();
            System.out.println("Patient button is pressed");
            sidebar = new SidePanel(window, "patient");
            window.getContentPane().add(sidebar);
            window.getContentPane().add(new PatientTab());
            patientButton.setBackground(Color.WHITE);
            roomButton.setBackground(Color.PINK);
            refreshPanel(sidebar);
            refreshFrame(window);
        }

        if (command.equals("roomsTab")){
            window.getContentPane().removeAll();
            System.out.println("Room button is pressed");
            sidebar = new SidePanel(window, "rooms");
            window.getContentPane().add(sidebar);
            window.getContentPane().add(new RoomsTab());
            roomButton.setBackground(Color.WHITE);
            patientButton.setBackground(Color.PINK);
            refreshPanel(sidebar);
            refreshFrame(window);
        }
    }
    
}
