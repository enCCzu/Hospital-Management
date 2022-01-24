//imports 
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

abstract class DatabaseControllers {
    
    // Table that will be sent to view 
    protected ArrayList<ArrayList<String>> table;

    // Initializing Model databases 
    protected CredentialsDatabase credentialsDatabase = new CredentialsDatabase();
    protected PatientDatabase patientDatabase = new PatientDatabase();
    protected RoomDatabase roomDatabase = new RoomDatabase();

    /**
     * Checks the username and password entered
     * @param userName Username entered
     * @param password Password entered
     * @return boolean true if matches sytem
     */
    public boolean checkPassword(String userName, String password) {

        return credentialsDatabase.checkPassword(userName, password);
    }

    /**
     * Gets and saves the patient table
     * @return ArrayList of patient information
     */
    public ArrayList<ArrayList<String>> getPatientTable() {

        table = patientDatabase.getTable();

        return table;
    }


    /**
     * Gets and saves the room table
     * @return ArrayList of room information
     */
    public ArrayList<ArrayList<String>> getRoomTable() {

        table = roomDatabase.getTable();

        return table;
    }



    /**
     * Gets the number of patients 
     * @return int of number of patients
     */
    protected int getNumberOfPatients(){

        return patientDatabase.getNumberOfPatients();

    }

    /**
     * Gets the number of rooms
     * @return int of number of rooms
     */
    protected int getNumberOfRooms(){

        return roomDatabase.getNumberOfRooms();
        
    }

    /**
     * Gets the total number of beds
     * @return int of number of beds
     */
    protected int getTotalBeds(){

        return roomDatabase.getTotalBeds();
    }

    /**
     * Gets the number of available beds
     * @return int of available beds
     */
    protected int getAvailableBeds(){

        return roomDatabase.getAvailableBeds();
    }

    /**
     * Gets the username of the user
     * @return String of username
     */
    protected String getUsername(){

        return credentialsDatabase.getUsername();
    }

}
