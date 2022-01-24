import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException; 
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage; 
import java.awt.Font;

public class SidePanel extends JPanel{

    private void setPosition(GridBagConstraints constraints, int row, int column, int spanColumn, Insets padding){ // figure out padding or anchor? 

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = spanColumn;
        constraints.insets = padding;
    }

    //Constructor
    public SidePanel(JFrame frame, String secondPanel){
        // JPanel logoPanel = new JPanel();
        // JPanel patientPanel = new JPanel();
        // JPanel roomsPanel = new JPanel();

        Dimension panelSize = new Dimension(200, 400);
        //setPreferredSize(panelSize);
        //setMinimumSize(null);
        //setMaximumSize(panelSize);
        setSize(200, 800);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        GridBagLayout gbl = new GridBagLayout(); 
        setLayout(gbl);

        GridBagConstraints constraints = new GridBagConstraints();

        //logoPanel.setLocation(0,0);

        //setBackground(Color.PINK);

        TestActionListener actionListener = new TestActionListener(frame);

        // //  Top Label
        // JLabel logoLabel = new JLabel("CovidCaptor Sakura"); 
        // logoLabel.setBounds(0,0,80,30);
        // add(logoLabel);

        // Tab Buttons
        JButton patientButton = new JButton("Patient"); 
        setPosition(constraints, 1, 0, 1, new Insets(10,0,0,0));
        gbl.setConstraints(patientButton, constraints);
        

        JButton roomsButton = new JButton("Rooms");
        setPosition(constraints, 2, 0, 1, new Insets(10,0,0,0));
        gbl.setConstraints(roomsButton, constraints);
        

        if (secondPanel.equals("patient")){

            patientButton.setBackground(Color.WHITE);
            roomsButton.setBackground(Color.PINK); 
        }
        else if(secondPanel.equals("rooms")){

            patientButton.setBackground(Color.PINK);
            roomsButton.setBackground(Color.WHITE); 
        }

        add(patientButton);
        add(roomsButton);

        // logoPanel.add(logoLabel);
        // patientPanel.add(patientButton);
        // roomsPanel.add(roomsButton);


        //Action Listener for buttons

        actionListener.sendButtons(patientButton, roomsButton);

        patientButton.setActionCommand("patientTab");
        patientButton.addActionListener(actionListener);

        roomsButton.setActionCommand("roomsTab");
        roomsButton.addActionListener(actionListener);
        setBackground(Color.PINK);
        System.out.println(getSize());
    }
}