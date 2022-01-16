import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;

public class SidePanel extends JPanel{

    public SidePanel(JFrame frame){

        Dimension panelSize = new Dimension(200, 800);
        setPreferredSize(panelSize);
        setMinimumSize(panelSize);
        setMaximumSize(panelSize);

        setBackground(Color.PINK);

        ActionListener actionListener = new TestActionListener(frame, this);

        //  Top Label
        JLabel logoLabel = new JLabel("CovidCaptur Sakura"); 

        // Tab Buttons
        JButton patientButton = new JButton("Patient"); 
        JButton roomsButton = new JButton("Rooms"); 
        add(logoLabel);
        add(patientButton);
        add(roomsButton);

        //Action Listener for buttons
        patientButton.setActionCommand("Patient Tab");
        patientButton.addActionListener(actionListener);

        roomsButton.setActionCommand("Rooms Tab");
        roomsButton.addActionListener(actionListener);
    }
}