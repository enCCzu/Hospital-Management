
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

    // Color Variables
    private Color lightPink = new Color(255, 186, 179);

    /**
     * Constructor Method for sidebar
     */
    public Sidebar(JFrame frame, String secondPanel) {

        // Panel
        setSize(200, 800);

        setBackground(lightPink);

        setLayout(null);

        // Load Image Files
        try {
            // Logo File
            logo = ImageIO.read(getClass().getResource("Images/CovidCaptorSakuraSmall.png"));

            // All Tab Files
            patientImage = ImageIO.read(getClass().getResource("Images/PatientWhite.png"));
            roomImage = ImageIO.read(getClass().getResource("Images/RoomWhite.png"));
            dashboardImage = ImageIO.read(getClass().getResource("Images/DashboardWhite.png"));

        } catch (IOException e) {

            System.out.println("Image is missing. Who stole the images?");

            e.printStackTrace();
        }

        // Add logo Image
        JLabel logoContainer = new JLabel(new ImageIcon(logo));
        // layout
        logoContainer.setBounds(5, 20, logoContainer.getPreferredSize().width, logoContainer.getPreferredSize().height);
        add(logoContainer);

        // Size/position of the buttons
        int buttonIndent = 55;
        int buttonHeight = 150;
        int buttonSpacing = 20;
        int extraWidth = 10;

        // TAB BUTTONS

        // Button to dashboard
        JButton dashboardButton = new JButton("<html>" + "Dashboard" + "</html>");
        dashboardButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        dashboardButton.setBackground(lightPink);
        dashboardButton.setBorder(null);
        // layout
        Dimension size = dashboardButton.getPreferredSize();
        dashboardButton.setBounds(buttonIndent, buttonHeight, size.width, size.height);

        // button to patient page
        JButton patientButton = new JButton("<html>" + "Patients" + "</html>");
        patientButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        patientButton.setBackground(lightPink);
        patientButton.setBorder(null);
        // layout
        size = patientButton.getPreferredSize();
        patientButton.setBounds(buttonIndent, buttonHeight + buttonSpacing + size.height, size.width + extraWidth,
                size.height);

        // button to room page
        JButton roomsButton = new JButton("<html>" + "Rooms" + "</html>");
        roomsButton.setFont(new Font("Verdana", Font.PLAIN, 24));
        roomsButton.setBackground(lightPink);
        roomsButton.setBorder(null);
        // layout
        size = roomsButton.getPreferredSize();
        roomsButton.setBounds(buttonIndent, buttonHeight + (buttonSpacing + size.height) * 2, size.width + extraWidth,
                size.height);

        // Getting database information for the dashboard
        DatabaseController databaseController = new DatabaseController();

        // Getting currently logged-in user
        String username = databaseController.getUsername();

        // Textboxes for username
        JLabel usernameTextTitle = new JLabel("<html>" + "User:" + "</html>");
        usernameTextTitle.setFont(new Font("Verdana", Font.PLAIN, 14));
        size = usernameTextTitle.getPreferredSize();
        usernameTextTitle.setBounds(20, 680, size.width, size.height);

        JLabel usernameTextbox = new JLabel("<html>" + username + "</html>");
        usernameTextbox.setFont(new Font("Verdana", Font.PLAIN, 14));
        size = usernameTextbox.getPreferredSize();
        usernameTextbox.setBounds(20, 700, size.width, size.height);
        
        // Image containers 
        JLabel patientContainer = new JLabel();
        JLabel roomContainer = new JLabel();
        JLabel dashboardContainer = new JLabel();

        // Use red variant depending on which page is clicked
        // ex. if user pressed the Patient page, then the patient button and logo would
        // be red
        try {
            // Sidebar accompanying patient panel
            if (secondPanel.equals("patient")) {

                dashboardButton.setForeground(Color.WHITE);
                patientButton.setForeground(Color.RED);
                roomsButton.setForeground(Color.WHITE);

                patientImage = ImageIO.read(getClass().getResource("Images/PatientRed.png"));
            }
            // Sidebar accompanying room panel
            else if (secondPanel.equals("room")) {

                dashboardButton.setForeground(Color.WHITE);
                patientButton.setForeground(Color.WHITE);
                roomsButton.setForeground(Color.RED);

                roomImage = ImageIO.read(getClass().getResource("Images/RoomRed.png"));
            }
            // Sidebar accompanying dashboard panel
            else if (secondPanel.equals("dashboard")) {

                dashboardButton.setForeground(Color.RED);
                patientButton.setForeground(Color.WHITE);
                roomsButton.setForeground(Color.WHITE);

                dashboardImage = ImageIO.read(getClass().getResource("Images/DashboardRed.png"));
            }

            // Add Tab Images to Panel
            patientContainer = new JLabel(new ImageIcon(patientImage));
            patientContainer.setBounds(5, 197, patientContainer.getPreferredSize().width,
                    patientContainer.getPreferredSize().height);

            roomContainer = new JLabel(new ImageIcon(roomImage));
            roomContainer.setBounds(5, 247, roomContainer.getPreferredSize().width,
                    roomContainer.getPreferredSize().height);

            dashboardContainer = new JLabel(new ImageIcon(dashboardImage));
            dashboardContainer.setBounds(5, 147, dashboardContainer.getPreferredSize().width,
                    dashboardContainer.getPreferredSize().height);

        } catch (IOException e) {

            System.out.println("Images required. Please do not steal the images.");
            System.out.println(e);

        }

        // Add to panel
        add(patientContainer);
        add(roomContainer);
        add(dashboardContainer);
        add(dashboardButton);
        add(patientButton);
        add(roomsButton);
        add(usernameTextTitle);
        add(usernameTextbox);

        // Action Listener for buttons
        InterfaceController sidebarController = new InterfaceController(frame);

        // Actionlistener for patient button
        patientButton.setActionCommand("patientTab");
        patientButton.addActionListener(sidebarController);

        // Actionlistener for room button
        roomsButton.setActionCommand("roomsTab");
        roomsButton.addActionListener(sidebarController);

        // Actionlistener for dashboard button
        dashboardButton.setActionCommand("dashboardTab");
        dashboardButton.addActionListener(sidebarController);

    }

}