//imports 

import javax.swing.*; 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Dashboard extends JPanel {
  
  //Images variables
  private BufferedImage patientIcon;
  private BufferedImage roomsIcon;
  
  public Dashboard(int patients, int rooms, int availableBeds, int totalBeds){
    
    //Layout set to null
    setLayout(null); 
    
    //Size of panel
    setSize(900,800); 
    
    //Set background colour
    setBackground(Color.WHITE);
    
    //Reads the image files
    try {
       patientIcon = ImageIO.read(new File("patientIcon.png"));
       roomsIcon = ImageIO.read(new File("roomIcon.png"));
    } 

    catch (IOException e){

       e.printStackTrace();
    }
    
    //JLabel for patient image
    JLabel patientContainer = new JLabel(new ImageIcon(patientIcon));
    Dimension size = patientContainer.getPreferredSize();
    patientContainer.setBounds(250, 370, size.width, size.height);
  
    //JLabel for room image
    JLabel roomContainer = new JLabel(new ImageIcon(roomsIcon));
    size = roomContainer.getPreferredSize();
    roomContainer.setBounds(650, 370, size.width, size.height);
    
    //JLabel for Main Title
    JLabel title = new JLabel("Dashboard");
    title.setFont(new Font("Verdana", Font.PLAIN, 35));
    size = title.getPreferredSize();
    title.setBounds(250, 70, size.width, size.height);


    //JLabel for Patient Subheading
    JLabel patientTitle = new JLabel("Patients");
    patientTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
    size = patientTitle.getPreferredSize();
    patientTitle.setBounds(300, 370, size.width, size.height);

    //JLabel for number of patients
    JLabel patientNum = new JLabel(patients + " Patients");
    patientNum.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = patientNum.getPreferredSize();
    patientNum.setBounds(250, 470, size.width, size.height);

    //JLabel for Rooms Subheadings
    JLabel roomTitle = new JLabel("Rooms");
    roomTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
    size = roomTitle.getPreferredSize();
    roomTitle.setBounds(700, 370, size.width, size.height);

    //JLabel for number of rooms
    JLabel roomNum = new JLabel(rooms + " Rooms");
    roomNum.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = roomNum.getPreferredSize();
    roomNum.setBounds(650, 470, size.width, size.height);

    //JLabel for available beds
    JLabel beds = new JLabel("Beds: " + availableBeds + "/" + totalBeds);
    beds.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = beds.getPreferredSize();
    beds.setBounds(650, 570, size.width, size.height);
    
    //Add all elements
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
