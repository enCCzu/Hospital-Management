import java.io.*;
import java.awt.*;
import java.util.ArrayList;

abstract class ControllerHelperMethods {

    protected ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    protected LoginCredentialsDatabase loginDatabase = new LoginCredentialsDatabase();
    protected PatientDatabase patientDatabase = new PatientDatabase();
    protected RoomManagementDatabase roomDatabase = new RoomManagementDatabase();

    /**
     * Checks the username and password entered by the user
     * Returns true if the username and password match the database
     * @param userName Username entered by user
     * @param password Password entered by user
     * @return Boolean of if the username and password are correct
     */
    public boolean checkPassword(String userName, String password) {

        return checkPassword(userName, password);
    }

    /**
     * Gets the table from the model
     * Gets either the patient or room table depending on requested table
     * @param requestedTable The table that the program wants to use
     * @return Return the table the user wants
      */
    public ArrayList<ArrayList<String>> getTable(String requestedTable) {

        if (requestedTable.equals("patient")){

            table = patientDatabase.getTable();

        }
        else if (requestedTable.equals("room")){

            table = roomDatabase.getTable();

        }
        return table;
    }

    /**
     * Sends the table to from view to model
     * @param viewTable table from view
     * @return ArrayList of the table to the model
     */
    public ArrayList<ArrayList<String>> sendTable(ArrayList<ArrayList<String>> viewTable) {

        table = viewTable;
        return table;
    }

    /**
     * Finds the number of patients in the database
     * @return returns the number of patients
     */
    protected int findNumberOfPatients(){
        return patientDatabase.getTable().size();
    }

    /**
     * Finds the total number of beds in the database
     * @return Returns the total number of beds
     */
    protected int findTotalBeds(){
        int totalBeds = 0;
        ArrayList<ArrayList<String>> roomInfo = roomDatabase.getTable();
        for(int i = 0; i < roomInfo.size(); i++){
            totalBeds += Integer.parseInt(roomInfo.get(i).get(1));
        }

        return totalBeds;
    }

    /**
     * Finds the number of available beds
     * @return returns the number of available beds
     */
    protected int findAvailableBeds(){
        int availableBeds = 0;
        ArrayList<ArrayList<String>> roomInfo = roomDatabase.getTable();
        for(int i = 0; i < roomInfo.size(); i++){
            availableBeds += Integer.parseInt(roomInfo.get(i).get(2));
        }

        return availableBeds;
    }
    /**
     * Finds the number of rooms
     * @return returns the number of rooms
     */
    protected int findNumberOfRooms(){
        return roomDatabase.getTable().size();
    }
}
