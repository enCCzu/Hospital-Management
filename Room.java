// imports
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Room extends JPanel {

    //Buttons to remove and add to the table
    private JButton addButton = new JButton();
    private JButton deleteButton = new JButton();

    // Creating JTable 
    private String[] columnNames = {"Room #", "# of Beds", "Beds Available", "Type of Room"};

    private DatabaseController controller = new DatabaseController();
    private ArrayList<ArrayList<String>> roomDataList = controller.getRoomTable();
    private String[][] roomDataArray = controller.arrayListToArray(roomDataList);
        

    private DefaultTableModel model = new DefaultTableModel(roomDataArray, columnNames){

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
    private JTable roomTable = new JTable(model);

    private JScrollPane scrollPane = new JScrollPane(roomTable);
    
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(roomTable.getModel());

    private JTextField jtfFilter = new JTextField();


    // Color Variables
    private Color rosePink = new Color(255, 105, 105);
    private Color beige = new Color(246, 245, 225);
    private Color unbleachedSilk = new Color(255, 216, 204);
    private Color erinColor = new Color(225, 242, 255);
   
    /**
     * Adds all JLabels and JButtons to the panel
     * Sets sizes and location
     */
    public void setElementAttributes(){
        //Size of Window
        setSize(900, 800);
        setBackground(erinColor);
        setLayout(null);

        //Top label (title) font and placement
        JLabel titleLabel = new JLabel("<html>"+"Rooms"+"</html>", JLabel.RIGHT);
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        titleLabel.setForeground(rosePink);
        //position 
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
        
        // JTable filter 
        jtfFilter.setBounds(225,75,200,20);

        // Initializing the JTable
        roomTable.setRowSorter(rowSorter);
        roomTable.setBounds(250, 75, 800, 600);
        scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(225, 100, 700, 600);

        //Table Color adjustment
        roomTable.setBackground(beige);
        JTableHeader header = roomTable.getTableHeader();
        header.setBackground(unbleachedSilk);   
        
        //Add elements to panel
        add(titleLabel);
        add(deleteButton);
        add(addButton);
        add(scrollPane);
        add(jtfFilter);
    }

    /**
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
        JTextField roomNumField = new JTextField();
        JTextField bedNumField = new JTextField();
        JTextField bedsAvailField = new JTextField();
        JTextField roomTypeField = new JTextField();
        
        // Objects within the popup 
        JPanel addRowPopup = new JPanel();
        // Layout
        addRowPopup.setLayout(new BoxLayout(addRowPopup, BoxLayout.PAGE_AXIS));
        addRowPopup.add(Box.createRigidArea(new Dimension(2,2)));

        // Adding objects to popup (text and textfields)
        addRowPopup.add(new JLabel("Room #"));
        addRowPopup.add(roomNumField);
        addRowPopup.add(new JLabel("# of Beds"));
        addRowPopup.add(bedNumField);
        addRowPopup.add(Box.createHorizontalStrut(15)); // a spacer
        addRowPopup.add(new JLabel("Beds Available:"));
        addRowPopup.add(bedsAvailField);
        addRowPopup.add(new JLabel("Type of Room:"));
        addRowPopup.add(roomTypeField);

        //Array to store text 
        String[] storedText = new String[4];

        // Creating popup container
        int result = JOptionPane.showConfirmDialog(null, addRowPopup, "Please enter the room information", JOptionPane.OK_CANCEL_OPTION);
        // If user presses OK
        if (result == JOptionPane.OK_OPTION) {
            // Get text 
            storedText[0] = roomNumField.getText();
            storedText[1] = bedNumField.getText();
            storedText[2] = bedsAvailField.getText();
            storedText[3] = roomTypeField.getText();
            // add row to the model (table)
            model.addRow(storedText);

            // Add row to the arrayList where the data is stored 
            ArrayList<String> newRow = new ArrayList<String>();
            for(int i = 0;i < storedText.length; i++){
                newRow.add(storedText[i]);
            }
            roomDataList.add(newRow);

            // Send new data to controller to be saved in the database 
            controller.saveRoomData(roomDataList);; 
        }
    }

    /**
     * 
     */
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

    /**
     * Edits the cell selected and saves it
     * @param row row selected
     * @param column column selected
     */
    public void editCell(int row, int column){
        String cellEdit = JOptionPane.showInputDialog(null, "What should this cell be changed to?");
        if(cellEdit != null){
            model.setValueAt(cellEdit, row, column);
            roomDataList.get(row).set(column, cellEdit);
            controller.saveRoomData(roomDataList);
        }
        
    }

    /**
     * Listens to mouse clicks on a cell
     * @param roomTable table being clicked
     */
    public void createCellListener(JTable roomTable){
        roomTable.addMouseListener(new MouseAdapter() {
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

    /**
     * 
     * @param valueAt
     * @return
     */
    private String checkForNull(Object valueAt) {
        String stringValue;
        if(valueAt != null){
            stringValue = valueAt.toString();
            return stringValue;
        }
        return "";
    }

    //Constructor
    public Room() {

        roomTable.getTableHeader().setResizingAllowed(true);
        roomTable.getTableHeader().setReorderingAllowed(false);

        for(int i = 0; i < columnNames.length; i++){
            rowSorter.setSortable(i, false);
        }
        
        setElementAttributes();
        createTableFilter(jtfFilter);
        createCellListener(roomTable);
        createButtonListener();
    }     


}