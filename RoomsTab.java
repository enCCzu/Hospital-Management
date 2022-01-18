import javax.swing.*; 
import java.awt.*;

public class RoomsTab extends JPanel{

    //Top Label of Tab
    JLabel titleLabel = new JLabel("Rooms", JLabel.RIGHT);

    //Buttons to edit and add to the table
    JButton editButton = new JButton("Edit");
    JButton addButton = new JButton("Add");

    //Constructor
    public RoomsTab() {

        //Null layout
        setLayout(null);
        //Size of Window
        setSize(900, 800);
        
        //Top label font and placement
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(230, 70, size.width + 70, size.height);

        //Edit button and placement
        size = editButton.getPreferredSize();
        editButton.setBounds(330, 130, size.width + 70, size.height);

        //Add button and placement
        size = addButton.getPreferredSize();
        addButton.setBounds(500, 130, size.width + 70, size.height);

        //Add elements to panel
        add(titleLabel);
        add(editButton);
        add(addButton);

    } 
}