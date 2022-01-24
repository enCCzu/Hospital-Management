
// imports 
import java.util.ArrayList;
import javax.swing.*; 
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

    // Creating database controller to pull data from database 
    private DatabaseController controller = new DatabaseController();
    private ArrayList<ArrayList<String>> patientDataList = controller.getPatientTable();
    private String[][] patientDataArray = controller.arrayListToArray(patientDataList);

    // Buttons for JTable to edit and add to the table
    JButton deleteButton = new JButton();
    JButton addButton = new JButton();

    // Creating JTable

    private String[] columnNames = {"Health Card", "Name", "Age", "Diagnosis", "Description"};

    private DefaultTableModel model = new DefaultTableModel(patientDataArray, columnNames){

        // Strangely throws an error 
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    
    private JTable patientTable = new JTable(model);
    private JScrollPane scrollPane = new JScrollPane(patientTable);
    // Sort table
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(patientTable.getModel());
    private JTextField jtfFilter = new JTextField();

    // Color Variables
    private Color rosePink = new Color(255, 105, 105);
    private Color lightPink = new Color(255, 186, 179);
    private Color beige = new Color(246, 245, 225);
    private Color unbleachedSilk = new Color(255, 216, 204);
    private Color peachPink = new Color(249, 215, 214);
    private Color snowPink = new Color(255, 239, 237);
    private Color platinum = new Color(237, 246, 255);
    private Color steelBlue = new Color(176, 193, 219);
    private Color erinColor = new Color(225, 242, 255);


    /**
     * 
     */
    public void setElementAttributes(){
        //Panel 
        setSize(900, 800);
        setBackground(erinColor);
        setLayout(null);

        // Title of page 
        JLabel titleLabel = new JLabel("<html>"+"Patients"+"</html>", JLabel.RIGHT);
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        titleLabel.setForeground(rosePink);
        // position
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(225, 20, size.width, size.height);

        // Read and use images for add and delete row buttons
        try {
            BufferedImage addImage = ImageIO.read(getClass().getResource("Images/add.png"));
            addButton.setIcon(new ImageIcon(addImage));

            BufferedImage minusImage = ImageIO.read(getClass().getResource("Images/minus.png"));
            deleteButton.setIcon(new ImageIcon(minusImage));
        } catch (Exception e) {

            System.out.println("Images required. Please do not steal the images.");
            System.out.println(e);

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

        // JTable filter
        jtfFilter.setBounds(225,75,200,20);
        
        // Initializing the JTable
        patientTable.setRowSorter(rowSorter);
        patientTable.setBounds(250, 75, 900, 600);
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(225, 100, 800, 600);
        
        //Table Color adjustment
        patientTable.setBackground(beige);
        JTableHeader header = patientTable.getTableHeader();
        header.setBackground(unbleachedSilk);

        // add to panel 
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
        
    }

    /**
     * 
     * @param jtfFilter
     */
    public void createTableFilter(JTextField jtfFilter){
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
    }

    /**
     * 
     */
    public void createNewRow(){

        // JTextFields for user to input information 
        JTextField healthCardField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField diagnosisField = new JTextField();
        JTextField descriptionField = new JTextField();

        // Objects within the popup 
        JPanel addRowPopup = new JPanel();
        // Layout
        addRowPopup.setLayout(new BoxLayout(addRowPopup, BoxLayout.PAGE_AXIS));
        addRowPopup.add(Box.createRigidArea(new Dimension(2,2)));

        // Adding objects to popup (text and textfields)
        addRowPopup.add(new JLabel("Patient Health Card:"));
        addRowPopup.add(healthCardField);
        addRowPopup.add(new JLabel("Patient Name:"));
        addRowPopup.add(nameField);
        addRowPopup.add(Box.createHorizontalStrut(15)); // a spacer
        addRowPopup.add(new JLabel("Patient Age:"));
        addRowPopup.add(ageField);
        addRowPopup.add(new JLabel("Diagnosis:"));
        addRowPopup.add(diagnosisField);
        addRowPopup.add(new JLabel("Description"));
        addRowPopup.add(descriptionField);

        //Array to store text 
        String[] storedText = new String[5];

        // Creating popup container
        int result = JOptionPane.showConfirmDialog(null, addRowPopup, "Please enter the patient's information", JOptionPane.OK_CANCEL_OPTION);
        // If user presses OK 
        if (result == JOptionPane.OK_OPTION) {
            // Get text 
            storedText[0] = healthCardField.getText();
            storedText[1] = nameField.getText();
            storedText[2] = ageField.getText();
            storedText[3] = diagnosisField.getText();
            storedText[4] = descriptionField.getText();
            // add row to the model (table)
            model.addRow(storedText);

            // Add row to the arrayList where the data is stored 
            ArrayList<String> newRow = new ArrayList<String>();
            for(int i = 0;i < storedText.length; i++){
                newRow.add(storedText[i]);
            }
            patientDataList.add(newRow);

            // Send new data to controller to be saved in the database 
            controller.savePatientData(patientDataList);;    
        }
    }

    public void createButtonListener(){
        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = patientTable.getSelectedRow();
                model.removeRow(patientTable.getSelectedRow());
                patientDataList.remove(row);
                controller.patientDatabase.arrayListToCSV(patientDataList, "Patient_List.csv");

                
            }

        });

        addButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewRow();
                
            }

        });
    }

    /**
     * 
     * @param row
     * @param column
     */
    public void editCell(int row, int column){
        String cellEdit = JOptionPane.showInputDialog(null, "What should this cell be changed to?");
        if(cellEdit != null){
            model.setValueAt(cellEdit, row, column);
            patientDataList.get(row).set(column, cellEdit);
            controller.savePatientData(patientDataList);;
        }
        
    }

    /**
     * 
     * @param patientTable
     */
    public void createCellListener(JTable patientTable){
        patientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    rowSorter.setRowFilter(null);
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    editCell(row,column);
               }
            }
          });
    }

    // Constructor
    public Patient() {
        // Size of Window
        setSize(900, 800);

        // Table 
        patientTable.getTableHeader().setResizingAllowed(true);
        patientTable.getTableHeader().setReorderingAllowed(false);

        for(int i = 0; i < columnNames.length; i++){
            rowSorter.setSortable(i, false);
        }
        
        // Methods to run panel 
        setElementAttributes();
        createTableFilter(jtfFilter);
        createCellListener(patientTable);
        createButtonListener();

    }

}