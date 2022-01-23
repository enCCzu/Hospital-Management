//imports 

import javax.swing.*; 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Dashboard extends JPanel {
  
  private BufferedImage patientIcon;
  private BufferedImage roomsIcon;
  
  public Dashboard(int patients, int rooms, int availableBeds, int totalBeds){
    
    
    setLayout(null); 
    
    setSize(900,800); 
    
    setBackground(Color.WHITE);
    
    try {
       patientIcon = ImageIO.read(new File("patientIcon.png"));
       roomsIcon = ImageIO.read(new File("roomIcon.png"));
    } 

    catch (IOException e){

       e.printStackTrace();
    }
    
    JLabel patientContainer = new JLabel(new ImageIcon(patientIcon));
    Dimension size = patientContainer.getPreferredSize();
    patientContainer.setBounds(250, 370, size.width, size.height);

    JLabel roomContainer = new JLabel(new ImageIcon(roomsIcon));
    size = roomContainer.getPreferredSize();
    roomContainer.setBounds(650, 370, size.width, size.height);
    
    JLabel title = new JLabel("Dashboard");
    title.setFont(new Font("Verdana", Font.PLAIN, 35));
    size = title.getPreferredSize();
    title.setBounds(250, 70, size.width, size.height);


    JLabel patientTitle = new JLabel("Patients");
    patientTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
    size = patientTitle.getPreferredSize();
    patientTitle.setBounds(300, 370, size.width, size.height);

    JLabel patientNum = new JLabel(patients + " Patients");
    patientNum.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = patientNum.getPreferredSize();
    patientNum.setBounds(250, 470, size.width, size.height);

    JLabel roomTitle = new JLabel("Rooms");
    roomTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
    size = roomTitle.getPreferredSize();
    roomTitle.setBounds(700, 370, size.width, size.height);

    JLabel roomNum = new JLabel(rooms + " Rooms");
    roomNum.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = roomNum.getPreferredSize();
    roomNum.setBounds(650, 470, size.width, size.height);

    JLabel beds = new JLabel("Beds: " + availableBeds + "/" + totalBeds);
    beds.setFont(new Font("Verdana", Font.PLAIN, 25));
    size = beds.getPreferredSize();
    beds.setBounds(650, 570, size.width, size.height);
    
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