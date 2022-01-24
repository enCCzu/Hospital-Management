import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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

public class RoomsTab extends JPanel{
    //Top Label of Tab
    JLabel titleLabel = new JLabel("Patients", JLabel.RIGHT);

    //Buttons to remove and add to the table
    JButton addButton = new JButton();
    JButton deleteButton = new JButton();
    JButton arrowButton = new JButton();

    private String[] columnNames = {"Room #", "# of Beds", "Beds Available", "Type of Room"};

    DatabaseController controller = new DatabaseController();
    ArrayList<ArrayList<String>> roomInfoList = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> roomDataList = controller.getRoomTable();
    String[][] roomDataArray = arrayListToArray(roomDataList);
        

    private DefaultTableModel model = new DefaultTableModel(roomDataArray, columnNames){

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    private JTable roomTable = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(roomTable);
    

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(roomTable.getModel());

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

    private Component add;
     


    //Constructor
    public RoomsTab() {

        roomTable.getTableHeader().setResizingAllowed(true);
        roomTable.getTableHeader().setReorderingAllowed(false);

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
        createCellListener(roomTable);
        createButtonListener();

        //Null layout
        setLayout(null);

        //Add elements to panel
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(arrowButton);
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
        
        roomTable.setRowSorter(rowSorter);
        // Initializing the JTable
        roomTable.setBounds(250, 75, 800, 600);
        scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(225, 100, 700, 600);

        //Table Color adjustment
        roomTable.setBackground(beige);
        JTableHeader header = roomTable.getTableHeader();
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
                int row = roomTable.getSelectedRow();
                model.removeRow(roomTable.getSelectedRow());
                roomDataList.remove(row);
                controller.roomDatabase.arrayListToCSV(roomDataList, "Room_List.csv");

                
            }

        });

        addButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewRow();
                
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
            roomDataList.get(row).set(column, cellEdit);
            controller.roomDatabase.arrayListToCSV(roomDataList, "Room_List.csv");
        }
        
    }

    public String[][] arrayListToArray(ArrayList<ArrayList<String>> patientInfo){
        String[][] patientInfoArray = new String[patientInfo.size()][];
        patientInfoArray = patientInfo.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        System.out.println(patientInfoArray.length);
        return patientInfoArray;
    }



    public void createNewRow(){

        
        JTextField roomNumField = new JTextField();
        JTextField bedNumField = new JTextField();
        JTextField bedsAvailField = new JTextField();
        JTextField roomTypeField = new JTextField();
        

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(2,2)));
        panel.add(new JLabel("Room #"));
        panel.add(roomNumField);
        panel.add(new JLabel("# of Beds"));
        panel.add(bedNumField);
        panel.add(Box.createHorizontalStrut(15)); // a spacer
        panel.add(new JLabel("Beds Available:"));
        panel.add(bedsAvailField);
        panel.add(new JLabel("Type of Room:"));
        panel.add(roomTypeField);

        String[] row = new String[4];

        int result = JOptionPane.showConfirmDialog(null, panel, "Please enter the patient's information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            row[0] = roomNumField.getText();
            row[1] = bedNumField.getText();
            row[2] = bedsAvailField.getText();
            row[3] = roomTypeField.getText();
            // add row to the model
            model.addRow(row);
            ArrayList<String> newRow = new ArrayList<String>();
            for(int i = 0;i < row.length; i++){
                newRow.add(row[i]);
            }
            roomDataList.add(newRow);
            controller.roomDatabase.arrayListToCSV(roomDataList, "Room_List.csv");    
        }
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