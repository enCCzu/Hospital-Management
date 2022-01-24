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

    private BufferedImage logo;

    private void setPosition(GridBagConstraints constraints, int row, int column, int spanColumn, Insets padding){ // figure out padding or anchor? 

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = spanColumn;
        constraints.insets = padding;
    }

    public SidePanel(JFrame frame, String secondPanel){
        // JPanel logoPanel = new JPanel();
        // JPanel patientPanel = new JPanel();
        // JPanel roomsPanel = new JPanel();

        Dimension panelSize = new Dimension(200, 400);
        //setPreferredSize(panelSize);
        //setMinimumSize(null);
        //setMaximumSize(panelSize);
        setSize(200, 800);

        //logoPanel.setLocation(0,0);

        //setBackground(Color.PINK);

        TestActionListener actionListener = new TestActionListener(frame);

        // //  Top Label
        try {
            logo = ImageIO.read(getClass().getResource("Images/CovidCaptorSakuraSmall.png"));
            JLabel logoContainer = new JLabel(new ImageIcon(logo));
            logoContainer.setBounds(0, 20, logoContainer.getPreferredSize().width, logoContainer.getPreferredSize().height);
            add(logoContainer);
        } catch (IOException e){

            e.printStackTrace();
        }


        setLayout(null);

        int buttonIndent = 40;
        int buttonHeight = 150;
        int buttonSpacing = 10;

        // Tab Buttons
        JButton dashboardButton = new JButton("Dashboard"); 
        dashboardButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        Dimension size = dashboardButton.getPreferredSize();
        dashboardButton.setBounds(buttonIndent, buttonHeight, size.width, size.height);
        dashboardButton.setBackground(Color.PINK);
        dashboardButton.setBorder(null);

        JButton patientButton = new JButton("Patient"); 
        patientButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        size = patientButton.getPreferredSize();
        patientButton.setBounds(buttonIndent, buttonHeight + buttonSpacing + size.height, size.width, size.height);
        patientButton.setBackground(Color.PINK);
        patientButton.setBorder(null);
        

        JButton roomsButton = new JButton("Rooms");
        roomsButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        size = roomsButton.getPreferredSize();
        roomsButton.setBounds(buttonIndent, buttonHeight + (buttonSpacing + size.height)*2, size.width, size.height);
        roomsButton.setBackground(Color.PINK);
        roomsButton.setBorder(null);
        

        if (secondPanel.equals("patient")){

            dashboardButton.setForeground(Color.WHITE);
            patientButton.setForeground(Color.RED); 
            roomsButton.setForeground(Color.WHITE);
        }
        else if(secondPanel.equals("rooms")){

            dashboardButton.setForeground(Color.WHITE);
            patientButton.setForeground(Color.WHITE);
            roomsButton.setForeground(Color.RED); 
        }
        else if(secondPanel.equals("dashboard")){

            dashboardButton.setForeground(Color.RED);
            patientButton.setForeground(Color.WHITE);
            roomsButton.setForeground(Color.WHITE); 
        }

        add(dashboardButton);
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