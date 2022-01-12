import javax.swing.*; 
import java.awt.*;


abstract class ViewHelperMethods {
    
    protected void refreshFrame(JFrame frame){

        frame.repaint();
        frame.revalidate();

    }

    protected void refreshPanel(JPanel panel){

        panel.repaint();
        panel.revalidate();

    }

    protected void deconstructPanel(JFrame frame, JPanel panel){

        frame.remove(panel);

        refreshFrame(frame);

    }

    protected JFrame makeFrame(){

        JFrame frame = new JFrame("Covidcaptor Sakura"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800); // or frame.pack();

        // add frame.setVisible(true); somewhere later in the code 

        return frame;
    }

}
