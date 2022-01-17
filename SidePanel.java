import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;

public class SidePanel extends JPanel{

    public SidePanel(JFrame frame){
        // JPanel logoPanel = new JPanel();
        // JPanel patientPanel = new JPanel();
        // JPanel roomsPanel = new JPanel();

        Dimension panelSize = new Dimension(200, 400);
        //setPreferredSize(panelSize);
        //setMinimumSize(null);
        //setMaximumSize(panelSize);
        setSize(200, 800);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //logoPanel.setLocation(0,0);

        //setBackground(Color.PINK);

        ActionListener actionListener = new TestActionListener(frame, this);

        //  Top Label
        JLabel logoLabel = new JLabel("CovidCaptur Sakura"); 

        // Tab Buttons
        JButton patientButton = new JButton("Patient"); 
        JButton roomsButton = new JButton("Rooms"); 

        // logoPanel.add(logoLabel);
        // patientPanel.add(patientButton);
        // roomsPanel.add(roomsButton);

        add(logoLabel, 0);
        add(patientButton);
        add(roomsButton);

        //Action Listener for buttons
        patientButton.setActionCommand("Patient Tab");
        patientButton.addActionListener(actionListener);

        roomsButton.setActionCommand("Rooms Tab");
        roomsButton.addActionListener(actionListener);
        setBackground(Color.PINK);
        System.out.println(getSize());
    }
}