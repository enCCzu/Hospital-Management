//imports 
import javax.swing.*;

public class UserInterface extends GraphicalInterfaces {

    /**
     * Constructor 
     */
    public UserInterface(){

        JFrame frame = makeFrame();

        frame.getContentPane().add(new LoginPanel(frame));

        frame.setVisible(true);

    }

}
