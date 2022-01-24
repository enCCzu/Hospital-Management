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

    // Color Variables
    private Color rosePink = new Color(255, 105, 105);
    private Color lightPink = new Color(255, 186, 179);
    private Color erinBlue = new Color(225, 242, 255);

    /**
     * Constructor Method to create dashboard panel
     */
    public Dashboard() {

        // PANEL
        setLayout(null);

        setSize(900, 800);

        // Background colour of the panel 
        setBackground(erinBlue);

        // Add Panel for rectangle
        JPanel rectangle1 = new JPanel();
        rectangle1.setBounds(225, 350, 725, 300);
        rectangle1.setBackground(lightPink);
        rectangle1.setLayout(null);


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

        // Title of the page/panel 
        JLabel title = new JLabel("<html>"+"Dashboard"+"</html>");
        title.setFont(new Font("Verdana", Font.PLAIN, 35));
        title.setForeground(rosePink);
        //layout
        size = title.getPreferredSize();
        title.setBounds(225, 20, size.width, size.height);

        // Creating icons
        JLabel patientContainer = new JLabel();
        JLabel roomContainer = new JLabel();

         try {
            // Patient Icon
            patientIcon = ImageIO.read(new File("Images/patientIcon.png"));
            patientContainer = new JLabel(new ImageIcon(patientIcon));

            // Room Icon
            roomsIcon = ImageIO.read(new File("Images/roomIcon.png"));
            roomContainer = new JLabel(new ImageIcon(roomsIcon));
            
        }

        catch (IOException e) {

            System.out.println("Images required. Please do not steal the images.");
            e.printStackTrace();
        }

        // patient logo layout
        size = patientContainer.getPreferredSize();
        patientContainer.setBounds(25, 25, size.width, size.height); //250,370

        // room logo layout
        size = roomContainer.getPreferredSize();
        roomContainer.setBounds(450, 25, size.width, size.height);

        // Title of patient area of the dashboard 
        JLabel patientTitle = new JLabel("Patients");
        patientTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
        //layout
        size = patientTitle.getMaximumSize();
        patientTitle.setBounds(75, 25, size.width + 100, size.height); //75,370

        // Text showing number of patients 
        JLabel patientNum = new JLabel(patients + " Patients");
        patientNum.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout
        size = patientNum.getMaximumSize();
        patientNum.setBounds(75, 125, size.width + 100, size.height);

        // Title of room area of the dashboard 
        JLabel roomTitle = new JLabel("Rooms");
        roomTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
        //layout
        size = roomTitle.getMaximumSize();
        roomTitle.setBounds(500, 25, size.width + 100, size.height);

        // Text showing the number of rooms 
        JLabel roomNum = new JLabel(rooms + " Rooms");
        roomNum.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout
        size = roomNum.getMaximumSize();
        roomNum.setBounds(500, 125, size.width + 100, size.height);

        // Text showing total number of available beds 
        JLabel beds = new JLabel("Beds: " + availableBeds + "/" + totalBeds);
        beds.setFont(new Font("Verdana", Font.PLAIN, 25));
        //layout 
        size = beds.getMaximumSize();
        beds.setBounds(500, 225, size.width + 100, size.height);

        // Adding objects to panel
        add(rectangle1);
        add(title);
        rectangle1.add(patientContainer);
        rectangle1.add(roomContainer);
        rectangle1.add(patientTitle);
        rectangle1.add(patientNum);
        rectangle1.add(roomTitle);
        rectangle1.add(roomNum);
        rectangle1.add(beds);

    }
}