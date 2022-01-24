
//imports 
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dashboard extends JPanel {

    // Icons 
    private BufferedImage patientIcon;
    private BufferedImage roomsIcon;
    
    // Declaring variable to store size of containers/objects 
    private Dimension size; 

    public Dashboard() {

        // PANEL
        setLayout(null);

        setSize(900, 800);

        // Background colour of the panel 
        Color lightSteelBlue = new Color(176, 193, 219);

        setBackground(lightSteelBlue);

        // Getting database information for the dashboard 
        DatabaseController databaseController = new DatabaseController();
        // patient #
        int patients = databaseController.getNumberOfPatients();
        // room #
        int rooms = databaseController.getNumberOfRooms();
        // available beds #
        int availableBeds = databaseController.getAvailableBeds();
        // bed #
        int totalBeds = databaseController.getTotalBeds();


        // Creating icons
        try {
            patientIcon = ImageIO.read(new File("patientIcon.png"));
            roomsIcon = ImageIO.read(new File("roomIcon.png"));
        }

        catch (IOException e) {

            e.printStackTrace();
        }

        // Patient Icon
        JLabel patientContainer = new JLabel(new ImageIcon(patientIcon));
        //layout
        size = patientContainer.getPreferredSize();
        patientContainer.setBounds(250, 370, size.width, size.height);

        // Room Icon
        JLabel roomContainer = new JLabel(new ImageIcon(roomsIcon));
        //layout
        size = roomContainer.getPreferredSize();
        roomContainer.setBounds(650, 370, size.width, size.height);

        // Title of the page/panel 
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Verdana", Font.PLAIN, 35));
        //layout
        size = title.getPreferredSize();
        title.setBounds(250, 70, size.width, size.height);

        // Title of patient area of the dashboard 
        JLabel patientTitle = new JLabel("Patients");
        patientTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
        //layout
        size = patientTitle.getPreferredSize();
        patientTitle.setBounds(300, 370, size.width, size.height);

        // Text showing number of patients 
        JLabel patientNum = new JLabel(patients + " Patients");
        patientNum.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout
        size = patientNum.getPreferredSize();
        patientNum.setBounds(250, 470, size.width, size.height);

        // Title of room area of the dashboard 
        JLabel roomTitle = new JLabel("Rooms");
        roomTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
        //layout
        size = roomTitle.getPreferredSize();
        roomTitle.setBounds(700, 370, size.width, size.height);

        // Text showing the number of rooms 
        JLabel roomNum = new JLabel(rooms + " Rooms");
        roomNum.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout
        size = roomNum.getPreferredSize();
        roomNum.setBounds(650, 470, size.width, size.height);

        // Text showing total number of available beds 
        JLabel beds = new JLabel("Beds: " + availableBeds + "/" + totalBeds);
        beds.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout 
        size = beds.getPreferredSize();
        beds.setBounds(650, 570, size.width, size.height);

        // Adding objects to panel 
        add(patientContainer);
        add(roomContainer);
        add(title);
        add(patientTitle);
        add(patientNum);
        add(roomTitle);
        add(roomNum);
        add(beds);

    }
}