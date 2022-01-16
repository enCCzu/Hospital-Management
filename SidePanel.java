import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class SidePanel extends JPanel{

    public SidePanel(){

        setPreferredSize(null);

        setBackground(Color.PINK);

        ActionListener actionListener = new TestActionListener(this);

        //  Top Label
        JLabel logoLabel = new JLabel("CovidCaptur Sakura"); 

        // Tab Buttons
        JButton patientButton = new JButton("Patient"); 
        JButton roomsButton = new JButton("Rooms"); 

        //Action Listener for buttons
        patientButton.setActionCommand("Patient Tab");
        patientButton.addActionListener(actionListener);

        roomsButton.setActionCommand("Rooms Tab");
        roomsButton.addActionListener(actionListener);
    }
}