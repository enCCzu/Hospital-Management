
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
    private JButton deleteButton = new JButton();
    private JButton addButton = new JButton();
    private JButton arrowButton = new JButton();

    // Creating JTable

    private String[] columnNames = {"Health Card", "Name", "Age", "Diagnosis", "Description"};

    private DefaultTableModel model = new DefaultTableModel(patientDataArray, columnNames){

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
    private Color beige = new Color(246, 245, 225);
    private Color unbleachedSilk = new Color(255, 216, 204);
    private Color erinColor = new Color(225, 242, 255);
    private Color lightPink = new Color(255, 186, 179);


    /**
     * Adds all JLabels and JButtons to the panel
     * Sets sizes and location
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

            BufferedImage arrowImage = ImageIO.read(getClass().getResource("Images/Arrow.png"));
            arrowButton.setIcon(new ImageIcon(arrowImage));
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

        //Arrow button and placement
        size = arrowButton.getPreferredSize();
        arrowButton.setBounds(325, 705, 30, 30);
        // to remote the spacing between the image and button's borders
        arrowButton.setMargin(new Insets(0, 0, 0, 0));
        // to add a different background
        arrowButton.setBackground(Color.WHITE);
        // to remove the border
        arrowButton.setBorder(null);

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
        add(arrowButton);
        add(scrollPane);
        add(jtfFilter);
        
    }

    /**
     * Adds search functionality to the JTable by sorting using the text entered by the user in the JTextField
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
     * Creates a new row of the table
     * Saves added information
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
        addRowPopup.setBackground(lightPink);
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

        //create JPanel for invalid input popup
        JPanel addErrorPopup = new JPanel();
        addErrorPopup.add(new JLabel("You have entered an invalid data type in the Age field"));

        //Array to store text 
        String[] storedText = new String[5];

        // Creating popup container
        int result = JOptionPane.showConfirmDialog(null, addRowPopup, "Please enter the patient's information", JOptionPane.OK_CANCEL_OPTION);
        

        // If user presses OK 
        if (result == JOptionPane.OK_OPTION) {
            if(isNumeric(ageField.getText())){
                // Get text and fix any null values
                storedText[0] = checkForNull(healthCardField.getText());
                storedText[1] = checkForNull(nameField.getText());
                storedText[2] = checkForNull(ageField.getText());
                storedText[3] = checkForNull(diagnosisField.getText());
                storedText[4] = checkForNull(descriptionField.getText());
                // add row to the model (table)
                model.addRow(storedText);

                // Add row to the arrayList where the data is stored 
                ArrayList<String> newRow = new ArrayList<String>();
                for(int i = 0;i < storedText.length; i++){
                    newRow.add(storedText[i]);
                }
                patientDataList.add(newRow);

                // Send new data to controller to be saved in the database 
                controller.savePatientData(patientDataList);
            }
            else{
                System.out.println("Invalid");
                int result2 = JOptionPane.showConfirmDialog(null, addErrorPopup, "Error! Invalid Data Type", JOptionPane.PLAIN_MESSAGE);
            }
            
        }
    }

    /**
     * Checks to see if the string contains any characters from 0-9 or decimals
     * @param str
     * @return
     */
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    /**
     * 
     */
    public void createButtonListener(){
        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = patientTable.getSelectedRow();
                model.removeRow(patientTable.getSelectedRow());
                patientDataList.remove(row);
                // Send new data to controller to be saved in the database 
                controller.savePatientData(patientDataList);

                
            }

        });

        addButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewRow();
                
            }

        });

        arrowButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = patientTable.getSelectedRow();
                if(row >= 0){
                new PatientInfoPopup(row, model);
                }
            }
        });
    }

    /**
     * Edits the cell selected and saves it
     * @param row row selected
     * @param column column selected
     */
    public void editCell(int row){


        JTextField healthCardField = new JTextField(checkForNull(model.getValueAt(row, 0)));
        JTextField nameField = new JTextField(checkForNull(model.getValueAt(row, 1)));
        JTextField ageField = new JTextField(checkForNull(model.getValueAt(row, 2)));
        JTextField diagnosisField = new JTextField(checkForNull(model.getValueAt(row, 3)));
        JTextField descriptionField = new JTextField(checkForNull(model.getValueAt(row, 4)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(lightPink);
        panel.add(Box.createRigidArea(new Dimension(2,2)));
        panel.add(new JLabel("Patient Health Card:"));
        panel.add(healthCardField);
        panel.add(new JLabel("Patient Name:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15)); // a spacer
        panel.add(new JLabel("Patient Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Diagnosis:"));
        panel.add(diagnosisField);
        panel.add(new JLabel("Description"));
        panel.add(descriptionField);

        //create JPanel for invalid input popup
        JPanel addErrorPopup = new JPanel();
        addErrorPopup.add(new JLabel("You have entered an invalid data type in the Age field"));

        String[] cellEdit = new String[5];

        int result = JOptionPane.showConfirmDialog(null, panel, "Editing Row: " + (row + 1), JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(isNumeric(ageField.getText())){

                cellEdit[0] = checkForNull(healthCardField.getText());
                cellEdit[1] = checkForNull(nameField.getText());
                cellEdit[2] = checkForNull(ageField.getText());
                cellEdit[3] = checkForNull(diagnosisField.getText());
                cellEdit[4] = checkForNull(descriptionField.getText());
                // add row to the model

                for(int i = 0;i < cellEdit.length; i++){
                    cellEdit[i] = checkForNull(cellEdit[i]);
                    model.setValueAt(cellEdit[i], row, i);
                    System.out.println(i);
                    System.out.println(cellEdit.length);
                    System.out.println(patientDataList.get(row));
                    patientDataList.get(row).set(i,cellEdit[i]);
                }
                // Send new data to controller to be saved in the database 
                controller.savePatientData(patientDataList);    
            }
            else{
                System.out.println("Invalid");
                int result2 = JOptionPane.showConfirmDialog(null, addErrorPopup, "Error! Invalid Data Type", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * Listens to mouse clicks on a cell
     * @param patientTable table being clicked
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

                    editCell(row);
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

    private String checkForNull(Object valueAt) {
        String stringValue;
        if(valueAt != null){
            stringValue = valueAt.toString();
            return stringValue;
        }
        return "";
    }

}
