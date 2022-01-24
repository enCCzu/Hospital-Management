
// imports 
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Insets;

public class Patient extends JPanel {

    // JTtable
    private DefaultTableModel model = new DefaultTableModel();
    private JTable patientTable = new JTable(model);
    private JScrollPane scrollPane = new JScrollPane(patientTable);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(patientTable.getModel());
    private JTextField jtfFilter = new JTextField();

    // Constructor
    public Patient() {
        // Size of Window
        setSize(900, 800);


        // Buttons to edit and add to the table
        JButton deleteButton = new JButton();
        JButton addButton = new JButton();

        // Title of page 
        JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        // position
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(225, 20, size.width, size.height);

        // Read and use images for add and delete row buttons
        try {
            BufferedImage addImage = ImageIO.read(getClass().getResource("add.png"));
            addButton.setIcon(new ImageIcon(addImage));

            BufferedImage minusImage = ImageIO.read(getClass().getResource("minus.png"));
            deleteButton.setIcon(new ImageIcon(minusImage));
        } catch (Exception ex) {
            System.out.println(ex);

        }

        // Edit button and placement
        size = deleteButton.getPreferredSize();
        deleteButton.setBounds(225, 705, 30, 30);
        // to remote the spacing between the image and button's borders
        deleteButton.setMargin(new Insets(0, 0, 0, 0));
        // to add a different background
        deleteButton.setBackground(Color.WHITE);
        // to remove the border
        deleteButton.setBorder(null);

        // Add button and placement
        size = addButton.getPreferredSize();
        addButton.setBounds(275, 705, 30, 30);
        addButton.setMargin(new Insets(0, 0, 0, 0));
        // to add a different background
        addButton.setBackground(Color.WHITE);
        // to remove the border
        addButton.setBorder(null);

        
        // Null layout
        setLayout(null);

        // Add elements to panel
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
    }

}
