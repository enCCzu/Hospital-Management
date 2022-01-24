
// imports 
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class Sidebar extends JPanel {

    // Logo
    private BufferedImage logo, dashboardImage, patientImage, roomImage;

    public Sidebar(JFrame frame, String secondPanel){

        setSize(200, 800);

        setBackground(Color.PINK);

        setLayout(null);

        // Load Image Files 
        try {
            //Logo File
            logo = ImageIO.read(getClass().getResource("Images/CovidCaptorSakuraSmall.png"));
    
            //All Tab Files
            patientImage = ImageIO.read(getClass().getResource("Images/PatientWhite.png"));
            roomImage = ImageIO.read(getClass().getResource("Images/RoomWhite.png"));
            dashboardImage = ImageIO.read(getClass().getResource("Images/DashboardWhite.png"));

            //Use red variant for selected tab
            if(secondPanel.equals("patient")){
                patientImage = ImageIO.read(getClass().getResource("Images/PatientRed.png"));
            }
            else if(secondPanel.equals("room")){
                roomImage = ImageIO.read(getClass().getResource("Images/RoomRed.png"));
            }
            else if(secondPanel.equals("dashboard")){
                dashboardImage = ImageIO.read(getClass().getResource("Images/DashboardRed.png"));
            }

            //Add Tab Images to Panel
            JLabel patientContainer = new JLabel(new ImageIcon(patientImage));
            patientContainer.setBounds(0, 197, patientContainer.getPreferredSize().width, patientContainer.getPreferredSize().height);
            add(patientContainer);

            JLabel roomContainer = new JLabel(new ImageIcon(roomImage));
            roomContainer.setBounds(0, 247, roomContainer.getPreferredSize().width, roomContainer.getPreferredSize().height);
            add(roomContainer);
            
            JLabel dashboardContainer = new JLabel(new ImageIcon(dashboardImage));
            dashboardContainer.setBounds(0, 147, dashboardContainer.getPreferredSize().width, dashboardContainer.getPreferredSize().height);
            add(dashboardContainer);
        } catch (IOException e){

            e.printStackTrace();
        }
        
        //Add logo Image
        JLabel logoContainer = new JLabel(new ImageIcon(logo));
        //layout
        logoContainer.setBounds(0, 20, logoContainer.getPreferredSize().width, logoContainer.getPreferredSize().height);
        add(logoContainer);

        // Size/position of the buttons 
        int buttonIndent = 50;
        int buttonHeight = 150;
        int buttonSpacing = 20;

        // TAB BUTTONS
        
        // Button to dashboard 
        JButton dashboardButton = new JButton("Dashboard"); 
        dashboardButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        dashboardButton.setBackground(Color.PINK);
        dashboardButton.setBorder(null);
        // layout
        Dimension size = dashboardButton.getPreferredSize();
        dashboardButton.setBounds(buttonIndent, buttonHeight, size.width, size.height);
        
        // button to patient page 
        JButton patientButton = new JButton("Patient"); 
        patientButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        patientButton.setBackground(Color.PINK);
        patientButton.setBorder(null);
        // layout
        size = patientButton.getPreferredSize();
        patientButton.setBounds(buttonIndent, buttonHeight + buttonSpacing + size.height, size.width, size.height);
        
        // button to room page 
        JButton roomsButton = new JButton("Rooms");
        roomsButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        roomsButton.setBackground(Color.PINK);
        roomsButton.setBorder(null);
        // layout
        size = roomsButton.getPreferredSize();
        roomsButton.setBounds(buttonIndent, buttonHeight + (buttonSpacing + size.height)*2, size.width, size.height);


        // Getting database information for the dashboard 
        DatabaseController databaseController = new DatabaseController();
        // patient #
        String username = databaseController.getUsername();


        // Textbox for username 
        JLabel usernameTextbox = new JLabel("Username: " + username);
        usernameTextbox.setFont(new Font("Verdana", Font.PLAIN, 14));
        size = usernameTextbox.getPreferredSize();
        usernameTextbox.setBounds(20, 700, size.width, size.height);

        
        // Colour of button text depends on which page is clicked 
            // ex. if user pressed the Patient page, then the patient button would be red 
        if (secondPanel.equals("patient")){

            dashboardButton.setForeground(Color.WHITE);
            patientButton.setForeground(Color.RED); 
            roomsButton.setForeground(Color.WHITE);
        }
        else if(secondPanel.equals("room")){

            dashboardButton.setForeground(Color.WHITE);
            patientButton.setForeground(Color.WHITE);
            roomsButton.setForeground(Color.RED); 
        }
        else if(secondPanel.equals("dashboard")){

            dashboardButton.setForeground(Color.RED);
            patientButton.setForeground(Color.WHITE);
            roomsButton.setForeground(Color.WHITE); 
        }

        // Add to panel 
        add(dashboardButton);
        add(patientButton);
        add(roomsButton);
        add(usernameTextbox);

        //Action Listener for buttons
        InterfaceController sidebarController = new InterfaceController(frame);

        patientButton.setActionCommand("patientTab");
        patientButton.addActionListener(sidebarController);

        roomsButton.setActionCommand("roomsTab");
        roomsButton.addActionListener(sidebarController);

    }

}
