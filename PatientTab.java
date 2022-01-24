import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage; 
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PatientTab extends JPanel{
    //Top Label of Tab
    JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);

    //Buttons to remove and add to the table
    JButton addButton = new JButton();
    JButton deleteButton = new JButton();

    private String[] columnNames = {"Health Card", "Name", "Age", "Diagnosis", "Description"};

    DatabaseController controller = new DatabaseController();
    ArrayList<ArrayList<String>> patientInfoList = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> patientDataList = controller.getPatientTable();
    String[][] patientDataArray = arrayListToArray(patientDataList);
        

    private DefaultTableModel model = new DefaultTableModel(patientDataArray, columnNames){

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    private JTable patientTable = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(patientTable);
    

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(patientTable.getModel());

    private JTextField jtfFilter = new JTextField();



    // Color Variables
    Color pink = new Color(255, 204, 229);
    Color transparent = new Color(0, 0, 0, 0);
    Color beige = new Color(246, 245, 225);
    Color unbleachedSilk = new Color(255, 216, 204);
    Color palePink = new Color(249, 215, 214);
    Color snow = new Color(255, 247, 246);
    Color platinum = new Color(228, 231, 234);
    Color lightSteelBlue = new Color(176, 193, 219);
     


    //Constructor
    public PatientTab() {

        patientTable.getTableHeader().setResizingAllowed(true);
        patientTable.getTableHeader().setReorderingAllowed(false);

        for(int i = 0; i < columnNames.length; i++){
            rowSorter.setSortable(i, false);
        }
        



        //Read and use images for add and delete row buttons
        try {
            Image addImage = ImageIO.read(getClass().getResource("Images/add.png"));
            addButton.setIcon(new ImageIcon(addImage));

            Image minusImage = ImageIO.read(getClass().getResource("Images/minus.png"));
            deleteButton.setIcon(new ImageIcon(minusImage));
        } catch (Exception ex) {
            System.out.println(ex);

        }


        
        setElementAttributes();
        createTableFilter(jtfFilter);
        createCellListener(patientTable);
        createButtonListener();

        //Null layout
        setLayout(null);

        //Add elements to panel
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
    } 

    public void setElementAttributes(){
        //Size of Window
        setSize(900, 800);
        setBackground(snow);
        
        jtfFilter.setBounds(225,75,200,20);

        //Top label font and placement
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(225, 20, size.width, size.height);
        
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
        patientTable.setBounds(250, 75, 800, 600);
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(225, 100, 700, 600);
        

        //Table Color adjustment
        patientTable.setBackground(beige);
        JTableHeader header = patientTable.getTableHeader();
        header.setBackground(unbleachedSilk);
        
    }

    public void createCellListener(JTable patientTable){
        patientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    rowSorter.setRowFilter(null);
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();


                    System.out.println(row);
                    System.out.println(column);
                    editCell(row,column);
               }
            }
          });
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

    public void actionPerformed(ActionEvent e) {
        patientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                rowSorter.setRowFilter(null);
                JTable target = (JTable)e.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();


                System.out.println(row);
                System.out.println(column);
                editCell(row,column);
            }
          });
    }

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

    public void editCell(int row, int column){
        String cellEdit = JOptionPane.showInputDialog(null, "What should this cell be changed to?");
        if(cellEdit != null){
            model.setValueAt(cellEdit, row, column);
            patientDataList.get(row).set(column, cellEdit);
            controller.patientDatabase.arrayListToCSV(patientDataList, "Patient_List.csv");
        }
        
    }

    public String[][] arrayListToArray(ArrayList<ArrayList<String>> patientInfo){
        String[][] patientInfoArray = new String[patientInfo.size()][];
        patientInfoArray = patientInfo.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        System.out.println(patientInfoArray.length);
        return patientInfoArray;
    }



    public void createNewRow(){

        
        JTextField healthCardField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField diagnosisField = new JTextField();
        JTextField descriptionField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
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

        String[] row = new String[5];

        int result = JOptionPane.showConfirmDialog(null, panel, "Please enter the patient's information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            row[0] = healthCardField.getText();
            row[1] = nameField.getText();
            row[2] = ageField.getText();
            row[3] = diagnosisField.getText();
            row[4] = descriptionField.getText();
            // add row to the model
            model.addRow(row);
            ArrayList<String> newRow = new ArrayList<String>();
            for(int i = 0;i < row.length; i++){
                newRow.add(row[i]);
            }
            patientDataList.add(newRow);
            controller.patientDatabase.arrayListToCSV(patientDataList, "Patient_List.csv");    
        }
    }

    public void patientInfoPopup(int row){
        
    }


}