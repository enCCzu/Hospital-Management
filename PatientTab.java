import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.image.BufferedImage; 

public class PatientTab extends JPanel{
    //Top Label of Tab
    JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);

    //Buttons to edit and add to the table
    JButton deleteButton = new JButton();
    JButton addButton = new JButton();

    private String[] columnNames
            = {"Name", "Patient ID", "Age", "Room Number"};

    private Object[][] data = {
        {"Annie", "00004", 7, 4},
        {"Ashe", "00005", 23, 5},
        {"Caitlyn", "00006", 27, 6},
        {"Camille", "00002", 64, 2},
        {"Danny", "00008", 16, 8},
        {"Diana", "00009", 23, 9},
        {"Erin", "00003", 16, 3}
    };

    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable patientTable = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(patientTable);

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(patientTable.getModel());

    private JTextField jtfFilter = new JTextField();


    //Constructor
    public PatientTab() {
        //Size of Window
        setSize(900, 800);
        
        //Top label font and placement
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(225, 20, size.width, size.height);

        //Read and use images for add and delete row buttons
        try {
            Image addImage = ImageIO.read(getClass().getResource("Images/add.png"));
            addButton.setIcon(new ImageIcon(addImage));

            Image minusImage = ImageIO.read(getClass().getResource("Images/minus.png"));
            deleteButton.setIcon(new ImageIcon(minusImage));
          } catch (Exception ex) {
            System.out.println(ex);

          }
          
        //Edit button and placement
        size = deleteButton.getPreferredSize();
        deleteButton.setBounds(225, 705, 30, 30);
        // to remote the spacing between the image and button's borders
        deleteButton.setMargin(new Insets(0, 0, 0, 0));
        // to add a different background
        deleteButton.setBackground(Color.WHITE);
        // to remove the border
        deleteButton.setBorder(null);

        //Add button and placement
        size = addButton.getPreferredSize();
        addButton.setBounds(275, 705, 30, 30);
        addButton.setMargin(new Insets(0, 0, 0, 0));
        // to add a different background
        addButton.setBackground(Color.WHITE);
        // to remove the border
        addButton.setBorder(null);


        patientTable.setRowSorter(rowSorter);
        // Initializing the JTable
        //patientTable.setBounds(250, 75, 800, 600);
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(225, 100, 700, 600);

        jtfFilter.setBounds(225,75,200,20);


        //jtfFilter.setBounds(0, 20, 100, 20);

        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        patientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable)e.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();

                System.out.println(row);
                System.out.println(column);
               // do some stuff
            }
          });
        //Null layout
        setLayout(null);

        //Add elements to panel
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
    } 
}