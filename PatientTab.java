import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PatientTab extends JPanel{
    //Top Label of Tab
    JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);

    //Buttons to edit and add to the table
    JButton editButton = new JButton("Edit");
    JButton addButton = new JButton("Add");

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
        titleLabel.setBounds(150, 20, size.width + 70, size.height);

        //Edit button and placement
        size = editButton.getPreferredSize();
        editButton.setBounds(230, 70, size.width + 70, size.height);

        //Add button and placement
        size = addButton.getPreferredSize();
        addButton.setBounds(400, 70, size.width + 70, size.height);

        patientTable.setRowSorter(rowSorter);
        // Initializing the JTable
        patientTable.setBounds(250, 75, 800, 600);
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(230, 125, 700, 600);

        jtfFilter.setBounds(230,100,200,20);


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
        add(editButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
    } 
}