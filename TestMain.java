import javax.swing.*; 
import java.awt.*;

public class TestMain extends ViewHelperMethods{
    

    public TestMain() {

        JFrame frame = makeFrame();

        frame.getContentPane().add(new LoginPanel(frame));

        frame.setVisible(true);

    }
    public static void main(String[] args){

        new TestMain(); 

    }

}
