//imports 
import java.util.ArrayList;

public class PatientDatabase extends Databases {
    
    // Declaring and initializing variables/objects related to the patient database  
    private final String PATIENT_LIST = "Databases/Patient_List.csv";
    // Used ArrayList of ArrayLists instead of ArrayList of Arrays so optional features, such as: add column, are possible
    private ArrayList<ArrayList<String>> patientInfo = new ArrayList<ArrayList<String>>();


    /**
     * Allows controller to access the ArrayList 
     * @return patient ArrayList that stores the patient database 
     */
    public ArrayList<ArrayList<String>> getTable(){

        return patientInfo; 

    }

    /**
     * Allows controller to send a table to the database to be stored 
     * @param table is the table data to be stored 
     */
    public void saveData(ArrayList<ArrayList<String>> table){

        patientInfo = table; 

        arrayListToCSV(patientInfo, PATIENT_LIST);

    }

    /**
     * Is called by a controller. This method calls the Database method 'addRow' to add another row to the patient ArrayList
     * Adding another row to the patient ArrayList means that the user wants to add another patient into the database 
     */
    public void addRow(){

        addRow(patientInfo, PATIENT_LIST);

    }

    /**
     * Is called by a controller. Removes a selected row from the patient ArrayList 
     * Calls Database method 'removeRow' which removes a selected row (each row is an ArrayList) 
     * Removing a row means the user wants to delete a patient from the database 
     * @param row is the row to be deleted 
     */
    public void removeRow(int row){


        removeRow(patientInfo, row, PATIENT_LIST);

    }

    /**
     * Is called by a controller. Edits a specific element from the patient ArrayList 
     * Calls Database method 'editTable' which changes an element of the ArrayList 
     * @param row is the row/patient that needs to be changed 
     * @param column is the column that needs to be changed 
     * @param change is the String that the element should change to
     */
    public void editTable(int row, int column, String change){


        editTable(patientInfo, row, column, change, PATIENT_LIST);

    }

    /**
     * Called by a controller. Returns the number of patients 
     * @return # of patients in the database 
     */
    public int getNumberOfPatients(){

        return patientInfo.size();

    }

    /**
     * Constructor for the patient database 
     */
    public PatientDatabase(){

        // Reads csv file to make ArrayList/store information
        csvToArrayList(patientInfo, PATIENT_LIST);

    }

}
