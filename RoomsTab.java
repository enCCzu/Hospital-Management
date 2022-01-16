import javax.swing.*; 
import java.awt.*;

public class RoomsTab extends JPanel{
    JPanel roomPanel = new JPanel();

    //Top Label of Tab
    JLabel titleLabel = new JLabel("Rooms", JLabel.RIGHT);

    //Buttons to edit and add to the table
    JButton editButton = new JButton("Edit");
    JButton addButton = new JButton("Add");

    //Constructor
    public RoomsTab() {
        //Size of Window
        setSize(1100, 800);
        
        //Top label font and placement
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(230, 70, size.width, size.height);

        //Edit button and placement
        size = editButton.getPreferredSize();
        editButton.setBounds(900, 70, size.width, size.height);

        //Add button and placement
        size = addButton.getPreferredSize();
        addButton.setBounds(920 + editButton.getPreferredSize().width, 70, size.width, size.height);

        //Null layout
        roomPanel.setLayout(null);

        //Add elements to panel
        roomPanel.add(titleLabel);
        roomPanel.add(editButton);
        roomPanel.add(addButton);
        
        //Add to frame
        add(roomPanel);

    } 
}