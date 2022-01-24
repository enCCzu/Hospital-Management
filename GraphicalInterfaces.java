//Import 
import javax.swing.*; 

abstract class GraphicalInterfaces {
    
    /**
     * 
     * @param frame
     */
    protected void refreshFrame(JFrame frame){

        frame.repaint();
        frame.revalidate();

    }

    /**
     * 
     * @param panel
     */
    protected void refreshPanel(JPanel panel){

        panel.repaint();
        panel.revalidate();

    }

    /**
     * 
     * @param frame
     * @param panel
     */
    protected void deconstructPanel(JFrame frame, JPanel panel){

        frame.remove(panel);
        refreshFrame(frame);

    }

    /**
     * 
     * @return
     */
    protected JFrame makeFrame(){

        JFrame frame = new JFrame("Covidcaptor Sakura"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800); 
        frame.setResizable(false);

        // add frame.setVisible(true); somewhere later in the code 

        return frame;
    }

}
