//Import 
import javax.swing.*; 

abstract class GraphicalInterfaces {
    
    /**
     * Refreshes the frame 
     * @param frame JFrame to be refreshed
     */
    protected void refreshFrame(JFrame frame){

        frame.repaint();
        frame.revalidate();

    }

    /**
     * Refreshes the panel
     * @param panel JPanel to be refreshed
     */
    protected void refreshPanel(JPanel panel){

        panel.repaint();
        panel.revalidate();

    }

    /**
     * Deconstructs the panel from the frame
     * @param frame JFrame the panel will be removed from
     * @param panel JPanel that will be removed
     */
    protected void deconstructPanel(JFrame frame, JPanel panel){

        frame.remove(panel);
        refreshFrame(frame);

    }

    /**
     * Makes the frame that will be used
     * @return JFrame that is used
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
