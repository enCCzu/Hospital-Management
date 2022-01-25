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


public class PatientInfoPopup {

    Color snow = new Color(255, 247, 246);
    Border outline = BorderFactory.createLineBorder(Color.BLACK, 1);
    Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    Border border = BorderFactory.createCompoundBorder(outline, margin);
    JFrame patientPopup = new JFrame();
    JPanel mainPanel = new JPanel();

    /**
     * Constructor Method for Patient Popup
     * @param row row being selected from the table
     * @param model table where information is pulled from
     */
    public PatientInfoPopup(int row, DefaultTableModel model){


        
        mainPanel.setLayout(null);

        // Name Label
        JLabel patientName = new JLabel();
        patientName.setBorder(border);
        patientName.setText("<html><body><p style='width: 175px;'>"+ (checkForNull(model.getValueAt(row, 1))) + "</p></body></html>");
        patientName.setBounds(200,55,250,50);
        JLabel nameHeader = new JLabel();
        nameHeader.setText("Patient Name: ");
        nameHeader.setBounds(200,20,150,50);

        // Health card Label
        JLabel patientHealthCard = new JLabel();
        patientHealthCard.setBorder(border);
        patientHealthCard.setText("<html><body><p style='width: 100px;'>"+(checkForNull(model.getValueAt(row, 0)))+"</p></body></html>");
        patientHealthCard.setBounds(200,135,250,50);
        JLabel healthCardHeader = new JLabel();
        healthCardHeader.setText("Health Card Number: ");
        healthCardHeader.setBounds(200,100,200,50);

        // Age Label
        JLabel patientAge = new JLabel();
        patientAge.setBorder(border);
        patientAge.setText("<html><body><p style='width: 60px;'>"+(checkForNull(model.getValueAt(row, 2)))+"</p></body></html>");
        patientAge.setBounds(200,210,100,50);
        JLabel ageHeader = new JLabel();
        ageHeader.setText("Age:");
        ageHeader.setBounds(200,175,100,50);

        // Diagnosis Label
        JLabel patientDiagnosis = new JLabel();
        patientDiagnosis.setBorder(border);
        patientDiagnosis.setText("<html><body><p style='width: 310px;'>"+(checkForNull(model.getValueAt(row, 3)))+"</p></body></html>");
        patientDiagnosis.setBounds(25,280,425,75);
        JLabel diagnosisHeader = new JLabel();
        diagnosisHeader.setText("Diagnosis:");
        diagnosisHeader.setBounds(25, 245, 100, 50);

        // Description Label
        JLabel patientDescription = new JLabel();
        patientDescription.setBorder(border);
        patientDescription.setText("<html><body><p style='width: 310px;'>"+(checkForNull(model.getValueAt(row, 4)))+"</p></body></html>");
        patientDescription.setBounds(25,380,425,150);
        JLabel descriptionHeader = new JLabel();
        descriptionHeader.setText("Description:");
        descriptionHeader.setBounds(25,345,100,50);

        // Image Label
        JLabel imageHolder = new JLabel();
        imageHolder.setBounds(30,40,150,200);


        // Reads the patient image
        try {
            Image silhouette  = ImageIO.read(getClass().getResource("Images/silhouette.png"));
            imageHolder.setIcon(new ImageIcon(silhouette));

        } catch (Exception ex) {
            System.out.println(ex);

        }

        // Popup size
        patientPopup.setSize(500, 600);
        patientPopup.add(mainPanel);

        // Add all labels to main panel
        mainPanel.add(patientName);
        mainPanel.add(patientHealthCard);
        mainPanel.add(patientAge);
        mainPanel.add(patientDiagnosis);
        mainPanel.add(patientDescription);
        mainPanel.add(descriptionHeader);
        mainPanel.add(diagnosisHeader);
        mainPanel.add(ageHeader);
        mainPanel.add(healthCardHeader);
        mainPanel.add(nameHeader);
        mainPanel.add(imageHolder);

        // Frame settings
        patientPopup.setBackground(snow);
        patientPopup.setVisible(true);
        patientPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
    }

    /**
     * If the object has a value it is converted to a string otherwise a blank string is returned
     * in order to avoid NullPointerExceptions
     * @param valueAt value being checked
     * @return String of the valueAt or a blank string
     */
    private String checkForNull(Object valueAt) {
        String stringValue;
        if(valueAt != null){
            stringValue = valueAt.toString();
            return stringValue;
        }
        return "";
    }
}
