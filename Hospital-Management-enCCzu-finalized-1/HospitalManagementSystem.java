// Imports 
import javax.swing.*; 

public class HospitalManagementSystem {
    
    // Constructor
    public HospitalManagementSystem(){

        JFrame frame = new JFrame(); 

        frame.setSize(1100,800);

        frame.getContentPane().add(new Dashboard()); 

        frame.repaint();
        frame.revalidate();

        frame.setVisible(true);

    }


    public static void main(String[] args) {


        new HospitalManagementSystem();


    }


}


