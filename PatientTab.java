import javax.swing.*; 
import java.awt.*;

public class PatientTab extends JPanel{
    //Top Label of Tab
    JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);

    //Buttons to edit and add to the table
    JButton editButton = new JButton("Edit");
    JButton addButton = new JButton("Add");

    //Constructor
    public PatientTab() {
        //Size of Window
        setSize(900, 800);
        
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
        setLayout(null);

        //Add elements to panel
        add(titleLabel);
        add(editButton);
        add(addButton);
    } 
}