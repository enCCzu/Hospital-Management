import javax.swing.*; 
import java.awt.event.*;

public class TestActionListener extends ViewHelperMethods implements ActionListener{

    private JFrame window; 
    private JPanel pan;
    // private JLabel text = new JLabel("Press button");
    // private int count = 0; 

    public TestActionListener(JFrame frame, JPanel panel){

        window = frame; 
        pan = panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand(); 
        // type some stuff 
        // count++;
        // text.setText("Button was pressed: " + count);

        if (command.equals("loginButton")){
            deconstructPanel(window, pan);
            System.out.println("Button is pressed");
            window.getContentPane().add(new SidePanel(window));
            window.getContentPane().add(new PatientTab());
            refreshFrame(window);
        }
        if (command.equals("patientTab")){
            window.getContentPane().removeAll();
            System.out.println("Patient button is pressed");
            window.getContentPane().add(new SidePanel(window));
            window.getContentPane().add(new PatientTab());
            refreshFrame(window);
        }

        if (command.equals("roomsTab")){
            window.getContentPane().removeAll();
            System.out.println("Room button is pressed");
            window.getContentPane().add(new SidePanel(window));
            window.getContentPane().add(new RoomsTab());
            refreshFrame(window);
        }
    }
    
}
